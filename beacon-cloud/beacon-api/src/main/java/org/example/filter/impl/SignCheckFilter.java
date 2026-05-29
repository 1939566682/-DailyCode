package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.springframework.stereotype.Service;

/**
 * SignCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验短信的签名
 */

@Slf4j
@Service("sign")
public class SignCheckFilter implements CheckFilter {
	
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验签名】  校验ing......");
	}
	
}
