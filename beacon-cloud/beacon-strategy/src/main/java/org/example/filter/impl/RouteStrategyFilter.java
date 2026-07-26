package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.constant.RabbitMQConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.ChannelTransferUtil;
import org.example.util.ErrorSendMsgUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * BlackStrategyFilter
 * 路由策略：选择合适的运营商通道
 *
 * @author Yang QingBo
 * @date 2026-06-01 16:25
 * @description
 */

@Slf4j
@Service("route")
public class RouteStrategyFilter implements StrategyFilter {
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private ErrorSendMsgUtil errorSendMsgUtil;
	
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 路由策略】  校验ing......");
		// 1、拿到客户id
		Long clientId = submit.getClientId();
		// 2、基于redis获取到当前客户绑定的所有通道信息
		Set<Map> clientChannels = beaconCacheClient.sMemberMap(CacheConstant.CLIENT_CHANNEL + clientId);
		// 3、将获取到的客户通道信息基于权重进行排序
		List<Map> clientWeightChannels = new ArrayList<>(clientChannels);
		clientWeightChannels.sort((o1, o2) -> {
			int weight2 = Integer.parseInt(String.valueOf(o2.get("clientChannelWeight")));
			int weight1 = Integer.parseInt(String.valueOf(o1.get("clientChannelWeight")));
			return weight2 - weight1; // 降序
		});
		// TODO TreeSet 会强行去重 如果 Comparator.compare(o1, o2) == 0 TreeSet 就会认为这两个元素是“同一个” 从而丢弃后添加的那个
		/*TreeSet<Map> clientWeightChannels = new TreeSet<>(new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				int o2Weight = Integer.parseInt(String.valueOf(o2.get("clientChannelWeight")));
				int o1Weight = Integer.parseInt(String.valueOf(o1.get("clientChannelWeight")));
				return o2Weight - o1Weight;
			}
		});*/
//		clientWeightChannels.addAll(clientChannels);
		
		// 4、基于排序后的通道  选择权重更高的
		boolean ok = false;
		Map<String, Object> channel = null;
		Map clientChannel = null;
		for (Map clientWeightChannel : clientWeightChannels) {
			// 5、如果客户和通道的绑定关系可用  基于redis直接查询具体的通道信息
			if (Integer.parseInt(String.valueOf(clientWeightChannel.get("isAvailable"))) != 0) {
				// 当前绑定关系不可用  直接进行下次循环  选择权重相对更低一点的
				continue;
			}
			// 6、查询到通道信息后 判断通道可用 以及运营商是否匹配
			channel = beaconCacheClient.hGetAll(CacheConstant.CHANNEL + clientWeightChannel.get("channelId"));
			if (Integer.parseInt(String.valueOf(channel.get("isAvailable"))) != 0) {
				// 当前通道不可用 选择权重更低的通道
				continue;
			}
			// 获取通道的通讯方式
			Integer channelType = Integer.parseInt(String.valueOf(channel.get("channelType")));
			if (channelType != 0 && submit.getOperatorId() != channelType) {
				// 通道不是全网通  并且和当前手机号运营商不匹配
				continue;
			}
			// 7、TODO 如果后期涉及到了通道的转换  在这里进行处理
			Map transferChannel = ChannelTransferUtil.transferToChannel(submit, channel);
			
			// 找到可以使用的通道了
			ok = true;
			clientChannel = clientWeightChannel;
			break;
		}
		
		if (!ok) {
			log.info("【策略模块 - 路由策略】  没有可用的通道！");
			submit.setErrorMsg(ExceptionEnums.NOT_AVAILABLE_CHANNEL.getMessage());
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.NOT_AVAILABLE_CHANNEL);
		}
		
		// 8、基于选择的通道封装submit的信息
		submit.setChannelId(Long.parseLong(String.valueOf(channel.get("id"))));
		submit.setSrcNumber("" + channel.get("channelNumber") + clientChannel.get("clientChannelNumber"));
		
		try {
			// 9、声明队列名称 并构建队列
			String queueName = RabbitMQConstant.SMS_GATEWAY + submit.getChannelId();
			amqpAdmin.declareQueue(QueueBuilder.durable(queueName).build());
			
			// 10、发送消息到声明好的队列中
			rabbitTemplate.convertAndSend(queueName, submit);
		} catch (AmqpException e) {
			log.info("【策略模块 - 路由策略】  声明通道对应队列以及发送消息时出现了错误！");
			submit.setErrorMsg(e.getMessage());
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(e.getMessage(),ExceptionEnums.UNKNOWN_ERROR.getCode());
		}
		
	}
}
