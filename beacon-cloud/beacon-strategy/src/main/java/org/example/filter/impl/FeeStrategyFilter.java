package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardSubmit;
import org.example.util.ClientBalanceUtil;
import org.example.util.ErrorSendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FeeStrategyFilter
 *
 * @author Yang QingBo
 * @date 2026-06-05 14:23
 * @description 策略模块的扣费操作
 */

@Slf4j
@Service("fee")
public class FeeStrategyFilter implements StrategyFilter {
	
	private final String BALANCE = "balance";
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private ErrorSendMsgUtil errorSendMsgUtil;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块 - 扣费校验】   校验ing......");
		// 1、先获取submit中封装的金额
		Long fee = submit.getFee();
		Long clientId = submit.getClientId();
		// 2、调用redis的decr扣减具体的金额
		Long balance = beaconCacheClient.hIncrBy(CacheConstant.CLIENT_BALANCE + clientId, BALANCE, -fee);
		// todo 暂时写死的 3、获取当前客户的欠费金额的限制（外部方法调用）
		Long amountLimit = ClientBalanceUtil.getClientAmountLimit(clientId);
		// 4、判断扣减后的金额  是否超出了金额限制
		if (balance < amountLimit) {
			log.info("【策略模块 - 扣费校验】   扣除费用后超出欠费余额的限制  无法发送短信！");
			// 5、如果超过了  需要将扣除的费用增加回去  并做后续处理
			beaconCacheClient.hIncrBy(CacheConstant.CLIENT_BALANCE + clientId, BALANCE, fee);
			
			submit.setErrorMsg(String.format("%s (balance=%s, amountLimit=%d)",
					ExceptionEnums.INSUFFICIENT_BALANCE.getMessage(), balance, amountLimit));
			errorSendMsgUtil.sendWriteLog(submit);
			errorSendMsgUtil.sendPushReport(submit);
			throw new StrategyException(ExceptionEnums.INSUFFICIENT_BALANCE);
		}
		log.info("【策略模块 - 扣费校验】   扣费成功！");
	}
}
