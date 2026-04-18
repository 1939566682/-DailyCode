package org.example.hjycommunity.common.utils;

import org.example.hjycommunity.common.constant.HttpStatus;
import org.example.hjycommunity.common.core.exception.CustomException;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityUtils
 * 用户信息相关操作工具类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-16 14:32
 */

public class SecurityUtils {
	
	/**
	 * 获取用户账户
	 */
	public static String getUserName() {
		try {
			return getLoginUser().getUsername();
		} catch (Exception e) {
			throw new CustomException(HttpStatus.UNAUTHORIZED, "获取用户账户异常");
		}
	}
	
	/**
	 * 获取完整登录用户信息
	 */
	public static LoginUser getLoginUser() {
		try {
			return (LoginUser) getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new CustomException(HttpStatus.UNAUTHORIZED, "获取用户信息异常");
		}
	}
	
	/**
	 * 获取Authentication
	 */
	public static Authentication getAuthentication() {
		
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * 生成 BCryptPasswordEncoder 密码
	 *
	 * @param password
	 * @return: java.lang.String
	 */
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
