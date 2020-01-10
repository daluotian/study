package cn.idealismus.order.feign;

import cn.idealismus.order.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientCallBack implements ProductFeignClient {
    
    @Override
    public Product findById(Long id) {
        Product product = new Product();
        product.setProductName("触发feign熔断降级");
        return product;
    }
}
