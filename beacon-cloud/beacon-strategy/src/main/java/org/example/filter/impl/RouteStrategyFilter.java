package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.ChannelTransferUtil;
import org.example.util.ErrorSendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 路由策略】  校验ing......");
		// 1、拿到客户id
		Long clientId = submit.getClientId();
		// 2、基于redis获取到当前客户绑定的所有通道信息
		Set<Map> clientChannels = beaconCacheClient.sMemberMap(CacheConstant.CLIENT_CHANNEL + clientId);
		// 3、将获取到的客户通道信息基于权重进行排序
		TreeSet<Map> clientWeightChannels = new TreeSet<>(new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				int o2Weight = Integer.parseInt(String.valueOf(o2.get("clientChannelWeight")));
				int o1Weight = Integer.parseInt(String.valueOf(o1.get("clientChannelWeight")));
				return o2Weight - o1Weight;
			}
		});
		clientWeightChannels.addAll(clientChannels);
		
		// 4、基于排序后的通道  选择权重更高的
		boolean ok = false;
		for (Map clientWeightChannel : clientWeightChannels) {
			// 5、如果客户和通道的绑定关系可用  基于redis直接查询具体的通道信息
			if (Integer.parseInt(String.valueOf(clientWeightChannel.get("isAvailable"))) != 0) {
				// 当前绑定关系不可用  直接进行下次循环  选择权重相对更低一点的
				continue;
			}
			// 6、查询到通道信息后 判断通道可用 以及运营商是否匹配
			Map<String, Object> channel = beaconCacheClient.hGetAll(CacheConstant.CHANNEL + clientWeightChannel.get("channelId"));
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
			break;
		}
		
		if (ok == false) {
			log.info("【策略模块 - 路由策略】  没有可用的通道！");
			// 封装错误信息
			// ========发送写日志================
			submit.setErrorMsg(ExceptionEnums.NOT_AVAILABLE_CHANNEL.getMessage());
			errorSendMsgUtil.sendWriteLog(submit);
			// ====发送状态报告前 需要将report对象进行数据封装========
			errorSendMsgUtil.sendPushReport(submit);
			// ======抛出异常=========
			throw new StrategyException(ExceptionEnums.NOT_AVAILABLE_CHANNEL);
		}
		
		// 8、
	}
}
