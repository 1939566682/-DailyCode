package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.MyUserDetails;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 17:24
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、根据用户名查询用户信息
        User user = userMapper.findUserByUsername(username);

        //2、判断查询到的用户名是为否null，为null可以抛出异常，也可以return null
        if(user == null){
            System.out.println(username + "用户无法找到！");
            throw new BadCredentialsException("用户名或密码错误");
        }

        //3、不为null，就将User对象中的信息封装到UserDetails中

        //4、返回UserDetails即可
        return new MyUserDetails(user.getUsername(), user.getPassword());
    }
}
