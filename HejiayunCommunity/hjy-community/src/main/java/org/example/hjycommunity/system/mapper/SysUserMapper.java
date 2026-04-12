package org.example.hjycommunity.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.hjycommunity.system.domain.pojo.SysUser;

/**
 * SysUserMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-12 20:29
 */

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
	
	/**
	 * 通过用户名查询用户
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	public SysUser selectUserByUserName(String userName);
}
