package cn.idealismus.pay.service.impl;

import cn.idealismus.pay.dao.AccountPayDao;
import cn.idealismus.pay.entity.AccountPay;
import cn.idealismus.pay.service.AccountPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountPayServiceImpl implements AccountPayService {
    @Autowired
    private AccountPayDao accountPayDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    /**
     * 充值 --> 充值成功，发送通知
     * @param accountPay
     * @return
     */
    @Override
    public AccountPay insertAccountPay(AccountPay accountPay) {
        int success = accountPayDao.insertAccountPay(accountPay.getId(),accountPay.getAccountNo(),accountPay.getPayAmount(),"success");
        if (success > 1) {
            //使用普通消息发送通知
            rocketMQTemplate.convertAndSend("topic_notify_msg",accountPay);
            return accountPay;
        } else {
            return null;
        }
    }

    //查询充值记录，接收通知方调用此方法查询结果
    @Override
    public AccountPay getAccountPay(String txNo) {
        return accountPayDao.findByIdTxNo(txNo);
    }
}
