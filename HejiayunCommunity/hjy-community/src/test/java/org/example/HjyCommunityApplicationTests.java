package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.hjycommunity.system.domain.pojo.SysMenu;
import org.example.hjycommunity.system.domain.pojo.SysUser;
import org.example.hjycommunity.system.mapper.SysMenuMapper;
import org.example.hjycommunity.system.mapper.SysUserMapper;
import org.example.hjycommunity.system.service.SysMenuService;
import org.example.hjycommunity.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class HjyCommunityApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
	@Autowired
	SysUserMapper userMapper;
	
	@Autowired
	SysRoleService sysRoleService;
	
	@Autowired
	SysMenuService sysMenuService;
	
	@Autowired
	SysMenuMapper sysMenuMapper;
	
	@Test
	public void testColor() {
		System.out.println("\u001B[32mThis should be GREEN\u001B[0m");
		System.out.println("\u001B[34mThis should be BLUE\u001B[0m");
	}
	
	@Test
	public void testSelectUserByUserName() {
		SysUser admin = userMapper.selectUserByUserName("admin");
		log.info("log() testSelectUserByUserName:{}", admin);
	}
	
	@Test
	public void selectRoleAndMenuPermissionsByUserId() {
		Set<String> strings1 = sysRoleService.selectRolePermissionsByUserId(1L);
		Set<String> strings2 = sysMenuService.selectMenuPermissionsByUserId(1L);
		log.info("log() 用户角色权限信息：{}", strings1);
		log.info("log() 用户菜单权限信息：{}", strings2);
	}
	
	
	// 查询菜单信息
	@Test
	public void selectMenuTreeAll() {

//		List<SysMenu> menus = sysMenuMapper.selectMenuTreeAll();
//		System.out.println("查询全部的菜单信息:");
//		for (SysMenu menu : menus) {
//			System.out.println(menu);
//		}

//		System.out.println("根据用户id查询菜单信息：");
//		List<SysMenu> menus1 = sysMenuMapper.selectMenuTreeByUserId(2L);
//		for (SysMenu menu : menus1) {
//			System.out.println(menu);
//		}
		
		System.out.println("根据用户id查询树状菜单信息：");
		List<SysMenu> menus2 = sysMenuService.selectMenuTreeByUserId(2L);
		for (SysMenu menu : menus2) {
			System.out.println(menu);
		}
	}
	
	
}
