package org.example.workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.util.RabbitMQConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Publisher
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 19:43
 */

public class Publisher {
	
	public static final String QUEUE_NAME = "work";
	
	@Test
	public void publish() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		// 发布消息
		for (int i = 0; i < 10; i++) {
			String message = "Hello WorkQueues"+i;
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println("消息发送成功！！");
		}
	}
}
