package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.example.constant.WebMasterConstant;
import org.example.dto.UserDTO;
import org.example.entity.SmsUser;
import org.example.enums.ExceptionEnums;
import org.example.util.R;
import org.example.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * SmsUserController
 *
 * @author Yang QingBo
 * @date 2026-06-15 17:18
 * @description 认证 注册等基于用户的操作接口
 */


@Slf4j
@RestController
@RequestMapping("/sys")
public class SmsUserController {
	
	@PostMapping("/login")
	public ResultVO<Object> login(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
		// 1、请求参数的非空校验
		if (bindingResult.hasErrors()) {
			log.info("【认证操作】  参数不合法 userDTO = {}", userDTO);
			return R.error(ExceptionEnums.PARAMETER_ERROR);
		}
		
		// 2、基于验证码校验请求是否合理
		String realKaptcha = SecurityUtils.getSubject().getSession().getAttribute(WebMasterConstant.KAPTCHA).toString();
		if(!userDTO.getCaptcha().equalsIgnoreCase(realKaptcha)) {
			log.info("【认证操作】  验证码不正确，kaptcha = {}，realKaptcha = {}", userDTO.getCaptcha(), realKaptcha);
			return R.error(ExceptionEnums.KAPTCHA_ERROR);
		}
		
		// 3、基于用户民和密码做Shiro的认证操作
		UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRememberMe());
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			// 4、根据Shiro的认证  返回响应信息
			log.info("【认证操作】  用户名或密码错误 e = {}", e.getMessage());
			return R.error(ExceptionEnums.AUTHENTICATION_ERROR);
		}
		// 认证成功
		return R.ok();
	}
	
	/**
	 * 查询登录用户的信息
	 */
	@GetMapping("/user/info")
	public ResultVO<Object> info(){
		//1、基于SecurityUtils获取用户信息
		Subject subject = SecurityUtils.getSubject();
		SmsUser smsUser = (SmsUser) subject.getPrincipal();
		if(smsUser == null){
			log.info("【获取登录用户信息】  用户未登录！！");
			return R.error(ExceptionEnums.NOT_LOGIN);
		}
		
		//2、封装结果返回
		Map<String,Object> data = new HashMap<>();
		data.put("nickname",smsUser.getNickname());
		data.put("username",smsUser.getUsername());
		return R.ok(data);
	}
}
