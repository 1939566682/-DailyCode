package org.example.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.SearchEnums;
import org.example.constant.RabbitMQConstant;
import org.example.model.StandardReport;
import org.example.service.SearchService;
import org.example.utils.SearchUtils;
import org.example.utils.ThreadLocalUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SmsUpdataLogListener
 *
 * @author Yang QingBo
 * @date 2026-06-12 14:33
 * @description
 */

@Slf4j
@Component
public class SmsUpdataLogListener {
	
	@Autowired
	private SearchService searchService;
	
	@RabbitListener(queues = RabbitMQConstant.SMS_GATEWAY_DEAD_LETTER_QUEUE)
	public void consume(StandardReport report, Channel channel, Message message) throws IOException {
		log.info("【搜索模块 - 修改日志】  接收到修改日志的信息 report = {}", report);
		
		// 将report对象存储到ThreadLocal中  以便于在搜索模块中获取
		ThreadLocalUtils.set(report);
		
		// 调用搜索模块完成的修改操作
		Map<String, Object> doc = new HashMap<>();
		doc.put("reportState", report.getReportState());
		searchService.update(SearchEnums.INDEX.getIndex() + SearchUtils.getYear(), report.getSequenceId().toString(), doc);
		
		// ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
