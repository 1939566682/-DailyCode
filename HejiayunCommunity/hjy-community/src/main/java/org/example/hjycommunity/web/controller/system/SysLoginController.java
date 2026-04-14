package org.example.hjycommunity.web.controller.system;

import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.utils.ChainedMap;
import org.example.hjycommunity.common.utils.ServletUtils;
import org.example.hjycommunity.framework.service.SysPermissionService;
import org.example.hjycommunity.system.domain.pojo.LoginBody;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.domain.pojo.SysMenu;
import org.example.hjycommunity.system.domain.pojo.SysUser;
import org.example.hjycommunity.system.domain.vo.RouterVo;
import org.example.hjycommunity.system.service.SysLoginService;
import org.example.hjycommunity.system.service.SysMenuService;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

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
	
	@Autowired
	private SysPermissionService permissionService;
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private SysMenuService sysMenuService;
	
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
	
	/**
	 * 获取用户信息及其权限信息
	 * @return
	 */
	@GetMapping("/getInfo")
	public ChainedMap getInfo(){
		
		// 用户信息
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		
		// 角色集合
		Set<String> roles = permissionService.getRolePermission(user);
		
		// 权限集合
		Set<String> permissions = permissionService.getMenuPermission(user);
		
		ChainedMap map = ChainedMap.create().set("code", 200).set("msg", "操作成功");
		map.put("user", user);
		map.put("roles", roles);
		map.put("permissions", permissions);
		
		return map;
	}
	
	/**
	 * 获取路由信息
	 * @return: 路由信息
	 */
	@GetMapping("/getRouters")
	public BaseResponse<List<RouterVo>> getRouters(){
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		// 获取菜单列表
		List<SysMenu> menus = sysMenuService.selectMenuTreeByUserId(user.getUserId());
		// 将获取到的菜单列表转换为 前端所需要的路由列表
		List<RouterVo> routerVos = sysMenuService.buildMenuTree(menus);
		return BaseResponse.success(routerVos);
	}
}
