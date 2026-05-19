package org.example.listener;

import com.rabbitmq.client.Channel;
import org.example.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CouponListener
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 18:53
 */

@Component
public class UserPointsListener {
	
	@RabbitListener(queues = RabbitMQConfig.USER_POINTS_QUEUE)
	public void consume(String msg, Channel channel, Message message) throws InterruptedException, IOException {
		Thread.sleep(400);
		System.out.println("用户积分预扣除成功！\t" + msg);
		// 手动 ACK
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
	
}
