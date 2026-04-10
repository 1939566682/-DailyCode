package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.LoginUser;
import org.example.entity.Menu;
import org.example.entity.SysUser;
import org.example.mapper.MenuMapper;
import org.example.mapper.RoleMapper;
import org.example.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * UserDetailsServiceImpl
 * 实现从数据库根据用户名检索用户的信息
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:04
 */

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("log() -> SpringSecurity 根据用户名查询用户：{}", username);

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);

        if (sysUser == null) {
            log.error("log() -> 数据库中未找到该用户：{}", username);
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 查询用户权限信息,添加到 LoginUser 中 ，这里先写死封装到 List 中
//        ArrayList<String> list = new ArrayList<>(List.of("test"));
        // 从数据库查询用户权限信息  保存到 LoginUser
        List<String> perms = menuMapper.selectPermsByUserId(sysUser.getUserId());
        System.out.println("--");
        for (String perm : perms) {
            System.out.println(perm);
        }
        System.out.println("--");

        // 获取当前用户角色信息
        List<String> roles = roleMapper.selectRolesByUserId(sysUser.getUserId());


        log.info("log() -> 用户查询成功，封装为LoginUser");
        return new LoginUser(sysUser,perms,roles);
    }
}