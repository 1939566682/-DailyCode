package org.example.workqueues;

import com.rabbitmq.client.*;
import org.example.util.RabbitMQConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Consumer
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 20:26
 */

public class Consumer {
	
	@Test
	public void consume1() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare(Publisher.QUEUE_NAME, false, false, false, null);
		
		// 设置消息的流控
		channel.basicQos(1);
		
		// 监听消息
		DefaultConsumer callback  = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("消费者1号 - 获取到消息："+ new String(body, StandardCharsets.UTF_8));
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		};
		channel.basicConsume(Publisher.QUEUE_NAME,false,callback);
		System.out.println("开始监听队列");
		
		System.in.read();
	}
	
	@Test
	public void consume2() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare(Publisher.QUEUE_NAME, false, false, false, null);
		
		// 设置消息的流控
		channel.basicQos(1);
		
		// 监听消息
		DefaultConsumer callback  = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("消费者2号 - 获取到消息："+ new String(body, StandardCharsets.UTF_8));
				channel.basicAck(envelope.getDeliveryTag(),false);
			}
		};
		channel.basicConsume(Publisher.QUEUE_NAME,true,callback);
		System.out.println("开始监听队列");
		
		System.in.read();
	}
	
}
