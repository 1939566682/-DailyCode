package org.example.rabbitspringboot;

import org.example.rabbitspringboot.config.RabbitMQConfig;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * PublisherTest
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 22:40
 */

@SpringBootTest
public class PublisherTest {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void publish() {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "big.black.dog", "message");
		System.out.println("消息发送成功");
	}
	
	@Test
	public void publishWithProps() {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "big.black.dog", "messageWithProps", new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setCorrelationId("456");
				return message;
			}
		});
		System.out.println("消息发送成功");
	}
	
	@Test
	public void publishWithConfirms() throws IOException {
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
				if (ack) {
					System.out.println("消息已经送达了Exchange");
				} else {
					System.out.println("消息没有送达Exchange");
				}
			}
		});
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "big.black.dog", "message");
		System.out.println("消息发送成功");
		
		System.in.read();
	}
	
	@Test
	public void publishWithReturn() throws IOException {
		rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
			@Override
			public void returnedMessage(@NonNull ReturnedMessage returned) {
				String msg = new String(returned.getMessage().getBody());
				System.out.println("消息：" + msg + "  路由队列失败");
			}
		});
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "big.white.dog", "message");
		System.out.println("消息发送成功");
		
		System.in.read();
	}
	
	@Test
	public void publishWithProperties() throws IOException {
		
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "big.white.dog", "message", new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(@NonNull Message message) throws AmqpException {
				message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
				return message;
			}
		});
		System.out.println("消息发送成功");
		
		System.in.read();
	}
	
}
