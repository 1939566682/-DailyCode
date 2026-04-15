package org.example.hjycommunity.framework.security.filter;

import com.alibaba.fastjson.JSON;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.utils.ServletUtils;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * LogoutSuccessHandler
 * 自定义退出登录处理器
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-15 09:08
 */

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		LoginUser loginUser = tokenService.getLoginUser(request);
		// 拿到 token 删除用户的缓存数据
		if (Objects.nonNull(loginUser)) {
			String token = loginUser.getToken();
			tokenService.deleteLoginUser(token);
		}
		ServletUtils.renderString(response, JSON.toJSONString(BaseResponse.success("登出成功")));
	}
}
