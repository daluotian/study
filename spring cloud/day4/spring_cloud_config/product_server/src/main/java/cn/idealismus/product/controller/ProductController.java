package cn.idealismus.product.controller;

import cn.idealismus.product.entity.Product;
import cn.idealismus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RefreshScope //开启动态刷新
public class ProductController {
    @Autowired
    private ProductService productService;

    @Value("${name}")
    private String name;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @RequestMapping(value = "/test")
    public String test() {
        return name;
    }
}
