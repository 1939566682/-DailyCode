package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQConfig
 *
 * @author Yang QingBo
 * @date 2026-06-09 09:16
 * @description
 */

@Configuration
public class RabbitMQConfig {
	
	public static final String DELAYED_EXCHANGE = "push_delayed_exchange";
	
	public static final String DELAYED_QUEUE = "push_delayed_queue";
	
	public static final String DELAYED_EXCHANGE_TYPE = "x-delayed-message";
	
	public static final String DELAYED_ROUTING_TYPE_KEY = "x-delayed-type";
	
	public static final String DELAYED_ROUTING_TYPE_FANOUT = "fanout";
	
	@Bean
	public Exchange delayedExchange() {
		Map<String, Object> arguments =  new HashMap<>();
		arguments.put(DELAYED_ROUTING_TYPE_KEY, DELAYED_ROUTING_TYPE_FANOUT);
		return new CustomExchange(DELAYED_EXCHANGE,DELAYED_EXCHANGE_TYPE,false,false,arguments);
	}
	
	@Bean
	public Queue delayedQueue() {
		return QueueBuilder.durable(DELAYED_QUEUE).build();
	}
	
	@Bean
	public Binding delayedBinding(Exchange delayedExchange, Queue delayedQueue) {
		return BindingBuilder.bind(delayedQueue).to(delayedExchange).with("").noargs();
	}
	
}
