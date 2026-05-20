package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 * 构建普通交换机&队列  以及绑定上的死信交换机&队列
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 15:33
 */

@Configuration
public class RabbitMQConfig {
	
	public static final String ORDER_QUEUE = "order_queue";
	public static final String ORDER_EXCHANGE = "order_exchange";
	
	public static final String DEAD_QUEUE = "dead_queue";
	public static final String DEAD_EXCHANGE = "dead_exchange";
	
	// 普通交换机&队列
	@Bean
	public Exchange orderExchange() {
		return ExchangeBuilder.fanoutExchange(ORDER_EXCHANGE).build();
	}
	
	@Bean
	public Queue orderQueue() {
		return QueueBuilder.durable(ORDER_QUEUE).deadLetterExchange(DEAD_EXCHANGE).build();
	}
	
	// 死信交换机&队列
	@Bean
	public Exchange deadExchange() {
		return ExchangeBuilder.fanoutExchange(DEAD_EXCHANGE).build();
	}
	
	@Bean
	public Queue deadQueue() {
		return QueueBuilder.durable(DEAD_QUEUE).build();
	}
	
	// 绑定队列和交换机
	@Bean
	public Binding orderBinding(Queue orderQueue, Exchange orderExchange) {
		return BindingBuilder.bind(orderQueue).to(orderExchange).with("").noargs();
	}
	
	@Bean
	public Binding deadBinding(Queue deadQueue, Exchange deadExchange) {
		return BindingBuilder.bind(deadQueue).to(deadExchange).with("").noargs();
	}

	
}
