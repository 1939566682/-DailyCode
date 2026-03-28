package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 13:44
 */

@Controller
public class DemoController {

    @RequestMapping("/show")
    @ResponseBody
    public String show(){
        return "hello SpringBoot...";
    }
}
