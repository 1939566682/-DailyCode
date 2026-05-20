package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.TBOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderManangeController
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 10:59
 */

@Slf4j
@RestController
public class OrderManangeController {
	
	@Autowired
	private TBOrderService tbOrderService;
	
	@GetMapping("/create")
	public void create() {
		tbOrderService.save();
		log.info("log() create - 创建订单成功！！！");
	}
	
}
