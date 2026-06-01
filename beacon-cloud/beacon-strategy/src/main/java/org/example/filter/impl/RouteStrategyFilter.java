package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * BlackStrategyFilter
 * 路由策略：选择合适的运营商通道
 * @author Yang QingBo
 * @date 2026-06-01 16:25
 * @description
 */

@Slf4j
@Service("route")
public class RouteStrategyFilter implements StrategyFilter {
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 路由策略】  校验ing......");
	}
}
