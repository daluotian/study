package cn.idealismus.bank1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("cn.idealismus.bank1.feign")
@EnableHystrix
public class Bank1Service {
    public static void main(String[] args) {
        SpringApplication.run(Bank1Service.class,args);
    }
}
