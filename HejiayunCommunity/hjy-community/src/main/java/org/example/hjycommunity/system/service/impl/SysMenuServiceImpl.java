package org.example.hjycommunity.system.service.impl;

import org.apache.commons.lang.StringUtils;
import org.example.hjycommunity.common.constant.UserConstants;
import org.example.hjycommunity.system.domain.pojo.SysMenu;
import org.example.hjycommunity.system.domain.vo.MetaVo;
import org.example.hjycommunity.system.domain.vo.RouterVo;
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
	 * 构建前端路由所需要的菜单
	 *
	 * @param menus selectMenuTreeByUserId 所返回的菜单
	 * @return
	 */
	@Override
	public List<RouterVo> buildMenuTree(List<SysMenu> menus) {
		List<RouterVo> routers = new ArrayList<>();
		for (SysMenu menu : menus) {
			RouterVo routerVo = new RouterVo();
			routerVo.setName(getRouterName(menu));
			routerVo.setPath(getRouterPath(menu));
			routerVo.setComponent(getRouterComponent(menu));
			routerVo.setHidden("1".equals(menu.getVisible()));
			routerVo.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), "1".equals(menu.getIsCache())));
			List<SysMenu> subMenuList = menu.getChildren();
			if (subMenuList != null && !subMenuList.isEmpty() && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
				routerVo.setAlwaysShow(true);
				routerVo.setRedirect("noRedirect");
				routerVo.setChildren(buildMenuTree(subMenuList)); // 递归设置子菜单
			}
			routers.add(routerVo);
		}
		return routers;
	}
	
	/**
	 * 获取组件信息
	 * 获取 menu.getComponent()
	 * 如果是顶级菜单 直接返回 Layout
	 * 如果不是 则原样返回 menu.getComponent()
	 *
	 * @param menu
	 * @return 路由组件信息
	 */
	private String getRouterComponent(SysMenu menu) {
		if (menu == null) {
			throw new NullPointerException("getRouterComponent(SysMenu menu) menu不能为空");
		}
		String component = menu.getComponent();
		// 如果是子菜单
		if (component != null) {
			return component;
			// 如果不是顶级菜单  且菜单类型是目录
		} else if (menu.getParentId() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
			return UserConstants.PARENT_VIEW;
		} else return UserConstants.LAYOUT;
	}
	
	/**
	 * 获取路由的地址
	 * 获取 menu_path
	 * 如果是顶级菜单 在menu_path后将在首字母前加'/'
	 * 如果不是 则原样返回
	 *
	 * @param menu
	 * @return 路由路径
	 */
	private String getRouterPath(SysMenu menu) {
		String path = menu.getPath();
		if (menu.getParentId() == 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
			path = "/" + path;
		}
		return path;
	}
	
	/**
	 * 获取路由名称 ( 获取 menu_path 后将首字母大写 )
	 *
	 * @param menu
	 * @return 路由名称
	 */
	private String getRouterName(SysMenu menu) {
		String routerPath = menu.getPath();
		return StringUtils.capitalize(routerPath);
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
	 * @param menu  第一次调用是实参为一级菜单
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
	 * @param menu  第一次调用时 实参为一级菜单
	 * @return 第一次调用时 返回一个 List<SysMenu> 集合  存储一级菜单下的所有子节点
	 */
	private List<SysMenu> getChildList(List<SysMenu> menus, SysMenu menu) {
		return menus.stream()
				.filter(sub -> sub.getParentId().longValue() == menu.getMenuId().longValue())
				.toList();
	}
}
