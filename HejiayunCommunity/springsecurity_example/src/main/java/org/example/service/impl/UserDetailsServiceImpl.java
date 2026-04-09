package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.LoginUser;
import org.example.entity.SysUser;
import org.example.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        // todo 查询用户权限信息


        log.info("log() -> 用户查询成功，封装为LoginUser");
        return new LoginUser(sysUser);
    }
}