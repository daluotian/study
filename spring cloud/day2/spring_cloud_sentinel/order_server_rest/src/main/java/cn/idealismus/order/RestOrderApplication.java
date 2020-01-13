package cn.idealismus.order;

import cn.idealismus.order.exception.ExceptionUtils;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("cn.idealismus.order.entity")
//激活hystrix
@EnableCircuitBreaker
public class RestOrderApplication {
    /**
     * @LoadBalanced 负载均衡支持
     * @SentinelRestTemplate
     *      restTemplate对象再调用方法时，会默认支持sentinel
     *      异常降级
     *          fallback : 降级方法
     *          fallbackClass : 降级配置类
     *      限流熔断
     *          blockHandler
     *          blockHandlerClass
     * 资源名
     *      httpmethod:schema://host:port/path 协议，主机，端口，路径
     *      httpmethod:schema://host:pott 协议，主机，端口
     *      
     * @return
     */
    @LoadBalanced
    @SentinelRestTemplate(fallbackClass = ExceptionUtils.class,fallback = "handlerFallback",
            blockHandler = "handlerBlock",blockHandlerClass = ExceptionUtils.class
    )
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(RestOrderApplication.class);
    }
    
}
