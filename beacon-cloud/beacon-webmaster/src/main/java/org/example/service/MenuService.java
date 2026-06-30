package org.example.service;

import org.example.util.PageResult;
import org.example.entity.SmsMenu;

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
	
	/**
	 * 分页查询菜单列表
	 * @param offset 偏移量
	 * @param limit 每页数量
	 * @param search 搜索关键字
	 * @return 分页结果
	 */
	PageResult<SmsMenu> list(int offset, int limit, String search);
	
	/**
	 * 批量删除菜单
	 * @param ids 菜单ID数组
	 */
	void delete(Long[] ids);
	
	/**
	 * 根据ID查询菜单详情
	 * @param id 菜单ID
	 * @return 菜单信息
	 */
	SmsMenu findById(Long id);
	
	/**
	 * 保存菜单
	 * @param menu 菜单信息
	 */
	void save(SmsMenu menu);
	
	/**
	 * 更新菜单
	 * @param menu 菜单信息
	 */
	void update(SmsMenu menu);
	
	/**
	 * 查询所有菜单（用于树形结构展示）
	 * @return 菜单列表
	 */
	List<SmsMenu> selectAll();
}
