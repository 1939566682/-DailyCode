package org.example.config;

import org.example.constant.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.example.constant.RabbitMQConstant.*;


/**
 * RabbitMQConfig
 *
 * @author Yang QingBo
 * @date 2026-06-09 20:35
 * @description 针对性的配置可以采用当前方式
 */

@Configuration
public class RabbitMQConfig {
	
	private final Integer TTL = 10000;
	private final String FANOUT_ROUTING_KEY = "fanout_routing_key";
	
	// 声明死信队列  需要准备普通交换机  普通队列  死信交换机  死信队列
	@Bean
	public Exchange normalExchange() {
		return ExchangeBuilder.fanoutExchange(SMS_GATEWAY_NORMAL_EXCHANGE).build();
	}
	
	@Bean
	public Queue normalQueue() {
		return QueueBuilder.durable(SMS_GATEWAY_NORMAL_QUEUE)
				.withArgument("x-message-ttl",TTL)
				.withArgument("x-dead-letter-exchange",SMS_GATEWAY_DEAD_LETTER_EXCHANGE)
				.withArgument("x-dead-letter-routing-key",FANOUT_ROUTING_KEY) // 因为使用的是fanout直接绑定  这里写或者不写都可以
				.build();
	}
	
	@Bean
	public Binding normalBinding(Queue normalQueue, Exchange normalExchange) {
		return BindingBuilder.bind(normalQueue).to(normalExchange).with(FANOUT_ROUTING_KEY).noargs();
	}
	
	@Bean
	public Exchange deadLetterExchange() {
		return ExchangeBuilder.fanoutExchange(SMS_GATEWAY_DEAD_LETTER_EXCHANGE).build();
	}
	
	@Bean
	public Queue deadLetterQueue() {
		return QueueBuilder.durable(SMS_GATEWAY_DEAD_LETTER_QUEUE).build();
	}
	
	@Bean
	public Binding deadLetterBinding(Queue deadLetterQueue, Exchange deadLetterExchange) {
		return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(FANOUT_ROUTING_KEY).noargs();
	}

	// 以配置类的方式设置RabbitMQ消费的方式
//	@Bean
	public SimpleRabbitListenerContainerFactory gatewayContainerFactory(ConnectionFactory connectionFactory,
	                                                                    SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConcurrentConsumers(5);
		factory.setPrefetchCount(10);
		factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		
		configurer.configure(factory, connectionFactory);
		return factory;
	}

}
