package org.example.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.example.client.BeaconCacheClient;
import org.example.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Map;
import java.util.Set;

/**
 * MonitorClientBalanceTask
 *
 * @author Yang QingBo
 * @date 2026-06-13 22:46
 * @description
 */

@Component
public class MonitorClientBalanceTask {
	
	// 客户余额限制，小于500大洋，直接发送信息
	private final long balanceLimit = 500000;
	
	private final String CLIENT_BALANCE_PATTERN = "client_balance:*";
	
	private final String BALANCE = "balance";
	
	private final String EMAIL = "extend1";
	
	private String text = "客户您好，你在【烽火短信平台】内的余额仅剩余%s元，请您及时补充金额，避免影响你您的短信发送！！";
	
	@Autowired
	private BeaconCacheClient cacheClient;
	
	@Autowired
	private MailUtil mailUtil;
	
	@XxlJob("monitorClientBalanceTask")
	public void monitor() throws MessagingException {
		//1、查询客户余额信息。
		Set<String> keys = cacheClient.keys(CLIENT_BALANCE_PATTERN);
		
		for (String key : keys) {
			Map map = cacheClient.hGetAll(key);
			Long balance =  Long.parseLong(String.valueOf(map.get(BALANCE)));
			String email = map.get(EMAIL).toString();
			//2、判断余额是否小于限制，小于发送邮件
			if(balance < balanceLimit){
				mailUtil.sendEmail(email,"【烽火短信平台】提醒您余额不足。",String.format(text,balance/1000));
			}
		}
	}
	
}
