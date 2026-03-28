package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-28 17:54
 */

@Controller
public class MyController {
    @RequestMapping("/show")
    public String show(){
        return "test01";
    }

    @RequestMapping("/show2")
    public String show2(HttpServletRequest req){
        req.setAttribute("msg1","msb");
        req.setAttribute("msg2","yjx");
        req.setAttribute("msg3",50);
        req.setAttribute("msg4",new User(18,"zs"));
        List<User> list = new ArrayList<>();
        list.add(new User(11,"lili"));
        list.add(new User(22,"lulu"));
        list.add(new User(33,"feifei"));
        req.setAttribute("msg5",list);
        return "test01";
    }


    @RequestMapping("/show3")
    public String show3(int id){
        System.out.println("-------show3方法的参数id为：-------" + id);
        return "XXXX";
    }

    @RequestMapping("/show4")
    public String show4(HttpServletRequest req){
        req.setAttribute("msg","msb");
        req.setAttribute("date",new Date());
        return "test02";
    }

    @RequestMapping("/show5")
    public String show5(HttpServletRequest req){
        // 将数据存入request作用域
        req.setAttribute("req","aaa");
        // 将数据存入session作用域
        req.getSession().setAttribute("ses","bbb");
        // 将数据存入application作用域
        req.getSession().getServletContext().setAttribute("app","ccc");

        return "test03";
    }
}
