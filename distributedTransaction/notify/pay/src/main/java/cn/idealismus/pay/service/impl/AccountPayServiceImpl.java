package cn.idealismus.pay.service.impl;

import cn.idealismus.pay.dao.AccountPayDao;
import cn.idealismus.pay.entity.AccountPay;
import cn.idealismus.pay.service.AccountPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AccountPayServiceImpl implements AccountPayService {
    @Autowired
    private AccountPayDao accountPayDao;

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    /**
     * 充值 --> 充值成功，发送通知
     * @param accountPay
     * @return
     */
    @Override
    public AccountPay insertAccountPay(AccountPay accountPay) {
        log.info("充值开始...");
        int success = accountPayDao.insertAccountPay(accountPay.getId(),accountPay.getAccountNo(),accountPay.getPayAmount(),"success");
        if (success > 1) {
            log.info("充值成功，开始发送消息...");
            //使用普通消息发送通知
            rocketMQTemplate.convertAndSend("topic_notify_msg",accountPay);
            return accountPay;
        } else {
            log.info("充值结束，充值失败...");
            return null;
        }
    }

    //查询充值记录，接收通知方调用此方法查询结果
    @Override
    public AccountPay getAccountPay(String txNo) {
        return accountPayDao.findByIdTxNo(txNo);
    }
}
