package org.example.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.CacheConstant;
import org.example.constant.RabbitMQConstant;
import org.example.constant.SmsConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.StrategyException;
import org.example.filter.StrategyFilter;
import org.example.model.StandardReport;
import org.example.model.StandardSubmit;
import org.example.util.ErrorSendMsgUtil;
import org.example.util.HutoolDFAUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DirtyWordHutoolDFAStrategyFilter
 *
 * @author Yang QingBo
 * @date 2026-06-02 23:14
 * @description
 */

@Service(value = "hutoolDFADirtyWord")
@Slf4j
public class DirtyWordHutoolDFAStrategyFilter implements StrategyFilter {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Autowired
	private ErrorSendMsgUtil errorSendMsgUtil;
	
	@Override
	public void strategy(StandardSubmit submit) {
		log.info("【策略模块-敏感词校验】   校验ing…………");
		//1、 获取短信内容
		String text = submit.getText();
		
		//2、 调用DFA查看敏感词
		List<String> dirtyWords = HutoolDFAUtil.getDirtyWord(text);
		
		//4、 根据返回的set集合，判断是否包含敏感词
		if (dirtyWords != null && !dirtyWords.isEmpty()) {
			//5、 如果有敏感词，抛出异常 / 其他操作。。
			log.info("【策略模块-敏感词校验】   短信内容包含敏感词信息， dirtyWords = {}", dirtyWords);
			// 封装错误信息
			// ========发送写日志================
			errorSendMsgUtil.sendWriteLog(submit, dirtyWords);
			// ====发送状态报告前 需要将report对象进行数据封装========
			errorSendMsgUtil.sendPushReport(submit);
			// ======抛出异常=========
			throw new StrategyException(ExceptionEnums.HAVE_DIRTY_WORD);
			
			// 还需要做其他处理
			// TODO 后期当敏感词做了写操作后  狐妖同步到Redis  并且通知策略模块  针对WordTree做修改
		}
		log.info("【策略模块-敏感词校验】   校验通过  没有敏感词信息");
		
	}
	

	
	
}
