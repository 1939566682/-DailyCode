package org.example.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.model.StandardSubmit;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * SmsGatewayListener
 *
 * @author Yang QingBo
 * @date 2026-06-09 20:08
 * @description
 */

@Slf4j
@Component
public class SmsGatewayListener {

	@RabbitListener(queues = "${gateway.sendtopic}")
	public void consume(StandardSubmit submit, Channel channel, Message message) throws IOException {
		log.info("【短信网关模块】  接收到消息 submit = {}",submit);
		// 完成与运营商交互  发送一次请求  接受两次响应
		
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}

}
