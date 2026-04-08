package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloSecurityController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-08 16:37
 */

@RestController
public class HelloSecurityController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Security!";
    }
}
