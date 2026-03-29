package org.example.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户管理模块", description = "用户模块接口")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    @ResponseBody
    @Operation(summary = "用户模块-根据用户id查询用户信息")
    @Parameter(name = "id", description = "查询参数-用户id", required = true)
    public User select(@PathVariable Integer id) {
        return userService.findOneUser(id);
    }


    @PostMapping("/user")
    @ResponseBody
    @Operation(summary = "用户模块-保存用户信息")
    @Parameters({
            @Parameter(name = "user", description = "更新参数-用户对象", required = true)
    })
    public int save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/user")
    @ResponseBody
    @Operation(summary = "用户模块-更新用户信息")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    @Operation(summary = "用户模块-根据用户id删除用户信息")
    public int delete(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/user/list")
    @ResponseBody
    @Operation(summary = "用户模块-分页查询用户信息")
    public PageInfo<User> selectAll(int pageNum, int pageSize) {
        return userService.findAllUsers(pageNum, pageSize);
    }

}