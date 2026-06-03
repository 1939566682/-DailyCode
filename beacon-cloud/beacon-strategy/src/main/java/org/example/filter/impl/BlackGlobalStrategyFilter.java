package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.ErrorSendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BlackStrategyFilter
 * 黑名单校验
 *
 * @author Yang QingBo
 * @date 2026-06-01 16:25
 * @description
 */

@Slf4j
@Service("blackGlobal")
public class BlackGlobalStrategyFilter implements StrategyFilter {
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private ErrorSendMsgUtil errorSendMsgUtil;
	
	/**
	 * 黑名单的默认value
	 */
	private final String TRUE = "1";
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 全局级别的黑名单校验】  校验ing......");
		// 1、获取发送短信的手机号
		String mobile = submit.getMobile();
		
		// 2、调用redis查询
		String value = beaconCacheClient.getString(CacheConstant.BLACK + mobile);
		
		// 3、如果查询的结果为"1"  代表是黑名单
		if (TRUE.equals(value)) {
			log.info("【策略模块 - 全局级别的黑名单校验】  当前手机号是黑名单！ mobile = {}", mobile);
			submit.setErrorMsg(ExceptionEnums.BLACK_GLOBAL.getMessage() + "mobile = " + mobile);
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.BLACK_GLOBAL);
		}
		// 4、不是1 正常结束
		log.info("【策略模块 - 全局级别的黑名单校验】  校验通过！ mobile = {}", mobile);
	}
}
