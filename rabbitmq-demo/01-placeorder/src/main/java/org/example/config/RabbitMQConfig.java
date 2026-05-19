package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-19 18:10
 */

@Configuration
public class RabbitMQConfig {
	
	// 下单服务的交换机
	public static final String PLACE_ORDER_EXCHANGE = "place_order_exchange";
	
	// 三个服务的Queue
	public static final String COUPON_QUEUE = "coupon_queue";
	public static final String USER_POINTS_QUEUE = "user_points_queue";
	public static final String BUSINESS_QUEUE = "business_queue";
	
	// 构建见换机
	@Bean
	public Exchange placeOrderExchange(){
		return ExchangeBuilder.fanoutExchange(PLACE_ORDER_EXCHANGE).build();
	}
	
	// 构建队列
	@Bean
	public Queue couponQueue(){
		return QueueBuilder.durable(COUPON_QUEUE).build();
	}
	
	@Bean
	public Queue userPointsQueue(){
		return QueueBuilder.durable(USER_POINTS_QUEUE).build();
	}
	
	@Bean
	public Queue businessQueue(){
		return QueueBuilder.durable(BUSINESS_QUEUE).build();
	}
	
	// 绑定交换机和队列
	@Bean
	public Binding couponBinding(Queue couponQueue,Exchange placeOrderExchange){
		return BindingBuilder.bind(couponQueue).to(placeOrderExchange).with("").noargs();
	}
	
	@Bean
	public Binding userPointsBinding(Queue userPointsQueue,Exchange placeOrderExchange){
		return BindingBuilder.bind(userPointsQueue).to(placeOrderExchange).with("").noargs();
	}
	
	@Bean
	public Binding cbusinessBinding(Queue businessQueue,Exchange placeOrderExchange){
		return BindingBuilder.bind(businessQueue).to(placeOrderExchange).with("").noargs();
	}
}
