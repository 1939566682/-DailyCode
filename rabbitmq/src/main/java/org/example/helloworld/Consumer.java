package org.example.helloworld;

import com.rabbitmq.client.*;
import org.example.util.RabbitMQConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;


/**
 * Consumer
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 20:02
 */

public class Consumer {
	
	
	@Test
	public void consume() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare(Publisher.QUEUE_NAME, false, false, false, null);
		
		// 监听消息
		DefaultConsumer callback  = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消费者获取到消息："+ new String(body, StandardCharsets.UTF_8));
			}
		};
		channel.basicConsume(Publisher.QUEUE_NAME,true,callback);
		System.out.println("开始监听队列");
		
		System.in.read();
	}
	
}
