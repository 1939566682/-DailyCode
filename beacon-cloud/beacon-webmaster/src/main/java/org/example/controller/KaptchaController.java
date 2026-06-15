package org.example.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * KaptchaController
 *
 * @author Yang QingBo
 * @date 2026-06-15 16:42
 * @description
 */

@Controller
public class KaptchaController {
	
	private final String JPG = "jpg";
	
	@Autowired
	private DefaultKaptcha kaptcha;
	
	@GetMapping("/captcha.jpg")
	public void captcha(HttpServletResponse resp) throws Exception {
		// 1、验证码图片不需要做存储和缓存
		resp.setHeader("Cache-Control", "no-store, no-cache");
		// 2、设置响应头信息
		resp.setContentType("image/jpg");
		// 3、生成验证码
		String kaptchaText = kaptcha.createText();
		// 4、基于文字生成对应的图片
		BufferedImage kaptchaImage = kaptcha.createImage(kaptchaText);
		// 5、写回验证码图片信息
		try {
			ServletOutputStream outputStream = resp.getOutputStream();
			ImageIO.write(kaptchaImage, JPG, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
