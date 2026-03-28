package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-27 15:30
 */

@Controller
public class MyController3 {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile photo) throws IOException {
        // 将传过来的图片放在d盘目录下,名字为原始资源名称
        File dir = new File("E:/images/");
        // 如果不存在则创建目录
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 获取文件原始名字
        String originalFilename = photo.getOriginalFilename();
        System.out.println(originalFilename);
        // 文件存储位置拼接：意味着：存储在E:/images/a.png
        File file = new File(dir, originalFilename);
        //  文件保存
        photo.transferTo(file);
        return "ok";
    }

    @RequestMapping("/upload2")
    @ResponseBody
    public String upload2(MultipartFile photo, HttpServletRequest req) throws IOException {
        String realPath = req.getServletContext().getRealPath("/images");

        File dir = new File(realPath);
        // 如果不存在则创建目录
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 获取文件原始名字
        String originalFilename = photo.getOriginalFilename();
        System.out.println(originalFilename);
        // 文件存储位置拼接：意味着：存储在E:/images/a.png
        File file = new File(dir, originalFilename);
        //  文件保存
        photo.transferTo(file);
        return "ok";
    }

    @RequestMapping("/upload3")
    @ResponseBody
    public Map<String, Object> upload3(MultipartFile photo, HttpServletRequest req) throws IOException {
        String realPath = req.getServletContext().getRealPath("/images");

        File dir = new File(realPath);
        // 如果不存在则创建目录
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 获取文件原始名字
        String originalFilename = photo.getOriginalFilename();

        String suffix = "";
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 创建文件唯一名字：
        // 方式1：利用时间戳+随机数+后缀：
//        long time01 = System.currentTimeMillis();
//        String fileName = time01 + new Random().nextInt(1000) + suffix;

        // 方式2：UUID
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + suffix;


        System.out.println(fileName);
        File file = new File(dir, fileName);
        //  文件保存
        photo.transferTo(file);
        // 新增代码：
        Map<String, Object> map = new HashMap<>();
        map.put("msg",1);// 上传图片成功返回：码1
        map.put("fileName",fileName);// 存入文件名字
        // 新增代码：
        return map;
    }
}
