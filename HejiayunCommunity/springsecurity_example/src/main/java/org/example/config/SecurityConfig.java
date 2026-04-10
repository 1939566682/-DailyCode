package org.example.config;

import org.example.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:22
 */

/**
 * 启用全局方法级别的安全控制
 *   设置 prePostEnabled = true
 *   pre 表示方法执行前进行授权校验
 *   post 表示方法执行后进行授权校验
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入 AuthenticationManager,供外部类使用
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //该方法用于配置 HTTP 请求的安全处理
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 允许跨域
        http.cors();

        // 关闭csrf
        http.csrf().disable();

        http
                // 不会创建会话，每个请求都将被视为独立的请求。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 定义请求授权规则
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                .antMatchers("/yes").hasAnyAuthority("system:user:list") // 配置形式的权限控制
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 将自定义认证过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                // 配置认证异常处理器 401
                .authenticationEntryPoint(authenticationEntryPoint)
                // 配置授权异常处理器 403
                .accessDeniedHandler(accessDeniedHandler);


    }
}