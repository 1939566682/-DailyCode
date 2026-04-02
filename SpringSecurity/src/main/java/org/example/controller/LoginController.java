package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 16:31
 */

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping("/user/login")
    public String login(String username, String password) {
        // 1、参数校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "username or password is empty";
        }

        // 2、封装参数 -> token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // 3、执行认证 -> 认证管理器
        Authentication user = null;
        try {
            user = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        if (user == null) {
            return "username or password is incorrect";
        }


        // 4、认证成功  把用户信息放到 SecurityContext 再把 SecurityContext 放到 SecurityContextHolder 里
        SecurityContextHolder.getContext().setAuthentication(user);

        return "success! 登录成功，当前用户：" + user.getName();
    }

    /**
     * 角色授权
     * @return
     */
    @GetMapping("/role")
    @PreAuthorize("hasAnyRole('管理员','用户')")
    public String role(){
        return "role";
    }

    /**
     * 权限授权
     * @return
     */
    @GetMapping("/perm")
    @PreAuthorize("hasAnyAuthority('xxx:yyy','user:select')")
    public String perm(){
        return "perm";
    }

}
