package org.example.hjycommunity.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.common.constant.Constants;
import org.example.hjycommunity.common.core.exception.BaseException;
import org.example.hjycommunity.common.core.exception.UserPasswordNotMatchException;
import org.example.hjycommunity.common.utils.RedisCache;
import org.example.hjycommunity.framework.exception.CaptchaNotMatchException;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.service.SysLoginService;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * SysLoginServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 19:34
 */

@Slf4j
@Service
public class SysLoginServiceImpl implements SysLoginService {
	
	@Autowired
	private RedisCache redisCache;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	/**
	 * 带验证码登录
	 *
	 * @param username
	 * @param password
	 * @param code
	 * @param uuid
	 * @return: java.lang.String
	 */
	@Override
	public String login(String username, String password, String code, String uuid) {
		
		//1.从redis中获取验证码,判断是否正确
		String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
		log.info("log() login captcha key:{}",verifyKey);
		String captcha = redisCache.getCacheObject(verifyKey);
		log.info("log() login captcha value:{}",captcha);
		
		redisCache.deleteObject(verifyKey);
		
		if (captcha == null || !code.equalsIgnoreCase(captcha)) {
			try {
				throw new CaptchaNotMatchException();
			} catch (CaptchaNotMatchException e) {
				throw new RuntimeException(e);
			}
		}
		
		//2.进行用户认证
		Authentication authentication = null;
		try {
			//该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new UserPasswordNotMatchException();
		}
		
		//3. 获取经过身份验证的用户的主体信息
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		
		//4.调用TokenService 生成token
		return tokenService.createToken(loginUser);
	}
}
