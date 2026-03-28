package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-27 15:01
 */

@Controller
public class MyController2 {

    /**
     * id查询
     * @param id
     * @return
     */
    @GetMapping( "/user/{id}")
    public String test01(@PathVariable Integer id) {
        System.out.println("ID："+id);
        return "/index.jsp";
    }

    /**
     * id删除
     * @param id
     * @return
     */
    @DeleteMapping( "/user/{id}")
    public String test02(@PathVariable Integer id) {
        System.out.println("ID："+id);
        return "/index.jsp";
    }
}
