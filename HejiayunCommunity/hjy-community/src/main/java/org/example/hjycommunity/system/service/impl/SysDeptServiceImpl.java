package org.example.hjycommunity.system.service.impl;

import org.example.hjycommunity.system.domain.pojo.SysDept;
import org.example.hjycommunity.system.mapper.SysDeptMapper;
import org.example.hjycommunity.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysDeptServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 09:47
 */

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询部门信息
     * @param sysDept
     * @return
     */
    @Override
    public List<SysDept> selectDeptList(SysDept sysDept) {
        return sysDeptMapper.selectDeptList(sysDept);
    }
}
