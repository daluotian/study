package cn.idealismus.bank2.message;

import cn.idealismus.bank2.model.AccountChangeEvent;
import cn.idealismus.bank2.service.AccountInfoService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_txmsn_bank2",topic = "topic_msg")
public class ConsumerTxmsgListener implements RocketMQListener<String> {
    @Autowired
    private AccountInfoService accountInfoService;
    
    //接收消息，增加金额
    @Override
    public void onMessage(String string) {
        log.info("bank2监听到消息");
        JSONObject jsonObject = JSONObject.parseObject(string);
        String accountChange = jsonObject.getString("accountChange");
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChange, AccountChangeEvent.class);
        //由于是bank1中发送来的消息，而这里是需要改bank2中的信息，所以需要改账户信息
        String accountNo = "2";
        accountChangeEvent.setAccountNo(accountNo);
        //这里不做幂等 在调用的方法里面已经做了幂等
        accountInfoService.addAccountInfoBalance(accountChangeEvent);
    }
}
