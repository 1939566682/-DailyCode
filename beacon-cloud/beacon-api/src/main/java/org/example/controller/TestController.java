package org.example.controller;

import org.example.filter.CheckFilterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author Yang QingBo
 * @date 2026-05-24 16:12
 * @description
 */

@RestController
public class TestController {
	
	@Autowired
	private CheckFilterContext checkFilterContext;
	
//	@GetMapping("/api/test")
//	public void test() {
//		checkFilterContext.check(new Object());
//	}
	
}
