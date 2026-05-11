package org.example.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-11 18:57
 */

@Service
public class OrderServiceImpl implements OrderService {
	
	@Override
	@SentinelResource("common")
	public String common() {
		return " common ";
	}
}
