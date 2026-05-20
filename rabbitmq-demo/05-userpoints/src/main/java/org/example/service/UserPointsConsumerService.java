package org.example.service;

import org.springframework.amqp.core.Message;

/**
 * UserPointsConsumerService
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-20 14:06
 */

public interface UserPointsConsumerService {
	
	/**
	 * 消费消息
	 * @param message
	 */
	void consume(Message message);

}
