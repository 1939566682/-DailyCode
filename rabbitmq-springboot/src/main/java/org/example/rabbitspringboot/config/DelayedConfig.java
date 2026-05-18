package org.example.rabbitspringboot.config;

import org.jspecify.annotations.Nullable;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * DelayedConfig
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-18 14:56
 */

@Configuration
public class DelayedConfig {
	
	public static final String DELAYED_EXCHANGE = "delayed-exchange";
	public static final String DELAYED_QUEUE = "delayed-queue";
	public static final String DELAYED_ROUTING_KEY = "delayed.#";
	
	@Bean
	public Exchange delayedExchange() {
		Map<String, @Nullable Object> arguments = new HashMap<>();
		arguments.put("x-delayed-type","topic");
		Exchange exchange = new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", true, false, arguments);
		return exchange;
	}
	
	@Bean
	public Queue delayedQueue() {
		return QueueBuilder.durable(DELAYED_QUEUE).build();
	}
	
	@Bean
	public Binding delayBinding(Queue delayedQueue, Exchange delayedExchange) {
		return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
	}
	
}
