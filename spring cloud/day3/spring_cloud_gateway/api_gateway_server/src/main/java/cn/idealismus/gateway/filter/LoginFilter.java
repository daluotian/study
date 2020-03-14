package cn.idealismus.gateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义一个gateway全局过滤器
 * 实现两个接口
 * GlobalFilter, Ordered
 */
//@Component
public class LoginFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器中的逻辑
     * 
     * 队请求参数中的access-token进行判断
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("执行了自定义的全局过滤器");
        //继续向下执行
        String token = exchange.getRequest().getQueryParams().getFirst("access-token");
        if (token == null) {
            System.out.println("未认证");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            //请求结束
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }

    /**
     * 指定过滤器执行顺序，返回值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
