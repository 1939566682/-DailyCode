package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ItemStockController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 10:56
 */

@RestController
public class ItemStockController {
	
	public static int stock = 10;
	
	@GetMapping("/decr")
	public void decr() throws InterruptedException {
		Thread.sleep(400);
		stock--;
		if (stock < 0) {
			throw new RuntimeException("商品库存不足！");
		}
		System.out.println("扣减库存成功！");
	}
	
}
