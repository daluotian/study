package cn.idealismus.bank1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@EnableHystrix
@EnableAspectJAutoProxy
@EnableFeignClients("cn.idealismus.bank1.feign")
@ComponentScan({"cn.idealismus.bank1" , "org.dromara.hmily"})
public class Bank1HmilyServer {
    public static void main(String[] args) {
        SpringApplication.run(Bank1HmilyServer.class,args);
    }
}
