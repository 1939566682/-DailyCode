package org.example.service;

import org.example.util.PageResult;
import org.example.vo.RoleVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SmsRoleService - 扩展原有接口，新增角色管理相关方法
 */
public interface SmsRoleService {

    /**
     * 根据用户id查询角色名称
     * @param userId 用户ID
     * @return 角色名称集合
     */
    Set<String> getRoleName(Long userId);

    /**
     * 分页查询角色列表
     * @param offset 偏移量
     * @param limit 每页数量
     * @param name 角色名称（模糊搜索）
     * @param status 状态
     * @return 分页结果
     */
    PageResult<RoleVO> list(int offset, int limit, String name, Integer status);

    /**
     * 批量删除角色
     * @param ids 角色ID数组
     */
    void delete(Long[] ids);

    /**
     * 根据ID查询角色详情
     * @param id 角色ID
     * @return 角色VO
     */
    RoleVO findById(Long id);

    /**
     * 新增角色
     * @param roleVO 角色VO
     */
    void save(RoleVO roleVO);

    /**
     * 更新角色
     * @param roleVO 角色VO
     */
    void update(RoleVO roleVO);

    /**
     * 分配菜单给角色
     * @param roleId 角色ID
     * @param menuIds 菜单ID数组
     */
    void assignMenu(Long roleId, Long[] menuIds);

    /**
     * 查询角色的已分配菜单ID数组
     * @param roleId 角色ID
     * @return 菜单ID数组
     */
    Long[] getRoleMenuIds(Long roleId);

    /**
     * 查询所有菜单树
     * @return 菜单列表
     */
    List<Map<String, Object>> getMenuTree();
}
