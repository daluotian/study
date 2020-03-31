package cn.idealismus.bank1.service.impl;

import cn.idealismus.bank1.dao.AccountInfoDao;
import cn.idealismus.bank1.entity.AccountPay;
import cn.idealismus.bank1.feign.PayClient;
import cn.idealismus.bank1.model.AccountChangeEvent;
import cn.idealismus.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private PayClient payClient;
    
    @Override
    @Transactional
    public void updateAccountInfoBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank1开始新增金额...开始幂等...");
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo()) > 1) {
            return;
        }
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        //插入一条记录 做幂等
        accountInfoDao.addTx(accountChangeEvent.getTxNo());
        log.info("bank1结束新增金额");
    }

    @Override
    public AccountPay queryPayResult(String txNo) {
        log.info("开始查询接口...");
        AccountPay payResult = payClient.payresult(txNo);
        if ("success".equals(payResult.getResult())) {
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(payResult.getAccountNo());
            accountChangeEvent.setAmount(payResult.getPayAmount());
            accountChangeEvent.setTxNo(txNo);
            updateAccountInfoBalance(accountChangeEvent);
        }
        log.info("结束查询接口...");
        return payResult;
    }
}
