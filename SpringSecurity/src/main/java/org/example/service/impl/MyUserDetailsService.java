package org.example.service.impl;

import org.example.mapper.PermissionMapper;
import org.example.mapper.RoleMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.MyUserDetails;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 17:24
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;

    private final String ROLE_PREFIX = "ROLE_";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、根据用户名查询用户信息
        User user = userMapper.findUserByUsername(username);

        // 2、判断查询到的用户名是为否null，为null可以抛出异常，也可以return null
        if (user == null) {
            System.out.println(username + "用户无法找到！");
            throw new BadCredentialsException("用户名或密码错误");
        }

        // 3、不为null，就将User对象中的信息封装到UserDetails中
        MyUserDetails userDetails = new MyUserDetails(username, user.getPassword());

        // 4、查询用户的角色和权限信息 并复制到 userDetails 中
        // 4.1 查询角色信息
        Set<String> roleNameSet = roleMapper.findRoleByUserId(user.getId());
        // 4.2 查询权限信息
        Set<String> permNameSet = permissionMapper.findPermissionByUserId(user.getId());
        // 4.3 声明完整的授权集合
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 4.4 遍历角色和权限都放到 authorities 中
        roleNameSet.forEach(roleName -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + roleName)));
        permNameSet.forEach(permName -> authorities.add(new SimpleGrantedAuthority(permName)));
        // 4.5 设置权限信息到 authorities
        userDetails.setAuthorities(authorities);
        System.out.println("用户 " + username + " 拥有的权限：" + authorities);

        // 5、返回UserDetails即可
        return userDetails;
    }
}
