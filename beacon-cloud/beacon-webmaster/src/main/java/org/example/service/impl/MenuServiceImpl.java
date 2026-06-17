package org.example.service.impl;

import org.example.mapper.SmsMenuMapper;
import org.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * MenuServiceImlo
 *
 * @author Yang QingBo
 * @date 2026-06-17 10:53
 * @description
 */

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private SmsMenuMapper menuMapper;
	
	@Override
	public List<Map<String, Object>> findUserMenu(Long id) {
		// 1、将多表查询的结果直接映射  查询到的结果顺序是type正序
		List<Map<String, Object>> list = menuMapper.findMenuByUserId(id);
		
		// 2、封装外层存储的父级菜单到当前的list集合
		List<Map<String, Object>> data = new ArrayList<>();
		// 3、用迭代器遍历所有菜单信息  封装父级菜单
		Iterator<Map<String, Object>> iterator = list.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();
			if (0 == (int)map.get("type")) {
				// 是父级菜单
				data.add(map);
				iterator.remove();
			} else {
				break;
			}
		}
		
		// 4、存放二级菜单
		for (Map<String, Object> parentMenu : data) {
			List<Map<String, Object>> sonMenuList = new ArrayList<>();
			Iterator<Map<String, Object>> sonIterator = list.iterator();
			while (sonIterator.hasNext()) {
				Map<String, Object> sonMenu = sonIterator.next();
				if ((long) parentMenu.get("id") == (long) sonMenu.get("parentId")) {
					sonMenuList.add(sonMenu);
					sonIterator.remove();
				}
			}
			parentMenu.put("list", sonMenuList);
		}
		return data;
	}
}
