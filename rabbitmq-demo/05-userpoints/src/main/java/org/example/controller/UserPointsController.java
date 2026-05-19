package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserPointsController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 11:03
 */

@RestController
public class UserPointsController {
	
	@GetMapping("/up")
	public void up() throws InterruptedException {
		Thread.sleep(400);
		System.out.println("扣除用户积分成功！！");
	}
	
}
