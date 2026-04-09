package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.ResponseResult;
import org.example.entity.LoginUser;
import org.example.entity.SysUser;
import org.example.service.LoginService;
import org.example.utils.JwtUtil;
import org.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * LoginServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:26
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<Map<String, String>> login(SysUser sysUser) {
        log.info("log() -> 开始执行用户登录逻辑");
        log.info("log() -> 当前登录用户名：{}", sysUser.getUserName());

        // 封装认证信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword());

        // 认证
        log.info("log() -> 调用SpringSecurity认证管理器");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 认证失败
        if (Objects.isNull(authentication)) {
            log.error("log() -> 登录失败：用户名或密码错误");
            throw new RuntimeException("用户名或密码错误");
        }

        // 认证成功
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getSysUser().getUserId();
        log.info("log() -> 认证成功，用户ID：{}", userId);

        // 生成JWT
        String jwt = JwtUtil.createJWT(userId.toString());
        log.info("log() -> JWT令牌生成完成");

        // 存入Redis
        String redisKey = "login:" + userId;
        redisCache.setCacheObject(redisKey, loginUser);
        log.info("log() -> 用户信息已存入Redis，key：{}", redisKey);

        // 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        log.info("log() -> 登录成功，返回token给前端");

        return ResponseResult.success(map);
    }

    @Override
    public ResponseResult<String> logout() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // 认证失败
        if (Objects.isNull(authentication)) {
            log.error("log() -> 登出：获取用户认证信息失败");
            throw new RuntimeException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getSysUser().getUserId();

        // 删除 redis 中的用户信息
        redisCache.deleteObject("login:" + userId);
        return ResponseResult.success("注销成功");
    }
}