package org.example.hjycommunity.system.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.hjycommunity.common.constant.Constants;
import org.example.hjycommunity.common.utils.RedisCache;
import org.example.hjycommunity.common.utils.UUIDUtils;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * TokenServiceImpl
 * token 处理器
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:08
 */

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private RedisCache redisCache;
	
	// 令牌自定义标识
	@Value("${token.header}")
	private String header;
	
	// 令牌秘钥
	@Value("${token.secret}")
	private String secret;
	
	// 令牌有效期（默认30分钟）
	@Value("${token.expireTime}")
	private int expireTime;
	// 毫秒
	private static final Long MILLS_SECOND = 1000L;
	// 分钟
	private static final Long MILLS_MINUTE = 60 * MILLS_SECOND;
	// 20分钟
	private static final Long MILLS_MINUTE_TEN = 20 * MILLS_SECOND;
	// 小时
	private static final Long MILLS_HOUR = 60 * MILLS_MINUTE;
	
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
		
		// 保存用户信息 & 刷新令牌的有效期
		refreshToken(loginUser);
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(Constants.LOGIN_USER_KEY, userKey);
		
		//创建token, 将用户唯一标识 通过setClaims方法 保存到token中
		String token = Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		
		return token;
	}
	
	/**
	 * 缓存用户信息 & 刷新令牌的有效期
	 *
	 * @param loginUser
	 */
	@Override
	public void refreshToken(LoginUser loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		// 过期时间 30min
		loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLS_SECOND);
		// 根据 uuid 将 loginUser 缓存
		String userKey = getTokenKey(loginUser.getToken());
		redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
	}
	
	/**
	 * 获取用户信息
	 *
	 * @param request
	 * @return
	 */
	@Override
	public LoginUser getLoginUser(HttpServletRequest request) {
		String token = getToken(request);
		if (StringUtils.isEmpty(token)) {
			Claims claims = parserToken(token);
			// 解析对应的用户信息
			String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
			String userKey = getTokenKey(uuid);
			return redisCache.getCacheObject(userKey);
		}
		return null;
	}
	
	/**
	 * 验证令牌有限期 & 实现自动刷新缓存
	 *
	 * @param loginUser
	 */
	@Override
	public void verifyToken(LoginUser loginUser) {
		Long expireTime = loginUser.getExpireTime();
		long currentTimeMillis = System.currentTimeMillis();
		// 相差不足 20 分钟 则自动刷新缓存
		if ((expireTime - currentTimeMillis) <= MILLS_MINUTE_TEN) {
			refreshToken(loginUser);
		}
	}
	
	/**
	 * 设置用户信息
	 * @param loginUser
	 */
	@Override
	public void setLoginUser(LoginUser loginUser) {
		if (!Objects.isNull(loginUser) && !StringUtils.isEmpty(loginUser.getToken())) {
			refreshToken(loginUser);
		}
	}
	
	/**
	 * 删除用户
	 * @param token
	 */
	@Override
	public void deleteLoginUser(String token) {
		if (!StringUtils.isEmpty(token)) {
			String tokenKey = getTokenKey(token);
			redisCache.deleteObject(tokenKey);
		}
	}
	
	/**
	 * 拼接 tokenKey
	 *
	 * @param uuid
	 * @return
	 */
	private String getTokenKey(String uuid) {
		return Constants.LOGIN_USER_KEY + uuid;
	}
	
	/**
	 * 从请求头中获取 token
	 *
	 * @param request
	 * @return
	 */
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(this.header);
		// JWT 标准写法 Authorization: Bearer aaa.bb.cc
		if (!StringUtils.isEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
			token = token.replace(Constants.TOKEN_PREFIX, "");
		}
		return token;
	}
	
	
	/**
	 * 从令牌中获取数据的声明
	 *
	 * @param token
	 * @return
	 */
	private Claims parserToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}
	
	
}
