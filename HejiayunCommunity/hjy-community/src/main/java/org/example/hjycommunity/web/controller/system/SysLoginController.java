package org.example.hjycommunity.web.controller.system;

import org.example.hjycommunity.common.utils.ChainedMap;
import org.example.hjycommunity.system.domain.pojo.LoginBody;
import org.example.hjycommunity.system.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysLoginController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:22
 */

@RestController
public class SysLoginController {
	
	@Autowired
	private SysLoginService loginService;
	
	/**
	 * 登录方法
	 */
	@PostMapping("/login")
	public ChainedMap login(@RequestBody LoginBody loginBody) {
		
		//生成令牌
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(),
				loginBody.getCode(), loginBody.getUuid());
		return ChainedMap.create().set("token", token);
	}
}
