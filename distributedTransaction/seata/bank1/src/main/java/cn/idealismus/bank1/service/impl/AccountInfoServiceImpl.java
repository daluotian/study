package cn.idealismus.bank1.service.impl;

import cn.idealismus.bank1.dao.AccountInfoDao;
import cn.idealismus.bank1.feign.Bank2Client;
import cn.idealismus.bank1.service.AccountInfoService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional
    @GlobalTransactional //全局事务开启
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank1 begin , XID : " + RootContext.getXID());
        //调用扣钱
        accountInfoDao.updateAccountBalance(accountNo, amount * -1);
        //调用微服务，转账
        String transfer = bank2Client.transfer(amount);
        if ("fallback".equals(transfer)) {
            throw new RuntimeException("调用微服务失败异常");
        }
        if (amount == 3) {
            throw new RuntimeException("ban1 throw Exception...");
        }
    }
}
