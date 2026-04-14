package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.pojo.SysMenu;
import org.example.hjycommunity.system.domain.vo.RouterVo;

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
	
	/**
	 * 构建前端路由所需要的菜单
	 * @param menus selectMenuTreeByUserId 所返回的菜单
	 * @return
	 */
	List<RouterVo> buildMenuTree(List<SysMenu> menus);
}
