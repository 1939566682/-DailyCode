package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.pojo.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * SysMenuService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 15:23
 */

public interface SysMenuService {
	
	/**
	 * 根据用户 id 查询菜单权限
	 * @param userId
	 * @return
	 */
	Set<String> selectMenuPermissionsByUserId(Long userId);
	
	/**
	 * 根据用户 id 查询菜单信息
	 * @param userId
	 * @return
	 */
	List<SysMenu> selectMenuTreeByUserId(Long userId);
}
