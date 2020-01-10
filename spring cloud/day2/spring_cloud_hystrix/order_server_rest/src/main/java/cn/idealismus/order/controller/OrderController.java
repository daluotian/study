package cn.idealismus.order.controller;

import cn.idealismus.order.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order")
/**
 * @DefaultProperties : 指定此接口中公共的熔断方法
 * 如果在@DefaultProperties指定了公共的熔断方法
 * 那么在@HystrixCommand中就不需要特别指定熔断方法
 */
@DefaultProperties(defaultFallback = "defaultFallBack")
public class OrderController {

    /**
     * 注入 restTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     * 参数，商品ID
     *  通过订单系统，调用商品服务 根据ID 查询对象
     *
     *  开始基于ribbon形式调用远程微服务
     *  1.使用 @LoadBalanced 声明 RestTemplate
     *  2.使用服务名替换ip地址
     */
    /**
     * 使用注解配置熔断保护 @HystrixCommand
     *  fallbackMethod : 配置熔断之后的降级方法
     * @param id
     * @return
     */
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    @HystrixCommand()
    public Product findById (@PathVariable Integer id) {
        //如何调用商品的服务呢？
        return restTemplate.getForObject("http://service-product/product/"+id,Product.class);
    }

    /**
     * 降级方法
     * 和需要保护的方法的返回值一致
     * 方法参数一致
     * @return
     */
    public Product orderFallBack (Integer id) {
        Product product = new Product();
        product.setProductName("触发降级");
        return product;
    }

    /**
     * 指定统一的降级方法
     * 参数 : 没有参数
     */
    public Product defaultFallBack () {
        Product product = new Product();
        product.setProductName("触发统一的降级方法");
        return product;
    }
}
