package cn.idealismus.order.exception;

import cn.idealismus.order.entity.Product;
import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

public class ExceptionUtils {
    /**
     * 静态方法
     *      返回值 : SentinelClientHttpResponse
     *      参数 : request , byte[] , clientRequestException , blockException
     */
    //限流熔断业务逻辑
    public static SentinelClientHttpResponse handlerBlock (HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException blockException) {
        System.err.println("Oops: " + blockException.getClass().getCanonicalName());
        Product product = new Product();
        product.setProductName("限流熔断降级");
        return new SentinelClientHttpResponse(JSON.toJSONString(product));
    }

    //限流熔断业务逻辑
    public static SentinelClientHttpResponse handlerFallback (HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException blockException) {
        System.err.println("fallback: " + blockException.getClass().getCanonicalName());
        Product product = new Product();
        product.setProductName("异常熔断降级");
        return new SentinelClientHttpResponse(JSON.toJSONString(product));
    }
}
