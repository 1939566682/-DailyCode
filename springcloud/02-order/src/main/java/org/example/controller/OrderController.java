package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.client.StockClient;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class OrderController {
	
	@Autowired
	private StockClient stockClient;
	
	// 基于 openFeign 尝试访问Stock服务
	@GetMapping("/order/feign")
	public String feign() {
		return stockClient.test();
	}
	
	
	// restTemplate 是启动类构建的
	@Autowired
	private RestTemplate restTemplate;
	
	// 获取 Nacos 配置文件中的 info
	@Value("${info:empty}")
	private String info;
	
	@GetMapping("/order/info")
	@SentinelResource(value = "info")
	public String getInfo() {
		return info;
	}
	
	//	===================关联==========================
	@GetMapping("/order/add")
	@SentinelResource(value = "add")
	public String add() {
		return "add";
	}
	
	//	===================链路==========================
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order/aaa")
	@SentinelResource(value = "aaa")
	public String aaa() {
		orderService.common();
		return "aaa";
	}
	
	@GetMapping("/order/bbb")
	@SentinelResource(value = "bbb")
	public String bbb() {
		orderService.common();
		return "bbb";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/order/test")
	public String test() {
		// 直接访问库存服务的 /stock/test 接口 获取数据
		String result = restTemplate.getForObject("http://stock:8080/stock/test", String.class);
		
		// 响应数据
		return "Order Test get " + result;
		
	}
}
