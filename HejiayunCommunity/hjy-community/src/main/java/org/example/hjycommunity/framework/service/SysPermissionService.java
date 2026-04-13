package org.example.hjycommunity.framework.service;

import org.example.hjycommunity.system.domain.pojo.SysRole;
import org.example.hjycommunity.system.domain.pojo.SysUser;
import org.example.hjycommunity.system.service.SysMenuService;
import org.example.hjycommunity.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * SysPermissionService
 * 用户的权限处理
 * @author Yang QingBo
 * {@code @date} 2026-04-13 17:38
 */

@Component
public class SysPermissionService {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 获取角色数据权限
	 * @param user
	 * @return
	 */
	public Set<String> getRolePermission(SysUser user) {
		
		Set<String> rolesPermissions = new HashSet<>();
		// 判断是否有管理员权限
		if(user.isAdmin()){
			rolesPermissions.add("admin");
		}else {
			rolesPermissions = sysRoleService.selectRolePermissionsByUserId(user.getUserId());
		}
		return rolesPermissions;
	}
	
	/**
	 * 获取菜单数据权限
	 * @param user
	 * @return
	 */
	public Set<String> getMenuPermission(SysUser user) {
		Set<String> menuPermissions = new HashSet<>();
		// 判断是否有管理员权限，如果是则有全部权限
		if(user.isAdmin()){
			menuPermissions.add("*:*:*");
		}else  {
			menuPermissions = sysMenuService.selectMenuPermissionsByUserId(user.getUserId());
		}
		return menuPermissions;
	}

}
