package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BusinessController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 11:04
 */

@RestController
public class BusinessController {
	
	@GetMapping("/notify")
	public void notifyBusiness() throws InterruptedException {
		Thread.sleep(400);
		System.out.println("通知商家成功！！");
	}
	
}
