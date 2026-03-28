package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-27 14:17
 */

@Controller
public class MyController {

    @RequestMapping( "/testWebInf1")
    public String testWebInf1() {
        System.out.println("走入 testWebInf1 方法");
        // 请求转发到WEB-INF/jsp/hi01.jsp
        return "hi01";
    }

    @RequestMapping( "/testWebInf2")
    public String testWebInf2() {
        System.out.println("走入 testWebInf2 方法");
        // 请求转发到WEB-INF/jsp/hi02.jsp
        return "hi02";
    }

    @RequestMapping( "/testWebInf3")
    public String testWebInf3() {
        System.out.println("走入 testWebInf3 方法");
        // 请求转发到WEB-INF/jsp/hi03.jsp
        return "hi03";
    }

    @RequestMapping( "/testWebInf4")
    public String testWebInf4() {
        System.out.println("走入 testWebInf4 方法");
        return "forward:/index.jsp";
    }

}
