package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.constant.SmsConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.ErrorSendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * LimitOneHourStrategyFilter
 *
 * @author Yang QingBo
 * @date 2026-06-04 16:34
 * @description 一小时发送三条的限流规则
 */

@Slf4j
@Service("limitOneHour")
public class LimitOneHourStrategyFilter implements StrategyFilter {
	
	/**
	 * todo 限流逻辑存在并发缺陷 存在误杀  且当分钟限流通过但小时限流未通过时  分钟限流依然会向redis中写入数据
	 */
	
	private final String UTC = "+8";
	private final long ONE_HOUR = 60 * 60 * 1000 - 1;
	private final int RETRY_COUNT = 2;
	private final int LIMIT_HOUR = 3;
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private ErrorSendMsgUtil errorSendMsgUtil;
	
	@Override
	public void strategy(StandardSubmit submit) {
		
		if (submit.getState() != SmsConstant.CAPTCHA_TYPE) return;
		
		// 1、基于submit获取短信的发送时间
		LocalDateTime sendTime = submit.getSendTime();
		// 2、基于LocalDataTime获取到时间的毫秒值
		long sendTimeMilli = sendTime.toInstant(ZoneOffset.of(UTC)).toEpochMilli();
		submit.setOneHourLimitMilli(sendTimeMilli);
		// 3、基于submit获取客户标识以及手机号信息
		Long clientId = submit.getClientId();
		String mobile = submit.getMobile();
		// 4、优先当前短信发送信息插入到Redis的ZSet结构中
		String key = CacheConstant.LIMIT_HOURS + clientId + CacheConstant.SEPARATE + mobile;
		// 5、如果插入失败  需要重新的毫秒值做改变  尝试重新插入
		int retry = 0;
		while (!beaconCacheClient.zAddLong(key, submit.getOneHourLimitMilli(), submit.getOneHourLimitMilli())) {
			// 发送失败  尝试重试
			retry++;
			if(retry > RETRY_COUNT) break;
			// 插入失败  因为存储的member不允许重复  既然重复了  将时间向后移动  移动到当前系统时间
			submit.setOneHourLimitMilli(System.currentTimeMillis());
		}
		// retry > 2 代表已经重试了两次  发送了三次  但是依然没有成功
		if (retry > RETRY_COUNT) {
			log.info("【策略模块 - 一小时限流策略】   插入失败，一小时内已存在三条记录，拒绝发送。clientId={}, mobile={}, sendTime={}",
					clientId, mobile, submit.getOneHourLimitMilli());
			submit.setErrorMsg(ExceptionEnums.ONE_HOUR_LIMIT.getMessage() + "mobile = " + mobile + "sendTime = " + submit.getOneHourLimitMilli());
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.ONE_MINUTE_LIMIT);
		}
		// 没有重试两次  三次之内  将数据正常的插入了  基于zRangeByScope做范围查询
		long start = submit.getOneHourLimitMilli() - ONE_HOUR;
		Integer count = beaconCacheClient.zRangeByScoreCount(key, start, submit.getOneHourLimitMilli());
		if (count > LIMIT_HOUR) {
			// 一小时内发送过三条短信  限流规则生效
			log.info("【策略模块 - 一小时限流策略】   查询到{}条记录，满足限流规则，拒绝发送。clientId={}, mobile={}, sendTime={}",
					count, clientId, mobile, submit.getOneHourLimitMilli());
			beaconCacheClient.zRemove(key,submit.getOneHourLimitMilli());
			submit.setErrorMsg(ExceptionEnums.ONE_HOUR_LIMIT.getMessage() + "mobile = " + mobile);
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.ONE_HOUR_LIMIT);
		}
		log.info("【策略模块 - 一小时限流策略】   校验通过，允许发送。clientId={}, mobile={}, sendTime={}",
				clientId, mobile, submit.getOneHourLimitMilli());
	}
}
