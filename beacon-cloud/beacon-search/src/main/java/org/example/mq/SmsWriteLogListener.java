package org.example.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RabbitMQConstant;
import org.example.model.StandardSubmit;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * SmsWriteLogListener
 *
 * @author Yang QingBo
 * @date 2026-06-08 14:27
 * @description
 */

@Slf4j
@Component
public class SmsWriteLogListener {
	
	
	@RabbitListener(queues = RabbitMQConstant.SMS_WRITE_LOG)
	public void consume(StandardSubmit submit, Channel channel, Message message) throws IOException {
		// 1、调用搜索模块的搜索方法  完成添加操作
		log.info("【搜索模块】  接收到存储日志的信息 submit = {}",submit);
		
		// 2、手动ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}

}
