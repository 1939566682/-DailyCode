package org.example.mq;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitMQConfig;
import org.example.constant.RabbitMQConstant;
import org.example.model.StandardReport;
import org.example.util.JsonUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * PushReportListener
 *
 * @author Yang QingBo
 * @date 2026-06-08 19:25
 * @description
 */

@Slf4j
@Component
public class PushReportListener {
	
	private final String SUCCESS = "SUCCESS";
	
	/**
	 * 重试的时间间隔
	 */
	private final int[] DELAYED_TIME = {0, 15000, 30000, 60000, 300000};
	// 测试时间
//	private final int[] DELAYED_TIME = {0, 150, 300, 600, 3000};
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 监控策略模块推送过来的消息（暂时是策略）
	 * @param report
	 * @param channel
	 * @param message
	 * @throws IOException
	 */
	@RabbitListener(queues = RabbitMQConstant.SMS_PUSH_REPORT)
	public void consume(StandardReport report, Channel channel, Message message) throws IOException {
		// 1、获取客户的回调地址
		String callbackUrl = report.getCallbackUrl();
		if (callbackUrl == null || StringUtils.isEmpty(callbackUrl)) {
			log.error("【推送模块 - 推送状态报告】  客户没有设置回调的地址信息！ report = {}", report);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			return;
		}
		
		// 2、发送状态报告
		boolean flag = pushReport(report);
		
		// 3、如果发送失败  重试
		isResend(report, flag);
		
		// 4、手动ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
	
	/**
	 * 监听延迟交换机路由过来的消息
	 * @param report
	 * @param channel
	 * @param message
	 * @throws IOException
	 */
	@RabbitListener(queues = RabbitMQConfig.DELAYED_QUEUE)
	public void delayedConsume(StandardReport report, Channel channel, Message message) throws IOException {
		// 1、发送状态报告
		boolean flag = pushReport(report);
		
		// 2、判断状态报告发送情况
		isResend(report, flag);
		
		// 手动ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
	
	/**
	 * 发送请求  给callbackUrl
	 *
	 * @param report
	 * @return
	 */
	private boolean pushReport(StandardReport report) {
		// 声明返回结果  默认为false
		boolean flag = false;
		
		// 1、声明发送的参数
		String body = JsonUtil.ObjectToJson(report);
		
		// 2、声明restTemplate的模板代码
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		try {
			log.info("【推送模块 - 推送状态报告】  第{}次推送状态报告开始！ report = {}", report.getResendCount() + 1, report);
			String result = restTemplate.postForObject("https://" + report.getCallbackUrl(), new HttpEntity<>(body, httpHeaders), String.class);
			flag = SUCCESS.equals(result);
		} catch (RestClientException e) {
			// 不做处理
		}
		
		// 3、得到响应后  确定是否为SUCCESS
		return flag;
	}
	
	/**
	 * 判断状态报告是否推送成功  失败则需要发送重试消息
	 * @param report
	 * @param flag
	 */
	private void isResend(StandardReport report, boolean flag) {
		if ((report.getResendCount()+1) >= DELAYED_TIME.length) {
			log.error("【推送模块 - 推送状态报告】  状态报告推送重试次数已达上限，放弃推送。report = {}", report);
			// TODO 可发送到死信队列或直接记录数据库
			return;
		}
		if (!flag) {
			log.error("【推送模块 - 推送状态报告】  isResend  第{}次推送状态报告推送失败！ report = {}", report.getResendCount() + 1, report);
			report.setResendCount(report.getResendCount() + 1);
			rabbitTemplate.convertAndSend(RabbitMQConfig.DELAYED_EXCHANGE, "", report, new MessagePostProcessor() {
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {
					// 设置延迟时间
					message.getMessageProperties().setDelay(DELAYED_TIME[report.getResendCount()]);
					return message;
				}
			});
		} else {
			log.info("【推送模块 - 推送状态报告】  第{}次推送状态报告成功！ report = {}", report.getResendCount() + 1, report);
		}
	}
}
