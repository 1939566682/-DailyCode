package org.example.runnable;


import com.alibaba.cloud.commons.lang.StringUtils;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.constant.RabbitMQConstant;
import org.example.constant.SmsConstant;
import org.example.enums.CMPP2DeliverEnums;
import org.example.model.StandardReport;
import org.example.util.CMPP2DeliverRespUtil;
import org.example.util.CMPPDeliverMapUtil;
import org.example.util.SpringUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.ObjectUtils;

/**
 * DeliverRespRunnable
 *
 * @author Yang QingBo
 * @date 2026-06-11 15:34
 * @description
 */

public class DeliverRespRunnable implements Runnable {
	
	private final RabbitTemplate rabbitTemplate = SpringUtil.getBean(RabbitTemplate.class);
	
	private final BeaconCacheClient beaconCacheClient = SpringUtil.getBean(BeaconCacheClient.class);
	
	private Long msgId;
	private String stat;
	
	public DeliverRespRunnable(Long msgId, String stat) {
		this.msgId = msgId;
		this.stat = stat;
	}
	
	
	@Override
	public void run() {
		// 1、基于msgId拿到临时存储的report对象
		StandardReport report = CMPPDeliverMapUtil.remove(String.valueOf(msgId));
		
		// 2、确认当前短信发送的最终状态
		if(!ObjectUtils.isEmpty(stat) && stat.equals(CMPP2DeliverEnums.DELIVERED.getStat())){
			// 短信发送成功
			report.setReportState(SmsConstant.REPORT_SUCCESS);
		}else{
			// 短信发送失败
			report.setReportState(SmsConstant.REPORT_FAIL);
			report.setErrorMsg(CMPP2DeliverRespUtil.getResultMessage(stat));
		}
		
		// 3、客户状态报告推送  让网关模块查询缓存  当前客户是否需要状态报告推送
		// 查看当前客户的isCallBack
		Integer isCallback = beaconCacheClient.hGetInteger(CacheConstant.CLIENT_BUSINESS + report.getApiKey(), "isCallback");
		// 查看是否需要给客户一个回调
		if (isCallback == 1) {
			// 如果需要回调  再查询客户的回调地址
			String callbackUrl = beaconCacheClient.hGet(CacheConstant.CLIENT_BUSINESS + report.getApiKey(), "callbackUrl");
			// 如果回调地址不为空
			if (callbackUrl != null && !StringUtils.isEmpty(callbackUrl)) {
				// 封装客户的报告推送的信息  开始封装StandardReport
				report.setIsCallback(isCallback);
				report.setCallbackUrl(callbackUrl);
				rabbitTemplate.convertAndSend(RabbitMQConstant.SMS_PUSH_REPORT, report);
			}
		}
		
		// 4、再次发送消息  让搜索模块对之前写入的消息进行修改  这里需要用死信队列  延迟10s再发送修改es信息的消息
		
	
	}
}
