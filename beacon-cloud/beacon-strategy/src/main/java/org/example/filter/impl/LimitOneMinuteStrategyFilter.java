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

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * LimitOneMinuteStrategyFilter
 *
 * @author Yang QingBo
 * @date 2026-06-04 16:34
 * @description 一分钟发送一条的限流规则
 */

@Slf4j
@Service("limitOneMinute")
public class LimitOneMinuteStrategyFilter implements StrategyFilter {
	
	/**
	 * todo 限流逻辑存在并发缺陷 存在误杀
	 * 此处限流采用的是先插入当前消息  然后再统计60秒内的记录数
	 * 虽然避免了先插入后查询时可能同时插入两条的情况
	 * 但也导致在高并发时  若两个请求 A、B 几乎同时到达（时间戳不同） 它们都会插入成功 然后各自查询到的 count 都可能是 2 于是两个请求都被拒绝
	 * 实际上按照60秒内只允许一条的规则 应该放行第一条 拒绝第二条  现在的结果是两条都被拦 用户收不到短信
	 */
	
	private final String UTC = "+8";
	private final long ONE_MINUTE = 60 * 1000 - 1;
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private ErrorSendMsgUtil errorSendMsgUtil;
	
	@Override
	public void strategy(StandardSubmit submit) {
		// 1、基于submit获取短信的发送时间
		LocalDateTime sendTime = submit.getSendTime();
		// 2、基于LocalDataTime获取到时间的毫秒值
		long sendTimeMilli = sendTime.toInstant(ZoneOffset.of(UTC)).toEpochMilli();
		// 3、基于submit获取客户标识以及手机号信息
		Long clientId = submit.getClientId();
		String mobile = submit.getMobile();
		// 4、优先当前短信发送信息插入到Redis的ZSet结构中
		String key = CacheConstant.LIMIT_MINUTES + clientId + CacheConstant.SEPARATE + mobile;
		Boolean addResult = beaconCacheClient.zAddLong(key, sendTimeMilli, sendTimeMilli);
		// 5、如果插入失败  直接结束  有并发情况  60秒不能插入两条数据  直接结束
		if (!addResult) {
			log.info("【策略模块 - 一分钟限流策略】   插入失败，60秒内已存在记录，拒绝发送。clientId={}, mobile={}, sendTime={}",
					clientId, mobile, sendTimeMilli);
			submit.setErrorMsg(ExceptionEnums.ONE_MINUTE_LIMIT.getMessage() + "mobile = " + mobile + "sendTime = " + sendTimeMilli);
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.ONE_MINUTE_LIMIT);
		}
		// 6、基于ZRangeByScore查询一分钟之内是否只有当前查询的发送短信信息
		long start = sendTimeMilli - ONE_MINUTE;
		Integer count = beaconCacheClient.zRangeByScoreCount(key, start, sendTimeMilli);
		
		// 7、如果大于等于两条短信信息  达到了60秒一条的短信限流规则  直接结束
		if (count > 1) {
			// 一分钟内发送过短信  限流规则生效
			log.info("【策略模块 - 一分钟限流策略】   查询到{}条记录，满足限流规则，拒绝发送。clientId={}, mobile={}, sendTime={}",
					count, clientId, mobile, sendTimeMilli);
			beaconCacheClient.zRemove(key,sendTimeMilli);
			submit.setErrorMsg(ExceptionEnums.ONE_MINUTE_LIMIT.getMessage() + "mobile = " + mobile);
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.ONE_MINUTE_LIMIT);
		}
		log.info("【策略模块 - 一分钟限流策略】   校验通过，允许发送。clientId={}, mobile={}, sendTime={}",
				clientId, mobile, sendTimeMilli);
	}
}
