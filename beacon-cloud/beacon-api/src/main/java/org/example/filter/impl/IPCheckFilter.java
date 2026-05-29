package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.springframework.stereotype.Service;


/**
 * IPCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验请求的ip地址是否是白名单
 */

@Slf4j
@Service("ip")
public class IPCheckFilter implements CheckFilter {
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验ip】  校验ing......");
	}
	
}
