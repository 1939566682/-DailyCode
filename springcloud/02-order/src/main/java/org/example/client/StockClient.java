package org.example.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * StockClient
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-11 11:58
 */

@FeignClient("stock")
public interface StockClient {
	
	@GetMapping("/stock/test")
	String test();
	
}
