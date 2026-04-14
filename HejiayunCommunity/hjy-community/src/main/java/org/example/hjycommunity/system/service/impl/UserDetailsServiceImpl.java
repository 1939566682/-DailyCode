package org.example.hjycommunity.system.service.impl;

/**
 * UserDetailsServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:35
 */

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.common.core.exception.BaseException;
import org.example.hjycommunity.framework.service.SysPermissionService;
import org.example.hjycommunity.system.domain.pojo.LoginUser;
import org.example.hjycommunity.system.domain.pojo.SysUser;
import org.example.hjycommunity.system.domain.pojo.UserStatus;
import org.example.hjycommunity.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * 用户验证处理
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:35
 **/
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private SysUserService userService;
	
	@Autowired
	private SysPermissionService permissionService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SysUser user = userService.selectUserByUserName(username);
		
		if (Objects.isNull(user)) {
			log.info("登录用户:{} 不存在", username);
			throw new UsernameNotFoundException("登录用户: " + username + " 不存在");
		} else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
			log.info("登录用户:{} 已被删除", username);
			throw new BaseException("对不起,您的账号: " + username + " 以被删除");
		} else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
			log.info("登录用户:{} 已被停用", username);
			throw new BaseException("对不起,您的账号: " + username + " 以被停用");
		}
		
		return createLoginUser(user);
	}
	
	/**
	 * 创建一个 LoginUser 并返回当前用户 和 用户所拥有的菜单权限信息
	 *
	 * @param user
	 * @return
	 */
	public UserDetails createLoginUser(SysUser user) {
		Set<String> menuPermission = permissionService.getMenuPermission(user);
		return new LoginUser(user, menuPermission);
	}
}
