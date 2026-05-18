package org.example.rabbitspringboot;

import org.example.rabbitspringboot.config.DeadLetterConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DeadPublisherTest
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-18 09:38
 */

@SpringBootTest
public class DeadPublisherTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void publish(){
		String msg = "dead letter!";
		rabbitTemplate.convertAndSend(DeadLetterConfig.NORMAL_EXCHANGE,"normal.abc",msg);
	}

	@Test
	public void publishExpire(){
		String msg = "dead letter expire";
		rabbitTemplate.convertAndSend(DeadLetterConfig.NORMAL_EXCHANGE, "normal.abc", msg, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setExpiration("5000");
				return message;
			}
		});
	}
	
}
