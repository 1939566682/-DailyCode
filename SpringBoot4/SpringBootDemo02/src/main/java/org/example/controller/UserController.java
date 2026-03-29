package org.example.controller;

import jakarta.validation.Valid;
import org.example.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 17:57
 */

//@Controller
@RestController
public class UserController {

    @PostMapping("/user")
//    @ResponseBody
    public int save(@RequestBody @Valid User user) {
        return 0;
    }
}
