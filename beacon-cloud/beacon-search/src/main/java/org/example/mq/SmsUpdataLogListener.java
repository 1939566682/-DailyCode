package org.example.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RabbitMQConstant;
import org.example.model.StandardReport;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
	
	@RabbitListener(queues = RabbitMQConstant.SMS_GATEWAY_DEAD_LETTER_QUEUE)
	public void consume(StandardReport report, Channel channel, Message message) {
		log.info("【搜索模块 - 修改日志】  接收到修改日志的信息 report = {}",report);
	}
	
}
