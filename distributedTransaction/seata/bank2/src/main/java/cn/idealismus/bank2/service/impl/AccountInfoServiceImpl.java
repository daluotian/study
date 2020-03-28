package cn.idealismus.bank2.service.impl;

import cn.idealismus.bank2.dao.AccountInfoDao;
import cn.idealismus.bank2.service.AccountInfoService;
import io.seata.core.context.RootContext;
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
    
    @Transactional
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank2 begin , XID : " + RootContext.getXID());
        accountInfoDao.updateAccountBalance(accountNo, amount);
        if (amount == 201) {
            throw new RuntimeException("bank2 throw Exception...");
        }
    }
}
