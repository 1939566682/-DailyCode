package org.example.controller;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 16:39
 */

import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Controller 容器创建DemoController对象
 * @RequestMapping("/show") 访问该方法的路径
 * @ResponseBody 将controller的方法返回的对象转换为指定的格式(JSON数据或者是XML)
 */
@Controller
public class UserController {

    // 加上这个构造方法
    public UserController() {
        System.out.println("========= UserController 加载成功！！！ =========");
    }

    // 注入service层对象：
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/findAll") // 明确指定GET请求，路径匹配访问地址
    public List<User> findAll(){
        // 方法名与Service接口完全一致
        return userService.findAllUsers();
    }
}