package cn.idealismus.bank1.service.impl;


import cn.idealismus.bank1.dao.AccountInfoDao;
import cn.idealismus.bank1.feign.Bank2Client;
import cn.idealismus.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    Bank2Client bank2Client;

    /**
     * 空回滚:在没有调用 TCC 资源 Try 方法的情况下，调用了二阶段的 Cancel 方法
     * 幂等:保证多次提交不会重复使用或者释放资源
     * 悬挂:悬挂就是对于一个分布式事务，其二阶段 Cancel 接口比 Try 接口先执行。
     * try 幂等校验
     * try 悬挂处理
     * 检查扣款金额是否足够
     * 扣减金额
     */
    @Transactional
    //发起扣款 这个就是try
    @Hmily(confirmMethod = "commit", cancelMethod = "rollback")  
    //标记Hmily注解的 就是try方法，这个注解中指定 confirm 和 cancel 方法
    public void updateAccountBalance(String accountNo, Double amount) {
        //获取全局事务ID
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        //判断local_try_log 中是否有 try 日志，如果有就不再执行
        if (accountInfoDao.isExistTry(transId) > 0) {
            log.info("bank1 try 已经执行...,transId: " + transId);
            return;
        }
        
        //悬挂处理
        if (accountInfoDao.isExistConfirm(transId) > 0 || accountInfoDao.isExistCancel(transId) > 0) {
            log.info("bank1 try 悬挂处理...,transId: " + transId);
            return;
        }
        
        //扣减金额
        if (accountInfoDao.subtractAccountBalance(accountNo, amount) <= 0) {
            throw new RuntimeException("bank1 try 扣减失败...,transId: " + transId);
        }
        //为了做幂等，插入一条记录
        accountInfoDao.addTry(transId);
        
        //远程调用转账
        if (bank2Client.transfer(amount)) {
            throw new RuntimeException("bank1 远程调用转账失败...,transId: " + transId);
        }
    }
    
    public void commit (String accountNo, Double amount) {
        //获取全局事务ID
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 confirm 已经执行...,transId: " + transId);
    }

    /**
     *做幂等校验
     *做空回滚校验
     *增加可用余额
     */
    @Transactional
    public void rollback (String accountNo, Double amount) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        //做幂等校验
        if (accountInfoDao.isExistConfirm(transId) > 0) {
            log.info("bank1 cancel 已处理，幂等，无需处理第二次...,transId: " + transId);
            return;
        }
        //做空回滚
        if (accountInfoDao.isExistTry(transId) <= 0) {
            log.info("bank1 cancel 还未开始try,空回滚...,transId: " + transId);
            return;
        } 
        
        //增加可用余额
        accountInfoDao.addAccountBalance(accountNo,amount);
        //插入cancel执行记录
        accountInfoDao.addCancel(transId);
    }
}
