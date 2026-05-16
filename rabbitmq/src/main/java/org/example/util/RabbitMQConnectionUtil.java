package org.example.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQConnectionUtil
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 19:32
 */

public class RabbitMQConnectionUtil {
	
	public static final String RABBITMQ_HOST="192.168.113.3";
	public static final int RABBITMQ_PORT=5672;
	public static final String USERNAME="guest";
	public static final String RABBITMQ_PASSWORD="guest";
	public static final String RABBITMQ_VIRTUAL_HOST="/";
	
	
	/**
	 * 构建 RabbitMQ 的连接对象
	 * @return
	 */
	public static Connection getConnection() throws IOException, TimeoutException {
		// 创建 Connection 连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		
		// 设置 RabbitMQ 的连接信息
		factory.setHost(RABBITMQ_HOST);
		factory.setPort(RABBITMQ_PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(RABBITMQ_PASSWORD);
		factory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);
		
		// 返回连接对象
		Connection connection = factory.newConnection();
		return connection;
	}
}
