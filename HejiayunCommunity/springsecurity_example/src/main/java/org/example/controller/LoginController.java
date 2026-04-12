package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.common.ResponseResult;
import org.example.entity.LoginBody;
import org.example.entity.SysUser;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:25
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseResult<Map<String, String>> login(@RequestBody SysUser user) {
		log.info("log() -> 接收前端登录请求，用户名：{}", user.getUserName());
		return loginService.login(user);
	}
	
	/**
	 * 登录方法
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@PostMapping("/login")
	public ResponseResult<Map<String, Object>> login(@RequestBody LoginBody loginBody) {
		// 生成令牌
		String token = loginService.login(loginBody.getUserName(), loginBody.getPassword(), loginBody.getCode(),
				loginBody.getUuid());
		
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		return ResponseResult.success("登录成功", map);
	}
	
	/**
	 * 用户登出
	 *
	 * @return
	 */
	@GetMapping("/logout")
	public ResponseResult<String> logout() {
		return loginService.logout();
	}
}
