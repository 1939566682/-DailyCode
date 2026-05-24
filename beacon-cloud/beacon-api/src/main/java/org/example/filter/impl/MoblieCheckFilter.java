package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.CheckFilter;
import org.springframework.stereotype.Service;

/**
 * MoblieCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验手机号的格式合法性
 */

@Slf4j
@Service("mobile")
public class MoblieCheckFilter implements CheckFilter {
	
	
	@Override
	public void check(Object obj) {
		log.info("【接口模块 - 校验手机号】  校验ing......");
	}
	
}
