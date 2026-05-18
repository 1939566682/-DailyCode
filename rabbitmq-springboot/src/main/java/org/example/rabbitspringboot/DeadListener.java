package org.example.rabbitspringboot;

import com.rabbitmq.client.Channel;
import org.example.rabbitspringboot.config.DeadLetterConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * DeadListener
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-18 09:41
 */

//@Component
public class DeadListener {

	@RabbitListener(queues = DeadLetterConfig.NORMAL_QUEUE)
	public void consume(String msg, Channel channel, Message message) throws IOException {
		System.out.println("normal - 队列的消息为：" + msg);
		channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
	}

}
