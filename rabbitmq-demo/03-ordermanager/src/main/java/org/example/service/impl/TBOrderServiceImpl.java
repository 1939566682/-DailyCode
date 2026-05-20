package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.config.RabbitMQConfig;
import org.example.mapper.TBOrderMapper;
import org.example.service.TBOrderService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * TBOrderServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 15:19
 */

@Slf4j
@Service
public class TBOrderServiceImpl implements TBOrderService {
	
	@Autowired
	private TBOrderMapper tbOrderMapper;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	@Transactional
	public void save() {
		// 创建订单
		String id = UUID.randomUUID().toString();
		tbOrderMapper.save (id);
		// 订单构建成功  发送消息到RabbitMQ的死信队列
		rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, "", id, new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// 设置消息的生存时间为15秒  也可以在指定队列时 指定队列的生存时间
				message.getMessageProperties().setExpiration("15000");
				return message;
			}
		});
	}
	
	@Override
	@Transactional
	public void delayCancelOrder(String id) {
		// 基于id查询订单信息
		int orderState = tbOrderMapper.findOrderStateByIdUpdate(id);
		// 判断订单状态
		if (orderState != 0) {
			log.info("订单已经支付");
			return;
		}
		// 修改订单状态
		log.info("订单未支付 修改订单状态为已取消");
		tbOrderMapper.updateOrderStateById(-1,id);
		
	}
}
