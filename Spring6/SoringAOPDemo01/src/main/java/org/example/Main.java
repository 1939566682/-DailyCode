package org.example;

import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-24 21:05
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 按类型获取，Spring会自动返回代理对象
        UserService us = ac.getBean(UserService.class);

        us.a(); // 不匹配切点，无通知
        us.b(18); // 匹配切点，触发所有通知
        // 打印类名，验证是否为代理对象
        System.out.println(us.getClass().getName());
    }
}