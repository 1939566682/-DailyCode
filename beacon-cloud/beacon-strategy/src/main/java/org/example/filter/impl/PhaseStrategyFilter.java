package org.example.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.config.RabbitMQConfig;
import org.example.constant.CacheConstant;
import org.example.constant.RabbitMQConstant;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.MobileOperatorUtil;
import org.example.util.OperatorUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PhaseStrategyFilter
 * 号段补全：获取手机号的运营商以及对应的归属地
 *
 * @author Yang QingBo
 * @date 2026-06-01 16:23
 * @description
 */

@Slf4j
@Service(value = "phase")
public class PhaseStrategyFilter implements StrategyFilter {
	
	/**
	 * 切分手机号前7位
	 */
	private final int MOBILE_START = 0;
	private final int MOBILE_END = 7;
	/**
	 * 校验的长度
	 */
	private final int MOBILE_SPLIT_LENGTH = 2;
	/**
	 * 分割区域和运营商的标识
	 */
	private final String SEPARATE = ",";
	/**
	 * 未知的情况
	 */
	private final String UNKNOWN = "unknown";
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private MobileOperatorUtil mobileOperatorUtil;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 号段补齐】  校验ing......");
		// 1、根据手机号前7位 查询手机号信息
		String mobile = submit.getMobile().substring(MOBILE_START, MOBILE_END);
		String mobileInfo = beaconCacheClient.getString(CacheConstant.PHASE + mobile);
		
		getMobileInfo:
		if (StringUtils.isEmpty(mobileInfo)) {
			// 2、查不到  调用第三方接口https://cx.shouji.360.cn/phonearea.php?number=xxx查询手机号对应信息
			mobileInfo = mobileOperatorUtil.getMobileInfoBy360(mobile);
			if (!StringUtils.isEmpty(mobileInfo)) {
				// 3、TODO 调用第三方查到消息后  发送消息到MQ  并且同步到MySQL和Redis
				rabbitTemplate.convertAndSend(RabbitMQConstant.MOBILE_AREA_OPERATOR,submit.getMobile());
				break getMobileInfo;
			}
			mobileInfo = UNKNOWN;
			// TODO 通知（发送邮件）
		}
		
		// 4、无论是redis还是第三方接口查到了之后  封装到StandardSubmit对象中
		String[] areaAndOperator = mobileInfo.split(SEPARATE);
		if (areaAndOperator.length == MOBILE_SPLIT_LENGTH) {
			submit.setArea(areaAndOperator[0]);
			submit.setOperatorId(OperatorUtil.getOperatorIdByOperatorName(areaAndOperator[1]));
		}
		
		
	}
}
