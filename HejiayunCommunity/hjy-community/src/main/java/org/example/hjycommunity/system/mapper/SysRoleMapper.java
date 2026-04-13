package org.example.hjycommunity.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.hjycommunity.system.domain.pojo.SysRole;

import java.util.List;

/**
 * SysRoleMapper
 * 角色表 数据层
 * @author Yang QingBo
 * {@code @date} 2026-04-13 14:51
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
	/**
	 * 根据用户 id 查询用户权限
	 * @param userId
	 * @return
	 */
	List<String> selectRolePermissionsByUserId(Long userId);
}
