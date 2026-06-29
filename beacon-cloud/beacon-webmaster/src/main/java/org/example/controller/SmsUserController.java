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
import org.example.service.MenuService;
import org.example.service.SmsUserService;
import org.example.util.PageResult;
import org.example.util.R;
import org.example.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

	@Autowired
	private MenuService menuService;

	@Autowired
	private SmsUserService smsUserService;

	@PostMapping("/login")
	public ResultVO<Object> login(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.info("【认证操作】  参数不合法 userDTO = {}", userDTO);
			return R.error(ExceptionEnums.PARAMETER_ERROR);
		}

		String realKaptcha = SecurityUtils.getSubject().getSession().getAttribute(WebMasterConstant.KAPTCHA).toString();
		if (!userDTO.getCaptcha().equalsIgnoreCase(realKaptcha)) {
			log.info("【认证操作】  验证码不正确，kaptcha = {}，realKaptcha = {}", userDTO.getCaptcha(), realKaptcha);
			return R.error(ExceptionEnums.KAPTCHA_ERROR);
		}

		UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRememberMe());
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			log.info("【认证操作】  用户名或密码错误 e = {}", e.getMessage());
			return R.error(ExceptionEnums.AUTHENTICATION_ERROR);
		}
		return R.ok();
	}

	/**
	 * 查询当前登录用户的信息（index.js使用）
	 */
	@GetMapping("/user/info")
	public Map<String, Object> info() {
		Subject subject = SecurityUtils.getSubject();
		SmsUser smsUser = (SmsUser) subject.getPrincipal();
		if (smsUser == null) {
			log.info("【获取登录用户信息】  用户未登录！！");
			Map<String, Object> map = new HashMap<>();
			map.put("code", ExceptionEnums.NOT_LOGIN.getCode());
			map.put("msg", ExceptionEnums.NOT_LOGIN.getMessage());
			return map;
		}

		Map<String, Object> data = new HashMap<>();
		data.put("nickname", smsUser.getNickname());
		data.put("username", smsUser.getUsername());
		return R.okNamed("user", data);
	}

	/**
	 * 根据ID查询用户信息（user.js编辑时使用）
	 */
	@GetMapping("/user/info/{id}")
	public Map<String, Object> infoById(@PathVariable Integer id) {
		Map<String, Object> user = smsUserService.findById(id);
		if (user == null) {
			Map<String, Object> err = new LinkedHashMap<>();
			err.put("code", ExceptionEnums.USER_NOT_FOUND.getCode());
			err.put("msg", ExceptionEnums.USER_NOT_FOUND.getMessage());
			return err;
		}
		return R.okNamed("user", user);
	}

	/**
	 * 分页查询用户列表
	 */
	@GetMapping("/user/list")
	public ResultVO<Object> list(@RequestParam(defaultValue = "0") int offset,
	                              @RequestParam(defaultValue = "10") int limit,
	                              @RequestParam(required = false) String search) {
		PageResult<Map<String, Object>> result = smsUserService.list(offset, limit, search);
		return R.ok(result.getTotal(), result.getRows());
	}

	/**
	 * 新增用户
	 */
	@PostMapping("/user/save")
	public ResultVO<Object> save(@RequestBody Map<String, Object> user) {
		smsUserService.save(user);
		return R.ok();
	}

	/**
	 * 更新用户
	 */
	@PostMapping("/user/update")
	public ResultVO<Object> update(@RequestBody Map<String, Object> user) {
		smsUserService.update(user);
		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@PostMapping("/user/del")
	public ResultVO<Object> del(@RequestBody Integer[] ids) {
		smsUserService.delete(ids);
		return R.ok();
	}

	/**
	 * 修改密码
	 */
	@PostMapping("/user/password")
	public ResultVO<Object> password(@RequestBody Map<String, String> params) {
		Subject subject = SecurityUtils.getSubject();
		SmsUser smsUser = (SmsUser) subject.getPrincipal();
		if (smsUser == null) {
			return R.error(ExceptionEnums.NOT_LOGIN);
		}
		String newPassword = params.get("newPassword");
		if (newPassword == null || newPassword.trim().isEmpty()) {
			return R.error(ExceptionEnums.PASSWORD_EMPTY);
		}
		smsUserService.updatePassword(smsUser.getId(), newPassword.trim());
		return R.ok();
	}

	/**
	 * 查询当前用户的菜单信息
	 */
	@GetMapping("/menu/user")
	public ResultVO<Object> menuUser() {
		SmsUser smsUser = (SmsUser) SecurityUtils.getSubject().getPrincipal();
		if (smsUser == null) {
			log.info("【获取用户菜单信息】  用户未登录！！");
			return R.error(ExceptionEnums.NOT_LOGIN);
		}
		List<Map<String, Object>> data = menuService.findUserMenu(smsUser.getId());
		if (data == null) {
			log.error("【获取用户菜单信息】  查询用户菜单失败！！  id = {}", smsUser.getId());
			return R.error(ExceptionEnums.USER_MENU_ERROR);
		}
		return R.ok(data);
	}
}
