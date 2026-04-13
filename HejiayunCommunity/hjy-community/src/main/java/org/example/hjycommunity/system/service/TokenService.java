package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.pojo.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * tokenService
 * token 验证处理
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:07
 */

public interface TokenService {
	/**
	 * 创建令牌
	 * @param loginUser
	 * @return java.lang.String
	 */
	String createToken(LoginUser loginUser);
	
	/**
	 * 缓存用户信息 & 刷新令牌的有效期
	 * @param loginUser
	 */
	void refreshToken(LoginUser loginUser);
	
	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	LoginUser getLoginUser(HttpServletRequest request);
	
	/**
	 * 验证令牌有限期 & 实现自动刷新缓存
	 * @param loginUser
	 */
	void verifyToken(LoginUser loginUser);
	
	/**
	 * 设置用户身份信息
	 * @param loginUser
	 */
	void setLoginUser(LoginUser loginUser);
	
	/**
	 * 删除用户
	 * @param token
	 */
	void deleteLoginUser(String token);
}
