package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitTemplateConfig
 * 设置RabbitTemplate的Confirm&Return机制
 *
 * @author Yang QingBo
 * @date 2026-06-01 14:31
 * @description
 */

@Slf4j
@Configuration
public class RabbitTemplateConfig {
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		// 1、构建RabbitTemplate对象
		// 2、设置connectionFactory
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		
		// 3、配置confirm机制
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
				// ack为false  代表消息没有发送到交换机
				if (!ack) {
					log.error("【接口模块 - 发送消息】 消息没有发送到exchange，correlationData = {}，cause = {}", correlationData, cause);
				}
			}
		});
		
		// 4、配置return机制
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			
			// 出发这个回调  说明交换机没有把消息路由到指定的队列中
			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				log.error("【接口模块 - 发送消息】 消息没有路由到指定的Queue  message = {}  exchange = {}  routingKey = {}", new String(message.getBody()), exchange, routingKey);
			}
		});
		
		return rabbitTemplate;
	}
	
}
