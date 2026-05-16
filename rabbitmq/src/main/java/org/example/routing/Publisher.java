package org.example.routing;

import com.rabbitmq.client.BuiltinExchangeType;
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
 * {@code @date} 2026-05-16 21:10
 */

public class Publisher {
	
	public static final String EXCHANGE_NAME = "routing";
	public static final String QUEUE_NAME1 = "routing-one";
	public static final String QUEUE_NAME2 = "routing-two";
	
	@Test
	public void publish() throws IOException, TimeoutException {
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建 channel
		Channel channel = connection.createChannel();
		
		// 构建交换机
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		
		// 构建队列
		channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
		channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
		
		// 绑定交换机和队列
		channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"orange");
		channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"black");
		channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"green");
		
		// 发消息到交换机
		channel.basicPublish(EXCHANGE_NAME,"orange",null, "orange".getBytes());
		channel.basicPublish(EXCHANGE_NAME,"black",null, "black".getBytes());
		channel.basicPublish(EXCHANGE_NAME,"white",null, "white".getBytes());
		System.out.println("消息成功发送！");
		
	}
}
