package cn.idealismus.stream.comsumer;

import cn.idealismus.stream.channel.MyProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MyProcessor.class)
public class MessageListener {

    @StreamListener(MyProcessor.MY_INPUT)
    public void input (String message) {
        System.out.println("获取道消息" + message);
    }
}
