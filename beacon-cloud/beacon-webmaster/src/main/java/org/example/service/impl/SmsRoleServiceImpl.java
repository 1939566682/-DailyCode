package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.SmsMenu;
import org.example.entity.SmsMenuExample;
import org.example.entity.SmsRole;
import org.example.entity.SmsRoleExample;
import org.example.mapper.SmsMenuMapper;
import org.example.mapper.SmsRoleMapper;
import org.example.mapper.SmsRoleMenuMapper;
import org.example.service.SmsRoleService;
import org.example.util.PageResult;
import org.example.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SmsRoleServiceImpl - 扩展原有实现，新增角色管理相关方法
 * extend1 → status, extend2 → remark
 */
@Service
public class SmsRoleServiceImpl implements SmsRoleService {

    @Autowired
    private SmsRoleMapper smsRoleMapper;

    @Autowired
    private SmsMenuMapper smsMenuMapper;

    @Autowired(required = false)
    private SmsRoleMenuMapper smsRoleMenuMapper;

    /**
     * 根据用户id查询角色名称
     */
    @Override
    public Set<String> getRoleName(Long userId) {
        return smsRoleMapper.findRoleNameByUserId(userId);
    }

    /**
     * 分页查询角色列表
     * status存储在extend1中, remark存储在extend2中
     */
    @Override
    public PageResult<RoleVO> list(int offset, int limit, String name, Integer status) {
        SmsRoleExample example = new SmsRoleExample();
        SmsRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        // status存在extend1中，status=1有效，=0无效
        if (status != null) {
            criteria.andExtend1EqualTo(String.valueOf(status));
        }

        example.setOrderByClause("id asc");

        PageHelper.offsetPage(offset, limit);
        List<SmsRole> list = smsRoleMapper.selectByExample(example);
        long total = new PageInfo<>(list).getTotal();

        // Entity → VO
        List<RoleVO> voList = new ArrayList<>();
        for (SmsRole role : list) {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(role, vo);
            // extend1 → status
            if (role.getExtend1() != null) {
                try {
                    vo.setStatus(Integer.valueOf(role.getExtend1()));
                } catch (NumberFormatException e) {
                    vo.setStatus(0);
                }
            }
            // extend2 → remark
            vo.setRemark(role.getExtend2());
            voList.add(vo);
        }

        return new PageResult<>(total, voList);
    }

    /**
     * 批量删除角色（逻辑删除）
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            SmsRole role = new SmsRole();
            role.setId(id);
            role.setIsDelete((byte) 1);
            smsRoleMapper.updateByPrimaryKeySelective(role);
        }
    }

    /**
     * 根据ID查询角色详情
     */
    @Override
    public RoleVO findById(Long id) {
        SmsRole role = smsRoleMapper.selectByPrimaryKey(id.intValue());
        if (role == null) {
            return null;
        }
        RoleVO vo = new RoleVO();
        BeanUtils.copyProperties(role, vo);
        if (role.getExtend1() != null) {
            try {
                vo.setStatus(Integer.valueOf(role.getExtend1()));
            } catch (NumberFormatException e) {
                vo.setStatus(0);
            }
        }
        vo.setRemark(role.getExtend2());
        return vo;
    }

    /**
     * 新增角色
     * status → extend1, remark → extend2
     */
    @Override
    public void save(RoleVO roleVO) {
        SmsRole role = new SmsRole();
        BeanUtils.copyProperties(roleVO, role);
        // status → extend1
        if (roleVO.getStatus() != null) {
            role.setExtend1(String.valueOf(roleVO.getStatus()));
        }
        // remark → extend2
        role.setExtend2(roleVO.getRemark());
        role.setCreated(new Date());
        role.setUpdated(new Date());
        role.setIsDelete((byte) 0);
        smsRoleMapper.insertSelective(role);

        // 如果前端传了 id，需要把自增 ID 回填到 roleVO（如果需要的话）
        // 由于 useGeneratedKeys 配置，role.getId() 会自动回填
    }

    /**
     * 更新角色
     */
    @Override
    public void update(RoleVO roleVO) {
        SmsRole role = new SmsRole();
        BeanUtils.copyProperties(roleVO, role);
        if (roleVO.getStatus() != null) {
            role.setExtend1(String.valueOf(roleVO.getStatus()));
        }
        role.setExtend2(roleVO.getRemark());
        role.setUpdated(new Date());
        smsRoleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 分配菜单给角色
     * 先删除原关联，再批量插入新关联
     */
    @Override
    public void assignMenu(Long roleId, Long[] menuIds) {
        if (smsRoleMenuMapper == null) {
            org.slf4j.LoggerFactory.getLogger(getClass()).warn("SmsRoleMenuMapper 未注入，请确认 sms_role_menu 表已创建并配置了 Mapper");
            return;
        }
        // 先删除原有关联
        smsRoleMenuMapper.deleteByRoleId(roleId);
        // 再插入新关联
        if (menuIds != null && menuIds.length > 0) {
            smsRoleMenuMapper.insertBatch(roleId, menuIds);
        }
    }

    /**
     * 查询角色的已分配菜单ID数组
     */
    @Override
    public Long[] getRoleMenuIds(Long roleId) {
        if (smsRoleMenuMapper == null) {
            org.slf4j.LoggerFactory.getLogger(getClass()).warn("SmsRoleMenuMapper 未注入，请确认 sms_role_menu 表已创建并配置了 Mapper");
            return new Long[0];
        }
        List<Long> menuIds = smsRoleMenuMapper.findMenuIdsByRoleId(roleId);
        return menuIds.toArray(new Long[0]);
    }

    /**
     * 查询所有菜单树
     */
    @Override
    public List<Map<String, Object>> getMenuTree() {
        SmsMenuExample example = new SmsMenuExample();
        SmsMenuExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        example.setOrderByClause("sort asc, id asc");
        List<SmsMenu> list = smsMenuMapper.selectByExample(example);

        List<Map<String, Object>> result = new ArrayList<>();
        for (SmsMenu menu : list) {
            Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("id", menu.getId());
            map.put("parentId", menu.getParentId());
            map.put("name", menu.getName());
            map.put("url", menu.getUrl());
            map.put("icon", menu.getIcon());
            map.put("type", menu.getType());
            result.add(map);
        }
        return result;
    }
}
