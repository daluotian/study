package cn.idealismus.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//使用注解的方式开启eurekaService
@EnableEurekaServer
public class EurekaApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);   
    }
}
