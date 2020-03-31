package cn.idealismus.bank1.serice.impl;

import cn.idealismus.bank1.dao.AccountInfoDao;
import cn.idealismus.bank1.model.AccountChangeEvent;
import cn.idealismus.bank1.serice.AccountInfoService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    
    
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        /**
        /发送一条事务消息
        /txProducerGroup 消息组 
        /String destination topic 
        /Message<?> message 消息
        /Object arg 参数
         */
        log.info("bank1开始发送消息");
        rocketMQTemplate.sendMessageInTransaction("product_group_txmsg_bank1", "topic_msg",
                message, null);
    }

    @Override
    @Transactional
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank1开始执行扣款操作");
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo()) > 0) {
            return;
        }
        //扣减金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(),(accountChangeEvent.getAmount() * -1));
        //添加记录 实现幂等
        accountInfoDao.addTx(accountChangeEvent.getTxNo());
        log.info("bank1结束执行扣款操作");
    }
}
