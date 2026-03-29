package org.example;

import org.example.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceTests {

    @Autowired
    private UserService userService;

    @BeforeEach
    void before() {
        System.out.println("测试开始");
    }

    @Test
    void contextLoads() {
        System.out.println(userService.findOneUser(1));
    }

    @AfterEach
    void after(){
        System.out.println("测试结束");
    }

}
