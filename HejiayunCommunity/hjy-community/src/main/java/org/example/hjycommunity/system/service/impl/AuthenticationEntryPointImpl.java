package org.example.hjycommunity.system.service.impl;

import com.alibaba.fastjson.JSON;
import org.example.hjycommunity.common.constant.HttpStatus;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * AuthenticationEntryPointImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:40
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
	
	@Serial
	private static final long serialVersionUID = 4452925936594111023L;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		//状态码 401
		int code = HttpStatus.UNAUTHORIZED;
		ServletUtils.renderString(response, JSON.toJSONString(BaseResponse.fail(Integer.toString(code),"认证失败，无法访问系统资源")));
	}
}
