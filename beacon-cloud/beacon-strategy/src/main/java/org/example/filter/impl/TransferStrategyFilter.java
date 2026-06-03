package org.example.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransferStrategyFilter
 * @author Yang QingBo
 * @date 2026-06-01 16:25
 * @description 携号转网策略
 */

@Slf4j
@Service("transfer")
public class TransferStrategyFilter implements StrategyFilter {
	
	/**
	 * 代表携号转网了
	 */
	private final Boolean TRANSFER = true;
	
	@Autowired
	private BeaconCacheClient  beaconCacheClient;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 携号转网策略】  校验ing......");
		// 1、获取用户手机号
		String mobile = submit.getMobile();
		
		// 2、直接基于redis查询携号转网信息
		String value = beaconCacheClient.getString(CacheConstant.TRANSFER + mobile);
		
		// 3、如果存在携号转网  设置运营商信息
		if (!StringUtils.isEmpty(value)) {
			// 代表携号转网了
			submit.setOperatorId(Integer.parseInt(value));
			submit.setTransfer(TRANSFER);
			log.info("【策略模块 - 携号转网策略】  当前手机号携号转网了！ mobile = {}", mobile);
		}else {
			log.info("【策略模块 - 携号转网策略】  当前手机号未进行携号转网！ mobile = {}", mobile);
		}
	}
}
