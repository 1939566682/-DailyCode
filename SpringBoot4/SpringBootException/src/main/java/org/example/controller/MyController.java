package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 08:28
 */

@Controller
public class MyController {

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        int a = 1 / 0;
        return "test";
    }


}
