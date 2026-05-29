package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.ApiKeyIllegalException;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验apikey】  校验ing......");
		// 1、基于cacheClient查询客户信息
		Map clientBusiness = beaconCacheClient.hGetAll(CacheConstant.CLIENT_BUSINESS + submit.getApiKey());
		
		// 2、如果为null  直接抛出异常
		if (clientBusiness == null || clientBusiness.isEmpty()) {
			log.info("【接口模块 - 校验apikey】  非法的apiKey = {}", submit.getApiKey());
			throw new ApiKeyIllegalException(ExceptionEnums.ILLEGAL_APIKEY);
		}
		
		// 3、正常封装数据
		submit.setClientId(Long.parseLong(clientBusiness.get("id").toString()));
		log.info("【接口模块 - 校验apikey】  查询到客户信息 clientBusiness = {}", clientBusiness);
	}
	
}
