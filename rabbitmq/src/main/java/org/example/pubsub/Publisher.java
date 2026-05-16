package org.example.pubsub;

import com.rabbitmq.client.AMQP;
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
 * {@code @date} 2026-05-16 20:53
 */

public class Publisher {
	
	public static final String EXCHANGE_NAME = "pubsub";
	public static final String QUEUE_NAME1 = "pubsub-one";
	public static final String QUEUE_NAME2 = "pubsub-two";
	
	@Test
	public void publish() throws IOException, TimeoutException {
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建 channel
		Channel channel = connection.createChannel();
		
		// 构建交换机
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		
		// 构建队列
		channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
		channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
		
		// 绑定交换机和队列  使用的是FANOUT类型的交换机  绑定方式是直接绑定  routingkey写或者不写都可以
		channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"");
		channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"");
		
		// 发消息到交换机
		channel.basicPublish(EXCHANGE_NAME,"",null, "publish/subscrible!".getBytes());
		System.out.println("消息成功发送！");
		
	}
	
}
