package cn.idealismus.order.controller;


import cn.idealismus.order.entity.Product;
import cn.idealismus.order.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
	public Product findOrder(@PathVariable Long id) {
		return productFeignClient.findById(id);
	}
	
	
}
