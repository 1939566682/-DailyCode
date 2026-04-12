package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * WebSecurityConfigurer
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 15:18
 */

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private AuthenticationFailureHandler failureHandler;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	
	/**
	 * 定制基于 HTTP 请求的用户访问控制
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//开启基于HTTP请求访问控制
		http.authorizeHttpRequests()
				//开始配置授权，即允许哪些请求访问系统
				.mvcMatchers("/login.html", "/code/image").permitAll()
				//除上述以外,指定其他所有请求都需要经过身份验证
				.anyRequest().authenticated();
		
		//开启 form表单登录
		http.formLogin()
				.loginPage("/login.html")      //登录页面(覆盖security的)
				.loginProcessingUrl("/login")  //提交路径
				.usernameParameter("username") //表单中用户名
				.passwordParameter("password") //表单中密码
				// 使用自定义的认证成功和失败处理器
				.successHandler(successHandler)
				.failureHandler(failureHandler);
		
		//开启登出配置
		http.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutSuccessHandler(logoutSuccessHandler);
		
		http.csrf().disable();//这里先关闭 CSRF
		
	}
}
