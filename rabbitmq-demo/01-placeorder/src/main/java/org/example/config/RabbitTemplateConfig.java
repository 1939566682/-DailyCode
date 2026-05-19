package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.example.mapper.ResendMapper;
import org.example.util.GlobeCache;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * RabbitTemplateConfig
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 19:17
 */

@Configuration
@Slf4j
public class RabbitTemplateConfig {
	
	@Autowired
	private ResendMapper resendMapper;
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		// new出RabbitTemplate对象
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		// 将connectionFactory设置进RabbitTemplate
		
		// 设置confirm回调
		rabbitTemplate.setConfirmCallback(confirmCallback());
		// 设置return回调
		rabbitTemplate.setReturnCallback(returnCallback());
		// 设置mandatory为true
		rabbitTemplate.setMandatory(true);
		// 返回RabbitTemplate对象
		return rabbitTemplate;
	}
	
	
	public RabbitTemplate.ConfirmCallback confirmCallback() {
		return new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
				if (correlationData == null) return;
				String msgId = correlationData.getId();
				if (ack) {
					log.info("log() confirm - 消息发送到Exchange成功！\t msgId = {}",msgId);
					GlobeCache.remove(msgId);
				} else {
					log.error("log() confirm - 消息发送到Exchange失败！\t msgId ={}",msgId);
					Map value = GlobeCache.get(msgId);
					resendMapper.save(value);
				}
			}
		};
	}
	
	public RabbitTemplate.ReturnCallback returnCallback() {
		return new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(@NonNull Message message, int replyCode, @NonNull String replyText,
			                            @NonNull String exchange, @NonNull String routingKey) {
				log.error("log() return - 消息未路由到队列");
				log.error("log() return - 消息为：{}", new String(message.getBody()));
				log.error("log() return - 交换机为：{}", exchange);
				log.error("log() return - 路由为：{}", routingKey);
				
			}
		};
	}
}
