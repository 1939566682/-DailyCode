package org.example.hjycommunity.system.service;

import org.example.hjycommunity.system.domain.pojo.SysDept;

import java.util.List;

/**
 * SysDeptService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 09:47
 */

public interface SysDeptService {

    /**
     * 查询部门信息
     * @param sysDept
     * @return
     */
    List<SysDept> selectDeptList(SysDept sysDept);

}
