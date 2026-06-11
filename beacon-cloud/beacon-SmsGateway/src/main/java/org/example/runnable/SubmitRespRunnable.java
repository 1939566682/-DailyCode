package org.example.runnable;

import org.example.constant.RabbitMQConstant;
import org.example.constant.SmsConstant;
import org.example.enums.CMPP2ResultEnums;
import org.example.model.StandardReport;
import org.example.model.StandardSubmit;
import org.example.netty4.entity.CmppSubmitResp;
import org.example.util.CMPP2ResultRespUtil;
import org.example.util.CMPPDeliverMapUtil;
import org.example.util.CMPPSubmitRespMapUtil;
import org.example.util.SpringUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;

/**
 * SubmitRespRunnable
 *
 * @author Yang QingBo
 * @date 2026-06-10 22:33
 * @description
 */

public class SubmitRespRunnable implements Runnable {
	
	private RabbitTemplate rabbitTemplate = SpringUtil.getBean(RabbitTemplate.class);
	
	private CmppSubmitResp submitResp;
	
	public SubmitRespRunnable(CmppSubmitResp submitResp) {
		this.submitResp = submitResp;
	}
	
	@Override
	public void run() {
		StandardReport report = null;
		// 1、拿到自增id 并且从ConcurrentHashMap中获取到存储的submit
		StandardSubmit submit = CMPPSubmitRespMapUtil.remove(submitResp.getSequenceId());
		
		// 2、根据运营商返回的submit  确认短信状态并封装submit
		int result = submitResp.getResult();

		// TODO 此处获取的result是运营商返回的submit的result  不应该用短信平台的消息状态码来进行判断  不影响结果但可能导致误解  （不确定  回头再看）
//			if (result != OK) {
		if (result != CMPP2ResultEnums.OK.getResult()) {
			// 说明运营商的提交应答中回馈了失败的情况
			String resultMessage = CMPP2ResultRespUtil.getResultMessage(result);
			submit.setReportState(SmsConstant.REPORT_FAIL);
			submit.setErrorMsg(resultMessage);
		} else {
			// 如果没进到if中  说明运营商已经正常的接受了发送短信的任务  继续完成操作三
			// 3、将submit封装为report临时存储  以便于运营商返回状态码时可以再次获取到信息
			// 这里没有对其他信息进行封装
			report = new StandardReport();
			BeanUtils.copyProperties(submit, report);
			CMPPDeliverMapUtil.put(String.valueOf(submitResp.getMsgId()),report);
		}
		
		// 4、将封装好的submit直接扔到RabbitMQ中  让搜索模块记录
		rabbitTemplate.convertAndSend(RabbitMQConstant.SMS_WRITE_LOG,submit);
	}
}
