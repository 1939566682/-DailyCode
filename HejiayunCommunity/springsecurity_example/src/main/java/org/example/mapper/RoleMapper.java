package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Role;

import java.util.List;

/**
 * RoleMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-10 19:54
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id获取用户拥有的角色
     * @param id
     * @return
     */
    List<String> selectRolesByUserId(Long id);
}
