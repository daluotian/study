package cn.idealismus.bank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@ComponentScan({"cn.idealismus.bank2" , "org.dromara.hmily"})
public class Bank2HmilyServer {
    public static void main(String[] args) {
        SpringApplication.run(Bank2HmilyServer.class,args);
    }
}
