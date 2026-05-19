package org.example.headers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.util.RabbitMQConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Publisher
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-18 22:05
 */

public class Publisher {
	
	public static final String HEADER_EXCHANGE = "header_exchange";
	public static final String HEADER_QUEUE = "header_queue";
	
	@Test
	public void publish() throws IOException, TimeoutException {
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		// 构建channel
		Channel channel = connection.createChannel();
		// 构建交换机
		channel.exchangeDeclare(HEADER_EXCHANGE, BuiltinExchangeType.HEADERS);
		// 构建队列
		channel.queueDeclare(HEADER_QUEUE, true, false, false, null);
		// 基于header的方式绑定交换机和队列
		Map<String, Object> arguments = new HashMap<>();
//		arguments.put("x-match", "all");  // all  全都要匹配
		arguments.put("x-match", "any");  // any  匹配上一个就行
		arguments.put("name", "jack");
		arguments.put("age", "23");
		channel.queueBind(HEADER_QUEUE, HEADER_EXCHANGE, "", arguments);
		// 发送消息
		String msg = "header测试消息";
		Map<String, Object> headers = new HashMap<>();
		headers.put("name", "jack");
		headers.put("age", "24");
		AMQP.BasicProperties props = new AMQP.BasicProperties()
				.builder()
				.headers(headers)
				.build();
		channel.basicPublish(HEADER_EXCHANGE, "", props, msg.getBytes());
		System.out.println("消息发送成功，headers = " + headers);
	}
	
}
