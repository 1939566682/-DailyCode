package org.example.controller;

import org.example.pojo.User;
import org.example.service.UserService;
import org.example.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 18:25
 */

@WebServlet("/loginServlet")
public class UserServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = (UserService) applicationContext.getBean("userServiceImpl");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题：
        // 设置请求信息的解码格式：
        req.setCharacterEncoding("UTF-8");
        // 设置响应信息的编码格式：
        resp.setCharacterEncoding("UTF-8");
        // 设置浏览器的编码格式：
        resp.setContentType("text/html;charset=UTF-8");
        // 获取前台参数：
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        User user = userService.selectOneUser(uname, pwd);
        // 对参数处理：
        if (user != null){
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else{
            resp.getWriter().write("登录失败");
        }
    }
}