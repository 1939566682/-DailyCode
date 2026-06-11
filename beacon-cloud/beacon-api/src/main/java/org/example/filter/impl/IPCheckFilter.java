package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


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
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	private final String IP_ADDRESS = "ipAddress";
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验ip】  校验ing......");
		
		// 1、通过 BeaconCacheClient 根据客户的apikey以及ipAddress查询客户的IP白名单
		List<String> ip = beaconCacheClient.hGetStringList(CacheConstant.CLIENT_BUSINESS + submit.getApiKey(), IP_ADDRESS);
		submit.setIp(ip);
		
		// 2、如果IP白名单为null或者在白名单内  直接放行
		if (CollectionUtils.isEmpty(ip) || ip.contains(submit.getRealIP())) {
			log.info("【接口模块 - 校验ip】  客户端请求IP合法！");
			return;
		}
		
		// 3、IP白名单为null  且当前客户端请求的IP地址不在IP白名单
		log.info("【接口模块 - 校验ip】  请求单IP不在白名单内");
		throw new ApiIllegalException(ExceptionEnums.IP_NOT_IN_WHITELIST);
		
	}
	
}
