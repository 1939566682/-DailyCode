package org.example.listener;

import com.rabbitmq.client.Channel;
import org.example.config.RabbitMQConfig;
import org.example.service.TBOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * DealyMessageListener
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 18:16
 */

@Component
public class DealyMessageListener {
	
	@Autowired
	private TBOrderService tbOrderService;
	
	@RabbitListener(queues = RabbitMQConfig.DEAD_QUEUE)
	public void consume(String id, Channel channel, Message message) throws IOException {
		// 调用service实现订单状态的处理
		tbOrderService.delayCancelOrder(id);
		// ack
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}
}
