package cn.idealismus.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("cn.idealismus.order.entity")
@EnableEurekaClient
public class OrderApplication {
    /**
     * 使用spring提供的RestTemplate发送http请求到商品服务
     *  1.创建RestTemplate对象交给spring容器管理
     *  2.在使用的时候，调用其相应的方法即可
     */
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);   
    }
}
