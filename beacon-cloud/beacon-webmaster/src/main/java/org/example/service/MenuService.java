package org.example.service;

import java.util.List;
import java.util.Map;

/**
 * MenuService
 *
 * @author Yang QingBo
 * @date 2026-06-17 10:53
 * @description
 */

public interface MenuService {
	
	/**
	 * 根据用户id查询用户的菜单信息
	 * @param id 用户id
	 * @return 菜单信息
	 */
	List<Map<String,Object>> findUserMenu(Long id);
}
