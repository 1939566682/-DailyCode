package org.example.hjycommunity.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.hjycommunity.system.domain.pojo.SysDept;

import java.util.List;

/**
 * SysDeptMapper
 * 部门管理  数据层
 * @author Yang QingBo
 * {@code @date} 2026-04-07 09:42
 */

@Mapper
public interface SysDeptMapper {

    /**
     * 查询部门信息
     * @param sysDept
     * @return
     */
    List<SysDept> selectDeptList(SysDept sysDept);

}
