package org.example.topics;

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
 * {@code @date} 2026-05-16 21:20
 */

public class Publisher {
	
	public static final String EXCHANGE_NAME = "topic";
	public static final String QUEUE_NAME1 = "topic-one";
	public static final String QUEUE_NAME2 = "topic-two";
	
	@Test
	public void publish() throws IOException, TimeoutException {
		// 获取连接对象
		Connection connection = RabbitMQConnectionUtil.getConnection();
		
		// 构建 channel
		Channel channel = connection.createChannel();
		
		// 构建交换机
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		
		// 构建队列
		channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
		channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
		
		// 绑定交换机和队列
		// TOPIC类型的交换机在和队列绑定时  需要以aaa.bbb.ccc 的方式进行绑定
		// 其中有两个特殊字符：* （相当于占位符） # （相当于通配符）
		channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"*.orange.*");
		channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"*.*.rabbit");
		channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"lazy.#");
		
		// 发消息到交换机
		channel.basicPublish(EXCHANGE_NAME,"big.orange.rabbit",null, "orange".getBytes());
		channel.basicPublish(EXCHANGE_NAME,"small.white.rabbit",null, "rabbit".getBytes());
		channel.basicPublish(EXCHANGE_NAME,"lazy.dog.han",null, "lazy".getBytes());
		System.out.println("消息成功发送！");
		
	}
	
}
