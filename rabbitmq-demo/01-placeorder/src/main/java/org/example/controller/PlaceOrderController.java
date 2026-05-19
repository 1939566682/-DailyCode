package org.example.controller;

import org.example.client.*;
import org.example.config.RabbitMQConfig;
import org.example.util.GlobeCache;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * PlaceOrderController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 10:51
 */

@RestController
public class PlaceOrderController {
	
	@Autowired
	private ItemStockClient itemStockClient;
	@Autowired
	private OrderManagerClient orderManagerClient;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping("/po")
	public String po(){
		long start = System.currentTimeMillis();
		// 调用库存服务扣除库存（核心）
		itemStockClient.decr();
		// 调用订单服务创建订单（核心）
		orderManagerClient.create();
		
		String msg = "用户信息&订单信息&...";
		// 声明当前消息的id标识
		String id = UUID.randomUUID().toString();
		// 封装消息的全部信息
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("message",msg);
		map.put("exchange",RabbitMQConfig.PLACE_ORDER_EXCHANGE);
		map.put("routingKey","");
		map.put("sendTime" , new Date());
		// 将id标识和消息存储到全局缓存当中
		GlobeCache.set(id,map);
		// 将同步方式修改为基于RabbitMQ的异步方式
		rabbitTemplate.convertAndSend(RabbitMQConfig.PLACE_ORDER_EXCHANGE,"",msg,new CorrelationData(id));
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		return "place order is ok!";
	}
	
}
