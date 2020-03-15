package cn.idealusmus.stream.channel;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息通道
 */
public interface MyProcessor {
    /**
     * 消息生产者的通道
     */
    String MY_OUTPUT = "my_output";

    @Output("my_output")
    MessageChannel myOutput ();

    /**
     * 消息消费这的通道配置
     */
    String MY_INPUT = "my_input";

    @Input("MY_INPUT")
    SubscribableChannel myInput ();
}
