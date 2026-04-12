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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()  //开始配置授权，即允许哪些请求访问系统
                .mvcMatchers("/login.html").permitAll()  //指定哪些请求路径允许访问
                .mvcMatchers("/index").permitAll()   //指定哪些请求路径允许访问
                .anyRequest().authenticated() //除上述以外,指定其他所有请求都需要经过身份验证
                .and()
                .formLogin()  //配置表单登录
                .successHandler(successHandler)
//                .failureHandler(new MyAuthenticationFailureHandler())
                .failureHandler(failureHandler)
                .and()
                .logout() // 开启注销配置
//                .logoutUrl("/logout") //退出登录地址
                .invalidateHttpSession(true) // 退出时是否是 session 失效，默认值为 true
                .clearAuthentication(true) //  退出时是否清除认证信息，默认值为 true
                .logoutSuccessHandler(logoutSuccessHandler)
//                .logoutSuccessUrl("/login.html") // 退出登录时跳转地址，默认是 GET 请求
                .and()
                .csrf().disable();//这里先关闭 CSRF
    }
}
