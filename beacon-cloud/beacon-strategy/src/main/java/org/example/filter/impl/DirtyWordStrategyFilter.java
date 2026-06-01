package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * PhaseStrategyFilter
 * 敏感词校验
 * @author Yang QingBo
 * @date 2026-06-01 16:23
 * @description
 */

@Slf4j
@Service(value = "dirtyword")
public class DirtyWordStrategyFilter implements StrategyFilter {
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 敏感词校验】  校验ing......");
	}
}
