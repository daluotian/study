package cn.idealismus.bank1.serice.impl;

import cn.idealismus.bank1.dao.AccountInfoDao;
import cn.idealismus.bank1.model.AccountChangeEvent;
import cn.idealismus.bank1.serice.AccountInfoService;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    
    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        Message<JSONObject> message = MessageBuilder.withPayload(jsonObject).build();
        /**
        /发送一条事务消息
        /txProducerGroup 消息组 
        /String destination topic 
        /Message<?> message 消息
        /Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction("product_group_txmsg_bank1", "topic_msg",
                message, null);
    }

    @Override
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        
    }
}
