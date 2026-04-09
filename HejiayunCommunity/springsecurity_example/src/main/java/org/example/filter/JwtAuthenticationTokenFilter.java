package org.example.filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.LoginUser;
import org.example.utils.JwtUtil;
import org.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JwtAuthenticationTokenFilter
 * 自定义认证过滤器,用来校验用户请求中携带的Token
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 18:40
 */

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    /**
     * 封装过滤器的执行逻辑
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1.从请求头中获取token
        String token = request.getHeader("token");

        //2.判断token是否为空,为空直接放行
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            //return的作用是返回响应的时候,避免走下面的逻辑
            return;
        }

        //3.解析Token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.warn("token解析异常：",e);
            throw new RuntimeException("非法token");
        }

        //4.从redis中获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        //5.将用户新保存到SecurityContextHolder,以便后续的访问控制和授权操作使用。
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //6.放行
        filterChain.doFilter(request, response);
    }


}