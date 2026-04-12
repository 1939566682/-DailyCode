package org.example.hjycommunity.system.service;

import org.example.hjycommunity.framework.exception.CaptchaNotMatchException;
import org.example.hjycommunity.system.domain.pojo.LoginBody;

/**
 * SysLoginservice
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 19:33
 */

public interface SysLoginService {
	
	/**
	 * 登录接口
	 */
	String login(String username,String password,String code,String uuid);
}
