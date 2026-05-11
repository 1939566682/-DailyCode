package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StockController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-09 20:07
 */

@RestController
public class StockController {
	
	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/stock/test")
	public String test(){
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		return "stock test!" + serverPort;
	}
}
