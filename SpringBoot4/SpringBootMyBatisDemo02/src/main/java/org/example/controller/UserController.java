package org.example.controller;

import com.github.pagehelper.PageInfo;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 19:06
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User select(@PathVariable Integer id){
        return userService.findOneUser(id);
    }

    @PostMapping("/user")
    @ResponseBody
    public int save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/user")
    @ResponseBody
    public int update(@RequestBody User user){
        return userService.update(user);
    }
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public int delete(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/user/list")
    @ResponseBody
    public PageInfo<User> selectAll(int pageNum, int pageSize){
        return userService.findAllUsers(pageNum,pageSize);
    }

}