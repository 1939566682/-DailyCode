package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.CheckFilter;
import org.springframework.stereotype.Service;

/**
 * ApiKeyCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验客户的ApiKey是否合法
 */

@Slf4j
@Service("apikey")
public class ApiKeyCheckFilter implements CheckFilter {
	
	
	@Override
	public void check(Object obj) {
		log.info("【接口模块 - 校验apikey】  校验ing......");
	}
	
}
