package org.example.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.RabbitMQConstant;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.filter.StrategyFilterContext;
import org.example.model.StandardSubmit;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * PreSendListener
 *
 * @author Yang QingBo
 * @date 2026-06-01 15:33
 * @description
 */

@Slf4j
@Component
public class PreSendListener {
	
	/**
	 * 整个策略模块的校验
	 */
	@Autowired
	private StrategyFilterContext strategyFilterContext;
	
	@RabbitListener(queues = RabbitMQConstant.SMS_PRE_SEND)
	public void listen(StandardSubmit submit, Message message, Channel channel) throws IOException {
		log.info("【策略模块 - 接收消息】 接收到接口模块发送的消息 submit = {}",submit);
		
		try {
			strategyFilterContext.strategy(submit);
			log.info("【策略模块 - 消费完毕】手动ack");
			channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
		} catch (StrategyException e) {
			log.error("【策略模块 - 消费/校验失败】 错误信息：{}",e.getMessage());
		}
	}
}
