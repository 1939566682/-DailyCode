package org.example.hjycommunity.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.hjycommunity.system.domain.pojo.SysMenu;

import java.util.List;

/**
 * SysMenuMapper
 * 菜单表 数据层
 * @author Yang QingBo
 * {@code @date} 2026-04-13 15:15
 */

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	
	/**
	 * 根据用户 id 查询菜单权限
	 * @param userId
	 * @return
	 */
	List<String> selectMenuPermissionsByUserId(Long userId);
}
