package cn.idealismus.product.controller;

import cn.idealismus.product.entity.Product;
import cn.idealismus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product finById (@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return product;
    }
    
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String save(@RequestBody Product product){
        productService.save(product);
        return "保存成功";
    }
}
