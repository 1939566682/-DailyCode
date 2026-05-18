package org.example.rabbitspringboot;

import org.example.rabbitspringboot.config.DelayedConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DelayedPublisher
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-18 15:09
 */

@SpringBootTest
public class DelayedPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void publish(){
		rabbitTemplate.convertAndSend(DelayedConfig.DELAYED_EXCHANGE, "delayed.abc", "xxx", new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setDelayLong(3000L);
				return message;
			}
		});
	}
	
}
