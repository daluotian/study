package cn.idealismus.order.controller;


import cn.idealismus.order.command.OrderCommand;
import cn.idealismus.order.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private RestTemplate restTemplate;

	/*@RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
	public Product findById(@PathVariable Long id) {
		System.out.println(Thread.currentThread().getName());
		return restTemplate.getForObject("http://127.0.0.1:9001/product/1", Product.class);
	}*/

	/**
	 * 使用OrderCommand调用远程服务
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/buy/{id}",method = RequestMethod.GET)
	public Product findById(@PathVariable Integer id) {
		return new OrderCommand(restTemplate,id).execute();
	}
	

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public String findOrder (@PathVariable Long id) {
		return "查询订单成功";
	}
}
