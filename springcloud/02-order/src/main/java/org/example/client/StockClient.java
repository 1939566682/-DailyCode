package org.example.client;


import org.example.client.fallback.StockClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * StockClient
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-11 11:58
 */

/**
 * StockClient 和 StockClientFallback 都交给了 Spring容器管理
 * 那么是怎么判断注入睡呢？
 * 通过 @FeignClient 的 primary 属性
 * 默认为 true  指定 Feign 接口的代理对象作为注入主要对象
 * 当设置 primary = false 时
 * spring容器就会不知道该注入谁
 * 报错：
 * Field stockClient in org.example.controller.OrderController required a single bean, but 2 were found
 */
@FeignClient(value = "stock",fallback = StockClientFallback.class)
public interface StockClient {
	
	@GetMapping("/stock/test")
	String test();
	
}
