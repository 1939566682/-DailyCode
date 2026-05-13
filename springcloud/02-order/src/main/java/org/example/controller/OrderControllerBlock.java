package org.example.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;

/**
 * OrderControllerBlock
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-12 22:44
 */

public class OrderControllerBlock {
	
	// 是sentinel的降级方法  可以在方法逻辑中返回降级数据
	public static String sentinelBlock(String value, BlockException exception){
		String message = null;
		if (exception instanceof FlowException){
			message = "流量控制！";
		} else if (exception instanceof DegradeException) {
			message = "熔断控制";
		}
		return "failed msg : " + message;
		
	}

}
