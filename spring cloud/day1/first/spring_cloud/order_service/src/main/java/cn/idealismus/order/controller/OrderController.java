package cn.idealismus.order.controller;

import cn.idealismus.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 注入 restTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     *  注入 DiscoveryClient
     *  spring cloud 提供的获取原数组的工具类
     *      调用方法获取服务的原数据
     *      
     *      这里注意：包是spring cloud的，不是 netflix 的
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     *
     * 参数，商品ID
     *  通过订单系统，调用商品服务 根据ID 查询对象
     *
     *  开始基于ribbon形式调用远程微服务
     *  1.使用 @LoadBalanced 声明 RestTemplate
     *  2.使用服务名替换ip地址
     */
    
    @RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById (@PathVariable Integer id) {
        //调用 discoveryClient的方法
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-product");

        ServiceInstance server = serviceInstances.get(0);
        //如何调用商品的服务呢？
        return restTemplate.getForObject("http://service-product/product/"+id,Product.class);
    }
    
    
    /**
     * 
     * 参数，商品ID
     *  通过订单系统，调用商品服务 根据ID 查询对象
     */
    /*@RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
    public Product findById (@PathVariable Integer id) {
        //调用 discoveryClient的方法
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-product");

        for (ServiceInstance server : serviceInstances) {
            System.out.println(server);
        }
        
        ServiceInstance server = serviceInstances.get(0);
        //如何调用商品的服务呢？
        return restTemplate.getForObject("http://"+ server.getHost() + server.getPort() +"/product/"+id,Product.class);
    }*/
    
}
