package org.example.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.example.util.PhoneFormatCheckUtil;
import org.springframework.stereotype.Service;

/**
 * MoblieCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验手机号的格式合法性
 */

@Slf4j
@Service("mobile")
public class MoblieCheckFilter implements CheckFilter {
	
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验手机号】  校验ing......");
		String mobile = submit.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			log.info("【接口模块 - 校验手机号】  手机号为空");
			return;
		}
		
		if (PhoneFormatCheckUtil.isChinaPhone(mobile)) {
			log.info("【接口模块 - 校验手机号】  校验通过 手机号格式合法 mobile = {}", mobile);
			return;
		}
		log.info("【接口模块 - 校验手机号】  手机号格式不正确 mobile = {}", mobile);
		throw new ApiIllegalException(ExceptionEnums.INVALID_MOBILE_FORMAT);
	}
	
}
