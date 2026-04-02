package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 16:39
 */

@EnableWebSecurity
public class SecurityConfig {

    /**
     * 采用 BCrypt 的密码加密手段
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 构建安全配置器
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // 构建者
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        // 基于构建者 构建安全管理器
        AuthenticationManager AuthenticationManager = builder.build();
        // 返回安全管理器
        return AuthenticationManager;
    }

    /**
     * 过滤器链
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(req ->req
                .mvcMatchers("/user/login").permitAll()
                // 角色和权限校验
//                .mvcMatchers("/role").hasAnyRole("管理员","用户")
//                .mvcMatchers("/perm").hasAuthority("user:add")
                .anyRequest().authenticated()
        );
        return http.build();
    }
}
