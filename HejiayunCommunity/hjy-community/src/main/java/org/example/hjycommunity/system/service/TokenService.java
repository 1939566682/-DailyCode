package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.pojo.LoginUser;

/**
 * tokenService
 * token 验证处理
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:07
 */

public interface TokenService {
	
	/**
	 * 创建令牌
	 *
	 * @param loginUser
	 * @return: java.lang.String
	 */
	public String createToken(LoginUser loginUser);
}
