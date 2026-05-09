package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * OrderController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-09 20:09
 */

@RestController
public class OrderController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/order/test")
	public String test() {
		// 直接访问库存服务的 /stock/test 接口 获取数据
		String result = restTemplate.getForObject("http://stock:8080/stock/test", String.class);
		
		// 响应数据
		return "Order Test get " + result;
		
	}
}
