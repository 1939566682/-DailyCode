package org.example.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * BlackStrategyFilter
 * 黑名单校验
 * @author Yang QingBo
 * @date 2026-06-01 16:25
 * @description
 */

@Slf4j
@Service("black")
public class BlackStrategyFilter implements StrategyFilter {
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 黑名单校验】  校验ing......");
	}
}
