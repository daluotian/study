package cn.idealismus.bank2.service.impl;

import cn.idealismus.bank2.dao.AccountInfoDao;
import cn.idealismus.bank2.model.AccountChangeEvent;
import cn.idealismus.bank2.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    
    @Override
    @Transactional
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent) {
        String txNo = accountChangeEvent.getTxNo();
        log.info("bank2 开始执行新增金额操作... ");
        //做幂等
        if (accountInfoDao.isExistTx(txNo) > 0) {
            return;
        }
        //增加金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        //添加记录 做幂等
        accountInfoDao.addTx(txNo);
    }
}
