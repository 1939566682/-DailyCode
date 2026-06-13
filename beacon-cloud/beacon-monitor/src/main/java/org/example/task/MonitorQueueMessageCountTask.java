package org.example.task;

import com.rabbitmq.client.Channel;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.example.client.BeaconCacheClient;
import org.example.constant.RabbitMQConstant;
import org.example.util.MailUtil;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Set;

/**
 * MonitorQueueMessageCountTask
 *
 * @author Yang QingBo
 * @date 2026-06-13 20:22
 * @description 监控队列中的消息个数 如果队列中的消息超过一万条 直接发送短信通知
 */

@Component
public class MonitorQueueMessageCountTask {
	
	// 查询队列名称的固定pattern
	private final String QUEUE_PATTERN = "channel:*";
	
	// 获取channel的索引
	private final Integer CHANNEL_ID_INDEX = QUEUE_PATTERN.indexOf("*");
	
	// 队列消息限制
	private final long MESSAGE_COUNT_LIMIT = 0;
	
	String text = "您的队列消息堆积超过一万条了  队列名：%s  消息个数：%s";
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	// 注入RabbitMQ的ConnectionFactory
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private MailUtil mailUtil;
	
	@XxlJob("monitorQueueMessageCountTask")
	public void monitor() throws MessagingException {
		// 1、拿到所有的队列名称
		// TODO 这里说的是通过通道id  但是策略模块推送消息到短信网关模块时用的是submit的clientId
		Set<String> keys = beaconCacheClient.keys(QUEUE_PATTERN);
		
		// 2、需要channel去操作
		Connection connection = connectionFactory.createConnection();
		Channel channel = connection.createChannel(false);
		listenQueueAndSendEmail(channel,RabbitMQConstant.SMS_PRE_SEND);
		for (String key : keys) {
			// 封装队列名称
			String queueName = RabbitMQConstant.SMS_GATEWAY + key.substring(CHANNEL_ID_INDEX);
			listenQueueAndSendEmail(channel, queueName);
		}
		
		

	}
	
	private void listenQueueAndSendEmail(Channel channel, String queueName) throws MessagingException {
		// 队列不存在则直接构建  如果已经存在就直接忽略
		try {
			channel.queueDeclare(queueName, true, false, false, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 3、拿到对应队列的消息 确认消息数量 超过限制 及时通知
		long count = 0;
		try {
			count = channel.messageCount(queueName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (count > MESSAGE_COUNT_LIMIT) {
			// 4、通知的方式就是发送短信
			mailUtil.sendEmail(queueName + "队列消息队列堆积",String.format(text, queueName,count));
		}
	}
	
}
