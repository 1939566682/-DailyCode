package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 15:20
 */

@Controller
public class LoginController {


    @RequestMapping("/ok")
    public String ok() {
        return "ok";
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }
}