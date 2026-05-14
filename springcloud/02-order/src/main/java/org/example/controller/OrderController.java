package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.example.client.StockClient;
import org.example.dto.ParamDTO;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * OrderController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-09 20:09
 */

@RestController
@RefreshScope
public class OrderController {
	
	//	===================OpenFeign访问服务==========================
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
	
	
	public String aaaFallBack() {
		return "托底数据 - 服务器正忙 请稍后再试~";
	}
	
	@GetMapping("/order/bbb")
	@SentinelResource(value = "bbb")
	public String bbb() {
		orderService.common();
		return "bbb";
	}
	
	//	===================熔断降级==========================
	
	@GetMapping("/order/circuitBreaker")
	@SentinelResource(value = "circuitBreaker")
	public String circuitBreaker(String value) throws InterruptedException {
		switch (value) {
			case "1":
				Thread.sleep(1000);
				break;
			case "2":
				int i = 1 / 0;
		}
		return "circuitBreaker  --  success!!";
	}
	
	
	//	===================@Sentinel==========================
	@GetMapping("/order/sentinel")
//	@SentinelResource(value = "sentinel",blockHandlerClass = OrderControllerBlock.class,blockHandler = "sentinelBlock")
//	@SentinelResource(value = "sentinel",fallback = "sentinelFallback",blockHandler = "sentinelBlock")
	@SentinelResource(value = "sentinel", defaultFallback = "defaultFallback", exceptionsToIgnore = ArithmeticException.class)
	public String sentinel(String value) throws InterruptedException {
		switch (value) {
			case "1":
				Thread.sleep(1000);
				break;
			case "2":
				int i = 1 / 0;
		}
		return "sentinel  --  success!!";
	}
	
	public String sentinelFallback(String value, Throwable ex) {
		return "failed";
	}
	
	
	// 是sentinel的降级方法  可以在方法逻辑中返回降级数据
	public String sentinelBlock(String value, BlockException exception) {
		String message = null;
		if (exception instanceof FlowException) {
			message = "流量控制！";
		} else if (exception instanceof DegradeException) {
			message = "熔断控制";
		}
		return "failed msg : " + message;
		
	}
	
	//	===================默认的fallback==========================
	public String defaultFallback(String value, Throwable ex) {
		return "failed" + ex.getMessage();
	}
	
	//	===================热点规则==========================
	@GetMapping("/order/hot")
	@SentinelResource(value = "hot")
	public String hot(ParamDTO paramDTO) {
		return "userId: " + paramDTO.getUserId() + " " + "type: " + paramDTO.getType();
	}
	
	
	//	===================授权规则==========================
	@GetMapping("/order/author")
	@SentinelResource(value = "author")
	public String author() {
		return "author!";
	}
	
	
	//	===================Nacos注册中心==========================
	@GetMapping("/order/test")
	public String test() {
		// 直接访问库存服务的 /stock/test 接口 获取数据
		String result = restTemplate.getForObject("http://stock:8080/stock/test", String.class);
		
		// 响应数据
		return "Order Test get " + result;
		
	}
	
	//  =========================Gateway的Filter测试========================================
	@GetMapping("/order/gateway")
	public String gateway(HttpServletRequest request) {
		
		return null;
	}
}
