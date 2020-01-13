package cn.idealismus.order.controller;

import cn.idealismus.order.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
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
     *  
     *  @SentinelResource
     *      blockHandler : 声明熔断时调用的降级方法
     *      fallBack : 抛出异常时的降级方法
     *      value : 自定义资源名称
     *          *不设置 : 当前全类名.方法名
     */
    
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    //@SentinelResource(value = "orderFindById",blockHandler = "orderBlockHandler",fallback = "orderFallBack")
    public Product findById (@PathVariable Integer id) {
        if (id != 1) {
            throw new RuntimeException("ID不为1，服务器返回异常");
        }
        //如何调用商品的服务呢？
        return restTemplate.getForObject("http://service-product/product/"+id,Product.class);
    }

    /**
     * 定义降级逻辑
     * hystrix和sentinel
     *      熔断执行的降级方法
     *      抛出异常执行的降级方法
     */
    /*public Product orderBlockHandler (Integer id) {
        Product product = new Product();
        product.setProductName("触发熔断的降级方法");
        return product;
    }

    public Product orderFallBack (Integer id) {
        Product product = new Product();
        product.setProductName("抛出异常的降级方法");
        return product;
    }*/
}
