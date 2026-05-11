package org.example.controller;

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
	
	// restTemplate 是启动类构建的
	@Autowired
	private RestTemplate restTemplate;
	
	// 获取 Nacos 配置文件中的 info
	@Value("${info:empty}")
	private String info;
	
	@GetMapping("/order/info")
	public String getInfo(){
		return info;
	}
	
	@GetMapping("/order/test")
	public String test() {
		// 直接访问库存服务的 /stock/test 接口 获取数据
		String result = restTemplate.getForObject("http://stock:8080/stock/test", String.class);
		
		// 响应数据
		return "Order Test get " + result;
		
	}
}
