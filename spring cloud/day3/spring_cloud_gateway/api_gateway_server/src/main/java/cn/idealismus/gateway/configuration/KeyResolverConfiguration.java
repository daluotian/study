package cn.idealismus.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Configuration
public class KeyResolverConfiguration {
    
    /**
     * 编写基于请求路径的限流规则
     * 其他的
     * //基于IP
     * //基于参数
     */
    @Bean
    public KeyResolver pathKeyResolver () {
        return new KeyResolver() {
            /**
             * ServerWebExchange 上下文参数
             * @param exchange
             * @return
             */
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getPath().toString());
            }
        };
    }

    /**
     * 基于参数的限流
     * 
     * 基于ip限流
     */
    @Bean
    public KeyResolver userKeyResolver () {
        return exchange -> Mono.just(
            //exchange.getRequest().getQueryParams().getFirst("userId")
            exchange.getRequest().getHeaders().getFirst("X-Forwarded-For")
        );
    }
}
