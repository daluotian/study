package cn.idealismus.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.引入依赖
 * 2.配置application.yml
 * 3.配置一个通道的接口
 *      内置获取消息的通道接口 sink
 * 4.绑定通道
 * 5.配置一个监听方法： 当程序从中间件获取数据之后，自行业务逻辑的方法
 *      需要在监听方法上加注解 @StreamListener
 *      
 *      注意 这里虽然写在启动类上 但是@EnableBinding(Sink.class) 与 @StreamListener(Sink.INPUT) 应当写在同一个类中
 */

@SpringBootApplication
public class ConsumerApplication{
    
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }

    /*@StreamListener(Sink.INPUT)
    public void input (String message) {

        System.out.println("获取道消息" + message);
    }*/
}
