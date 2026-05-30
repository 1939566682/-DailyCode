package org.example.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.ApiConstant;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * SignCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验短信的签名
 */

@Slf4j
@Service("sign")
public class SignCheckFilter implements CheckFilter {
	
	/**
	 * 截取签名的开始索引
	 */
	private final int SIGN_START_INDEX = 1;
	
	/**
	 * 客户存储签名的字段
	 */
	private final String CLIENT_SIGN_INFO = "signInfo";
	
	/**
	 * 签名ID
	 */
	private final String SIGN_ID = "id";
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验签名】  校验ing......");
		// 1、判断短信内容是否携带了【】
		String text = submit.getText();
		if (!text.startsWith(ApiConstant.SIGN_PREFIX) || !text.contains(ApiConstant.SIGN_SUFFIX)) {
			log.info("【接口模块 - 校验签名】  无可用签名 text = {}", text);
			throw new ApiIllegalException(ExceptionEnums.NO_AVAILABLE_SIGN);
		}
		
		// 2、将短信内容中的签名截取出来
		String sign = text.substring(SIGN_START_INDEX, text.indexOf(ApiConstant.SIGN_SUFFIX));
		if (StringUtils.isEmpty(sign)) {
			log.info("【接口模块 - 校验签名】  无可用签名 text = {}", text);
			throw new ApiIllegalException(ExceptionEnums.NO_AVAILABLE_SIGN);
		}
		
		// 3、从缓存中查询出客户绑定的签名
		Set<Map> set = beaconCacheClient.sMember(CacheConstant.CLIENT_SIGN + submit.getClientId());
		if (set == null || set.isEmpty()) {
			log.info("【接口模块 - 校验签名】  无可用签名 text = {}", text);
			throw new ApiIllegalException(ExceptionEnums.NO_AVAILABLE_SIGN);
		}
		
		// 4、判断
		for (Map map : set) {
			if (sign.equals(map.get(CLIENT_SIGN_INFO))) {
				submit.setSign(sign);
				submit.setSignId(Long.parseLong(map.get(SIGN_ID)+""));
				log.info("【接口模块 - 校验签名】  找到匹配的签名 sign = {}", sign);
				return;
			}
		}
		
		// 5、没有匹配的签名
		log.info("【接口模块 - 校验签名】  无可用签名 text = {}", text);
		throw new ApiIllegalException(ExceptionEnums.NO_AVAILABLE_SIGN);
		
	}
	
}
