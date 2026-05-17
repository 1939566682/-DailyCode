package org.example.rabbitspringboot.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ConsumerTest
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 23:06
 */

//@Component
//public class ConsumerListener {
//
//	@RabbitListener(queues = RabbitMQConfig.QUEUE)
//	public void consume(String msg, Channel channel, Message message) throws IOException {
//		System.out.println("consumer - 队列的消息为：" + msg);
//		String id = message.getMessageProperties().getCorrelationId();
//		System.out.println("consumer - 唯一标识为：" + id);
//		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//	}
//}
