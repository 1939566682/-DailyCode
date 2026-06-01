package org.example.constant;

/**
 * RabbitMQConstant
 * 关于RabbitMQ中的一些队列信息
 * @author Yang QingBo
 * @date 2026-06-01 14:25
 * @description
 */

public interface RabbitMQConstant {
	
	/**
	 * 接口模块发送消息到策略模块的队列名称
	 */
	String SMS_PRE_SEND = "sms_pre_send_topic";

}
