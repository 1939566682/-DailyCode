package org.example.hjycommunity.framework.security.service;

import org.example.hjycommunity.common.utils.ServletUtils;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.domain.pojo.SysRole;
import org.example.hjycommunity.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * PermsExpressionService
 * 自定义权限校验
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-14 10:41
 */

@Component("pe")
public class PermsExpressionService {
	
	private static final String ALL_PERMISSION = "*:*:*";
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 验证用户是否具备某个权限
	 *
	 * @param permission
	 * @return
	 */
	public boolean hasPermission(String permission) {
		// 健壮判断
		if (Objects.isNull(permission) || permission.trim().isEmpty()) {
			return false;
		}
		
		if (permission.equals(ALL_PERMISSION)) {
			return true;
		}
		
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getPermissions())) {
			return false;
		}
		return hasPermissions(loginUser.getPermissions(), permission);
	}
	
	/**
	 * 判断是否包含权限
	 *
	 * @param permissions 权限列表
	 * @param permission  权限字符串
	 * @return
	 */
	private boolean hasPermissions(Set<String> permissions, String permission) {
		return permissions.contains(permission) || permissions.contains(ALL_PERMISSION);
	}
	
	/**
	 * 验证用户是否有任意权限
	 *
	 * @param permissions
	 * @return
	 */
	public boolean hasPermissions(String permissions) {
		// 健壮判断
		if (Objects.isNull(permissions) || permissions.trim().isEmpty()) {
			return false;
		}
		
		if (permissions.equals(ALL_PERMISSION)) {
			return true;
		}
		
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getPermissions())) {
			return false;
		}
		// 遍历验证
		Set<String> authorities = loginUser.getPermissions();
		for (String perm : permissions.split(",")) {
			if (!Objects.isNull(perm) && hasPermissions(authorities, perm)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否拥有某个角色
	 *
	 * @param role
	 * @return
	 */
	public boolean hasRole(String role) {
		// 健壮判断
		if (Objects.isNull(role) || role.trim().isEmpty()) {
			return false;
		}
		
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getUser().getRoles())) {
			return false;
		}
	
		for (SysRole sysRole : loginUser.getUser().getRoles()) {
			String roleKey = sysRole.getRoleKey();
			if ("admin".equals(roleKey) || role.equals(roleKey)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否拥有任意一个角色
	 * @param roles
	 * @return
	 */
	public boolean hasRoles(String roles) {
		// 健壮判断
		if (Objects.isNull(roles) || roles.trim().isEmpty()) {
			return false;
		}
		
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getUser().getRoles())) {
			return false;
		}
		
		for (String role : roles.split(",")) {
			if (!Objects.isNull(role) && hasRole(role)) {
				return true;
			}
		}
		
		return false;
	}
}
