package cn.idealusmus.stream;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }
}
