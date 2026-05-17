package org.example.rpc;

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
 * {@code @date} 2026-05-16 20:02
 */

public class Consumer {
	
	public static final String QUEUE_PUBLISHER = "rpc_publisher";
	public static final String QUEUE_CONSUMER = "rpc_consumer";
	
	@Test
	public void consume() throws IOException, TimeoutException {
		
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建Channel
		Channel channel = connection.createChannel();
		
		// 构建队列
		channel.queueDeclare(QUEUE_PUBLISHER, false, false, false, null);
		channel.queueDeclare(QUEUE_CONSUMER, false, false, false, null);
		
		// 监听消息
		DefaultConsumer callback = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				
				System.out.println("消费者获取到消息：" + new String(body, StandardCharsets.UTF_8));
				String resp = "获取到了client发出的的请求  这里是响应的信息";
				String respQueueName = properties.getReplyTo();
				String id = properties.getCorrelationId();
				AMQP.BasicProperties props = new AMQP.BasicProperties().builder().correlationId(id).build();
				channel.basicPublish("", respQueueName, props, resp.getBytes());
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		};
		channel.basicConsume(QUEUE_PUBLISHER, false, callback);
		System.out.println("开始监听队列");
		
		System.in.read();
	}
	
}
