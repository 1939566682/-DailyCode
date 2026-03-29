package org.example;


import org.example.pojo.User;
import org.example.service.UserService;

import java.util.List;

@SpringBootTest
class SpringBootMyBatisPlusDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        // 查询全部USer：
        List<User> list = userService.list();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
