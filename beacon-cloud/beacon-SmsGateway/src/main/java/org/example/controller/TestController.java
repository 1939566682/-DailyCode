package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-06-10 20:30
 * @description
 */

@RestController
public class TestController {
	
	@Autowired
	private ThreadPoolExecutor cmppSubmitPool;
	
	@GetMapping("/test")
	public String test() {
		cmppSubmitPool.execute(() -> {
			System.out.println(Thread.currentThread().getName());
		});
		return "success";
	}
	
}
