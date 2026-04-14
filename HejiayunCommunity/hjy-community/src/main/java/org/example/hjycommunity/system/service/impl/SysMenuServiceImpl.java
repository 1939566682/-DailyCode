package org.example.hjycommunity.system.service.impl;

import org.example.hjycommunity.system.domain.pojo.SysMenu;
import org.example.hjycommunity.system.mapper.SysMenuMapper;
import org.example.hjycommunity.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SysMenuServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-13 15:23
 */

@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	/**
	 * 根据用户 id 查询菜单权限
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> selectMenuPermissionsByUserId(Long userId) {
		List<String> list = sysMenuMapper.selectMenuPermissionsByUserId(userId);
		return new HashSet<>(list);
	}
	
	/**
	 * 根据用户 id 查询菜单信息
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysMenu> selectMenuTreeByUserId(Long userId) {
		List<SysMenu> menus;
		if (userId != null && userId == 1) {
			menus = sysMenuMapper.selectMenuTreeAll();
		} else {
			menus = sysMenuMapper.selectMenuTreeByUserId(userId);
		}
		
		// 封装子菜单
		return getChildPerms(menus, 0);
	}
	
	/**
	 * 根据父节点 id 获取子节点
	 *
	 * @param menus    菜单列表
	 * @param parentId 父菜单id
	 * @return 有父子结构的集合
	 */
	private List<SysMenu> getChildPerms(List<SysMenu> menus, int parentId) {
		ArrayList<SysMenu> returnList = new ArrayList<>();
		menus.stream()
				// 将 parentId = 0 的菜单 (一级菜单) 过滤出来
				.filter(menu -> menu.getParentId() == parentId)
				.forEach(menu -> {
					recursionFunction(menus, menu);
					returnList.add(menu);
				});
		return returnList;
	}
	
	/**
	 * 递归获取子菜单
	 *
	 * @param menus 整个菜单列表
	 * @param menu 第一次调用是实参为一级菜单
	 */
	private void recursionFunction(List<SysMenu> menus, SysMenu menu) {
		// 获取子节点列表
		List<SysMenu> childMenu = getChildList(menus, menu);
		menu.setChildren(childMenu);
		for (SysMenu m : childMenu) {
			// 判断 子节点下还有子节点
			if (getChildList(menus, m).size() > 0) {
				recursionFunction(menus, m);
			}
		}
		
	}
	
	
	/**
	 * 获取子节点列表
	 *
	 * @param menus 整个菜单列表
	 * @param menu 第一次调用时 实参为一级菜单
	 * @return 第一次调用时 返回一个 List<SysMenu> 集合  存储一级菜单下的所有子节点
	 */
	private List<SysMenu> getChildList(List<SysMenu> menus, SysMenu menu) {
		return menus.stream()
				.filter(sub -> sub.getParentId().longValue() == menu.getMenuId().longValue())
				.toList();
	}
}
