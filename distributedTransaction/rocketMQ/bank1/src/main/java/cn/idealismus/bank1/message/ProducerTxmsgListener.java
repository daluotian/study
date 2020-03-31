package cn.idealismus.bank1.message;

import cn.idealismus.bank1.dao.AccountInfoDao;
import cn.idealismus.bank1.model.AccountChangeEvent;
import cn.idealismus.bank1.serice.AccountInfoService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "product_group_txmsg_bank1")
public class ProducerTxmsgListener implements RocketMQLocalTransactionListener {
    @Autowired
    private AccountInfoService accountInfoService;
    
    @Autowired
    private AccountInfoDao accountInfoDao;
    
    //事务发送消息的成功的回调
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {           
            log.info("bank1发送消息成功，开始执行回调");
            //解析 message， 转换成accountEvertServer;
            String s = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(s);
            String accountChange = jsonObject.getString("accountChange");
            //将accountChange(json)转换成accountChange
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChange, AccountChangeEvent.class);
            //执行扣减金额
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);
            //当返回Commit 表示本地事务执行成功
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //事务状态回查，查询是否扣减金额
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("bank1发送消息成功，回查");
        String s = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(s);
        String accountChange = jsonObject.getString("accountChange");
        //将accountChange(json)转换成accountChange
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChange, AccountChangeEvent.class);
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo()) > 1) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
