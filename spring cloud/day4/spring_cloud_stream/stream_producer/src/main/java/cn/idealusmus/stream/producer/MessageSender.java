package cn.idealusmus.stream.producer;

import cn.idealusmus.stream.channel.MyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 专门负责消息发送的类
 */
@EnableBinding(MyProcessor.class)
@Component
public class MessageSender {
    @Autowired
    @Qualifier("my_output")
    private MessageChannel my_output;
    
    public void Send (Object object) {
        my_output.send(MessageBuilder.withPayload(object).build());
    }
    
}
