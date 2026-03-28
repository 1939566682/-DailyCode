package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-27 17:28
 */

@Controller
public class MyController {

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
