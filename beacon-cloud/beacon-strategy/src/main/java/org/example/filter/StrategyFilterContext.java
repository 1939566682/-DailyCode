package org.example.filter;

import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * StrategyFilterContext
 * 策略模块校验链的执行
 * @author Yang QingBo
 * @date 2026-06-01 16:28
 * @description
 */

@Component
public class StrategyFilterContext {

	// 泛型注入  拿到所有的校验信息
	@Autowired
	private Map<String,StrategyFilter> strategyFilterMap;
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	private final String CLIENT_FILTERS = "clientFilters";
	
	/**
	 * 当前check方法用于管理校验链的顺序
	 * @param submit
	 */
	public void strategy(StandardSubmit submit) {

		// 1、基于Redis获取客户对应的校验信息
		String filters = beaconCacheClient.hGet(CacheConstant.CLIENT_BUSINESS + submit.getApiKey(), CLIENT_FILTERS);
		
		// 2、健壮性校验后  基于,分割遍历
		String[] filterArray;
		if(filters != null && (filterArray = filters.split(",")).length > 0){
			for (String filter : filterArray) {
				// 3、遍历时  从strategyFilterMap获取到需要执行的校验信息  执行
				StrategyFilter strategyFilter = strategyFilterMap.get(filter);
				if (strategyFilter != null) {
					strategyFilter.strategy(submit);
				}
			}
		}
	}
}
