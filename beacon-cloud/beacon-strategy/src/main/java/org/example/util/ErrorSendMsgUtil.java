package org.example.util;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.constant.RabbitMQConstant;
import org.example.constant.SmsConstant;
import org.example.enums.ExceptionEnums;
import org.example.model.StandardReport;
import org.example.model.StandardSubmit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ErrorSendMsgUtil
 *
 * @author Yang QingBo
 * @date 2026-06-03 18:46
 * @description
 */

@Component
public class ErrorSendMsgUtil {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	/**
	 * 策略模块校验未通过  发送写日志操作
	 * @param submit
	 */
	public void sendWriteLog(StandardSubmit submit) {
		submit.setReportState(SmsConstant.REPORT_FAIL);
		// 发送消息到写日志队列
		rabbitTemplate.convertAndSend(RabbitMQConstant.SMS_WRITE_LOG, submit);
	}
	
	/**
	 * 策略模块校验未通过  发送状态报告操作
	 * @param submit
	 */
	public void sendPushReport(StandardSubmit submit) {
		// 查看当前客户的isCallBack
		Integer isCallback = beaconCacheClient.hGetInteger(CacheConstant.CLIENT_BUSINESS + submit.getApiKey(), "isCallback");
		// 查看是否需要给客户一个回调
		if (isCallback == 1) {
			// 如果需要回调  再查询客户的回调地址
			String callbackUrl = beaconCacheClient.hGet(CacheConstant.CLIENT_BUSINESS + submit.getApiKey(), "callbackUrl");
			// 如果回调地址不为空
			if (callbackUrl != null && !StringUtils.isEmpty(callbackUrl)) {
				// 封装客户的报告推送的信息  开始封装StandardReport
				StandardReport report = new StandardReport();
				BeanUtils.copyProperties(submit, report);
				report.setIsCallback(isCallback);
				report.setCallbackUrl(callbackUrl);
				rabbitTemplate.convertAndSend(RabbitMQConstant.SMS_PUSH_REPORT, report);
			}
		}
	}
}
