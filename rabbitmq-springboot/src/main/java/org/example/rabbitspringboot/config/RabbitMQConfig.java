package org.example.rabbitspringboot.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-16 22:28
 */

@Configuration
public class RabbitMQConfig {
	
	public static final String EXCHANGE = "springboot-exchange";
	public static final String QUEUE = "springboot-queue";
	public static final String ROUTING_KEY = "*.black.*";
	
	@Bean
	public Exchange bootExchange(){
		// channel.DeclareExchange
		return ExchangeBuilder.topicExchange(EXCHANGE).build();
	}
	
	@Bean
	public Queue bootQueue(){
		return QueueBuilder.durable(QUEUE).build();
	}
	
	@Bean
	public Binding bootBinding(Exchange bootExchange,Queue bootQueue){
		return BindingBuilder.bind(bootQueue).to(bootExchange).with(ROUTING_KEY).noargs();
	}
	
	
	
}
