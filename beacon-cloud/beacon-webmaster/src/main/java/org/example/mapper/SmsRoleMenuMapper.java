package org.example.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SmsRoleMenuMapper - 角色菜单关联Mapper
 * TODO: 需确认 sms_role_menu 表是否已创建
 */
public interface SmsRoleMenuMapper {

    /**
     * 根据角色ID查询菜单ID列表
     * @param roleId 角色ID
     * @return 菜单ID列表
     */
    List<Long> findMenuIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 删除角色的所有菜单关联
     * @param roleId 角色ID
     */
    void deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 批量插入角色菜单关联
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     */
    void insertBatch(@Param("roleId") Long roleId, @Param("menuIds") Long[] menuIds);
}
