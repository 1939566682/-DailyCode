package org.example.rpc;

import com.rabbitmq.client.*;
import org.example.util.RabbitMQConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Publisher
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 21:46
 */

public class Publisher {
	
	
	public static final String QUEUE_PUBLISHER = "rpc_publisher";
	public static final String QUEUE_CONSUMER = "rpc_consumer";
	
	@Test
	public void publish() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare(QUEUE_PUBLISHER, false, false, false, null);
		channel.queueDeclare(QUEUE_CONSUMER, false, false, false, null);
		
		// 发布消息
		String message = "Hello RPC！！";
		String uuid = String.valueOf(UUID.randomUUID());
		AMQP.BasicProperties props = new AMQP.BasicProperties()
				.builder()
				.replyTo("QUEUE_CONSUMER")
				.correlationId(uuid)
				.build();
		channel.basicPublish("", QUEUE_PUBLISHER, props, message.getBytes());
		channel.basicConsume(QUEUE_CONSUMER,false,new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String id = properties.getCorrelationId();
				if (id != null  &&  id.equalsIgnoreCase(uuid)){
					System.out.println("接收到服务端的响应："+new String(body, StandardCharsets.UTF_8));
				}
			}
		});
		System.out.println("消息发送成功！！");
		
		
		System.in.read();
	}
	
}
