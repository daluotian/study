package cn.idealismus.order.feign;

import cn.idealismus.order.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 声明需要调用的微服务的名称
 * @FrignClient
 *  name ： 服务提供者的名称
 *  fallBack ： 配置熔断发生降级方法的实践类
 */
@FeignClient(name="service-product",fallback = ProductFeignClientCallBack.class)
public interface ProductFeignClient {

    /**
     * 配置需要调用的微服务接口
     */
    @RequestMapping(value="/product/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable("id") Long id);
}
