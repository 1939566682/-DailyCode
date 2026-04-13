package org.example.hjycommunity.web.test;

import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.core.exception.BaseException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * UserController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-03 20:07
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/queryUserById")
    public BaseResponse<User> queryUserById(String userId) {
        if (userId != null) {
            return BaseResponse.success(new User("001", "zhangsan"));
        } else {
            return BaseResponse.fail("查询失败");
        }
    }

    @RequestMapping("/addUser")
    public BaseResponse<User> addUser(@Validated User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return BaseResponse.fail(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        return BaseResponse.success(user);
    }

    @RequestMapping("/queryUser")
    public BaseResponse<User> queryUser(User user) {
        throw new BaseException(500,"测试异常类");
    }
}
