package org.example.hjycommunity.framework.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JwtAuthenticationTokenFilter
 * Token 过滤器
 * 验证 token 的有效性
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 11:45
 */

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {
		
		log.info(" log() JwtAuthenticationTokenFilter doFilterInternal");
		
		// 从 redis 中拿到用户信息
		LoginUser loginUser = tokenService.getLoginUser(request);
		// 获取用户认证对象
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		/**
		 * Authentication 是线程级别的
		 * 每一个请求都对应着一个单独的 Authentication
		 */
		if (!Objects.isNull(loginUser) && Objects.isNull(authentication)) {
			log.info("log() 解析到用户：{}",loginUser.getUsername());
			// 验证令牌有限期 缓存用户信息 & 刷新令牌的有效期
			tokenService.verifyToken(loginUser);
			
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
			// 设置当前身份验证相关的相信信息
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}
}
