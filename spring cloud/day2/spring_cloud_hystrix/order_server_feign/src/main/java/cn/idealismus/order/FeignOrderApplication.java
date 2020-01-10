package cn.idealismus.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("cn.idealismus.order.entity")
//激活feign
@EnableFeignClients
//激活hystrix
@EnableCircuitBreaker
//激活hystrix的web监控平台
@EnableHystrixDashboard
public class FeignOrderApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApplication.class);
    }
}
