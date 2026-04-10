package org.example.expression;

import org.example.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MyExpression
 * 自定义权限校验
 * @author Yang QingBo
 * {@code @date} 2026-04-10 19:30
 */

@Component("my_ex")
public class MyExpression {

    /**
     * 自定义 hasAuthority
     * @param authority
     * @return
     */
    public  boolean hasAuthority(String authority) {

        // 获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        // 判断集合中是否有 authority
        return permissions.contains(authority);
    }

}

