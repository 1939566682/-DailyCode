package org.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MenuServiceTest
 *
 * @author Yang QingBo
 * @date 2026-06-17 11:51
 * @description
 */

@SpringBootTest
class MenuServiceTest {
	
	@Autowired
	private MenuService menuService;
	
	@Test
	void findUserMenu() {
		List<Map<String, Object>> list = menuService.findUserMenu(1L);
		for (Map<String, Object> parentMenu : list) {
			System.out.println(parentMenu.get("name"));
			List<Map<String, Object>> sonMenuList = (List<Map<String, Object>>) parentMenu.get("list");
			for (Map<String, Object> sonMenu : sonMenuList) {
				System.out.println(sonMenu.get("name"));
			}
			System.out.println("--");
		}
	}
}
