package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang QingBo
 * {@code @date} 2026-04-01 08:57
 */

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello!!";
    }
}
