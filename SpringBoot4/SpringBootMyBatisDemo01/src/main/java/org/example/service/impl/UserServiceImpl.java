package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 16:30
 */

@Service
public class UserServiceImpl implements UserService {

    // 注入Mapper对象：
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectAllUsers();
    }
}
