package org.example.config;

import org.example.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQConfig
 * 构建队列&交换机信息
 * @author Yang QingBo
 * @date 2026-06-01 14:27
 * @description
 */

@Configuration
public class RabbitMQConfig {
	
	/**
	 * 接口模块发送消息到策略模块的队列
	 * @return
	 */
	@Bean
	public Queue preSendQueue() {
		return QueueBuilder.durable(RabbitMQConstant.MOBILE_AREA_OPERATOR).build();
	}

}
