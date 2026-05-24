package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.CheckFilter;
import org.springframework.stereotype.Service;

/**
 * FeeCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验客户剩余的金额是否充足
 */

@Slf4j
@Service("fee")
public class FeeCheckFilter implements CheckFilter {
	
	
	@Override
	public void check(Object obj) {
		log.info("【接口模块 - 校验客户余额】  校验ing......");
	}
	
}
