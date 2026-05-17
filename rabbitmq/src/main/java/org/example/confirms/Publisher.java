package org.example.confirms;

import com.rabbitmq.client.*;
import org.example.util.RabbitMQConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Publisher
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-17 19:18
 */

public class Publisher {
	
	@Test
	public void publish() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare("confirms", true, false, false, null);
		
		String message = "hello";
		
		// 开启confirms
		channel.confirmSelect();
		
		// 设置confirms的异步回调
		channel.addConfirmListener(new ConfirmListener() {
			@Override
			public void handleAck(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("消息成功的发送到Exchange");
			}
			
			@Override
			public void handleNack(long deliveryTag, boolean multiple) throws IOException {
				System.out.println("消息成功的发送到Exchange");
				System.out.println("我收到了消息，但我处理失败了，尝试重试");
			}
		});
		
		// 设置return回调  确认消息是否路由到了Queue
		channel.addReturnListener(new ReturnListener() {
			@Override
			public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("消息没有路由到指定队列");
			}
		});
		
		// 设置消息持久化
		AMQP.BasicProperties props = new AMQP.BasicProperties().builder().deliveryMode(2).build();
		
		// 发布消息
		channel.basicPublish("", "confirms", true, props, message.getBytes());
		System.out.println("消息发送成功！！");
		
		
		System.in.read();
	}
	
}
