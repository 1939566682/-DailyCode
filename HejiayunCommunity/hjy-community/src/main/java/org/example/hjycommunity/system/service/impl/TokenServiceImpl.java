package org.example.hjycommunity.system.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.hjycommunity.common.constant.Constants;
import org.example.hjycommunity.common.utils.UUIDUtils;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * TokenServiceImpl
 * token 处理器
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:08
 */

@Service
public class TokenServiceImpl implements TokenService {
	
	// 令牌自定义标识
	@Value("${token.header}")
	private String header;
	
	// 令牌秘钥
	@Value("${token.secret}")
	private String secret;
	
	// 令牌有效期（默认30分钟）
	@Value("${token.expireTime}")
	private int expireTime;
	
	/**
	 * 创建令牌
	 *
	 * @param loginUser
	 * @return: java.lang.String
	 */
	@Override
	public String createToken(LoginUser loginUser) {
		
		//设置唯一用户标识
		String userKey = UUIDUtils.randomUUID();
		loginUser.setToken(userKey);
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(Constants.LOGIN_USER_KEY, userKey);
		
		//创建token, 将用户唯一标识 通过setClaims方法 保存到token中
		String token = Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		
		return token;
	}
}
