package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderManangeController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 10:59
 */

@RestController
public class OrderManangeController {
	
	@GetMapping("/create")
	public void create() throws InterruptedException {
		Thread.sleep(400);
		System.out.println("创建订单成功！");
	}
	
}
