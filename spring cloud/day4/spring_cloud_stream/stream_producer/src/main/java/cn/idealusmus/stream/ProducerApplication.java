package cn.idealusmus.stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 1.引入案例
 * 2.配置application.yml
 * 3.发送消息，定义一个通道接口，通过通道接口中内置的messageChannel
 *      spring cloud stream 中内置接口 Source
 * 4.@EnableBinding(): 绑定对应的通道
 * 5.发送消息的话 通过MessageChannel发送消息 
 *      如果需要messageChannel 通过绑定的内置接口获取
 */

@SpringBootApplication
@EnableBinding(Source.class)
public class ProducerApplication implements CommandLineRunner {
    @Autowired
    private MessageChannel output;

    @Override
    public void run(String... args) throws Exception {
        //发送消息
        //messageBuilder创建消息
        
        output.send(MessageBuilder.withPayload("hello idealusmus").build());
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }
}
