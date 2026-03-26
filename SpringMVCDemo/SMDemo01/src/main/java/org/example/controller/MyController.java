package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.pojo.Student;
import org.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-26 14:48
 */

@Controller // 放入SpringMVC容器
//@RequestMapping("/MyController")
public class MyController {

    /*
     * 请求转发的简单写法（平时使用的方式）
     * 返回值是String，表示跳转的资源路径
     */
    // 优先级最高
    @RequestMapping("/test1")
    public String testAnt1() {
        System.out.println("testAnt1");
        return "/first.jsp";
    }

    // 优先级低于test1。总长度为6，test开头，后面跟个任意内容符号
    @RequestMapping("/test?")
    public String testAnt2() {
        System.out.println("testAnt2");
        return "/first.jsp";
    }

    // 优先级低于？。一层路径，任意内容
    @RequestMapping("/*")
    public String testAnt3() {
        System.out.println("testAnt3");
        return "/first.jsp";
    }

    // 优先级低于**。任意层路径
    @RequestMapping("/**")
    public String testAnt4() {
        System.out.println("testAnt4");
        return "/first.jsp";
    }

    @RequestMapping("/testform")
    public String demo01(HttpServletRequest req) {
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(name + "---" + pwd + "---" + sex + "---" + Arrays.toString(hobbies));
        // 跳转到index.jsp中
        return "/index.jsp";
    }

    @RequestMapping("/testform2")
    public String demo02(@RequestParam(defaultValue = "18") Integer age, @RequestParam("name") String name, String pwd, String sex, String[] hobby) {
        System.out.println(name + "---" + age + "---" + pwd + "---" + sex + "---" + Arrays.toString(hobby));
        // 跳转到index.jsp中
        return "/index.jsp";
    }

    @RequestMapping("/testParam3")
    public String testParam3(@RequestParam(required = true) String name) {
        System.out.println(name);
        return "/index.jsp";
    }

    /*
     * 当使用数组进行接收时，需要数组对象名和请求参数名一致。如果不想一致，可以使用@RequestParam("hovers")定义请求参数名。
     * */
    @RequestMapping("/testParam4")
    public String testParam4(@RequestParam("hobby") String[] hobbies) {
        System.out.println(Arrays.toString(hobbies));
        return "/index.jsp";
    }

    /*
     * 在使用List进行接收时，必须在参数前面添加@RequestParam注解，注解中内容就是请求参数名
     * */
    @RequestMapping("/testParam5")
    public String testParam5(@RequestParam("hobby") List hobbies) {
        System.out.println(hobbies);
        return "/index.jsp";
    }

    @RequestMapping("/test6")
    public String test6(User user){
        System.out.println(user.toString());
        return "/index.jsp";
    }

    @RequestMapping(value="/test10")
    @ResponseBody
    public String demo01(@RequestBody Student s){
        System.out.println(s);
        return "ok";
    }

}
