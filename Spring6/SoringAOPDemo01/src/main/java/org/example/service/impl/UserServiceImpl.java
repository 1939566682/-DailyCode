package org.example.service.impl;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:08
 */

import org.example.service.UserService;
import org.springframework.stereotype.Service;

/** 构建UserServiceImpl的对象，可以在spring的配置文件中进行bean标签的配置
 * 也可以使用注解@Service的方式配置
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void a() {
        System.out.println("业务层：UserServiceImpl.a");
    }

    @Override
    public void b(int num) {
        System.out.println("业务层：UserServiceImpl.b-----》" + num);
//        int age = 10 / 0;
    }
}
