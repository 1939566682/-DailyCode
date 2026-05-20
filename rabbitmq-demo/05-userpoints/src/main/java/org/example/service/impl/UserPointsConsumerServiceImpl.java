package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.mapper.UserPointsIdempotentMapper;
import org.example.service.UserPointsConsumerService;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserPointsConsumerService
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 14:05
 */

@Slf4j
@Service
public class UserPointsConsumerServiceImpl implements UserPointsConsumerService {
	
	@Autowired
	UserPointsIdempotentMapper userPointsIdempotentMapper;
	
	
	private static final String ID_NAME = "spring_returned_message_correlation";
	
	@Override
	@Transactional
	public void consume(Message message) {
		// 获取生产者提供的Correlation要基于header获取
		String id = message.getMessageProperties().getHeader(ID_NAME);
		// 查询幂等表是否存在当前消息标识
		Integer count = userPointsIdempotentMapper.findById(id);
		
		// 如果存在  直接结束
		if (count == 1) {
			log.info("log() consume : 消息已经被消费  无需重复消费");
			return;
		}
		
		// 如果不存在  插入消息标识到幂等表
		userPointsIdempotentMapper.save(id);
		
		// 执行消费逻辑
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("用户积分预扣除成功！\t" + message);
	}
}
