package org.example.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * RabbitMQConfig
 *
 * @author Yang QingBo
 * @date 2026-06-09 20:35
 * @description 针对性的配置可以采用当前方式
 */

//@Configuration
public class RabbitMQConfig {

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
