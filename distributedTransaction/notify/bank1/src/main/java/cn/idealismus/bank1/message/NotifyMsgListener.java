package cn.idealismus.bank1.message;

import cn.idealismus.bank1.entity.AccountPay;
import cn.idealismus.bank1.model.AccountChangeEvent;
import cn.idealismus.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "topic_notify_msg",consumerGroup = "consumer_group_notify")
public class NotifyMsgListener implements RocketMQListener<AccountPay> {
    @Autowired
    private AccountInfoService accountInfoService;
    
    @Override
    public void onMessage(AccountPay accountPay) {
        log.info("bank1接收到消息，开始执行增加账户金额");
        //变更金额
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
        accountChangeEvent.setAccountNo(accountPay.getAccountNo());
        accountChangeEvent.setAmount(accountPay.getPayAmount());
        accountInfoService.updateAccountInfoBalance(accountChangeEvent);
        log.info("接收消息结束......");
    }
}
