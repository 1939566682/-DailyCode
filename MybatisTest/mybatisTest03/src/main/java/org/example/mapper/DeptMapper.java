package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Dept;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-21 13:12
 */

@Mapper
public interface DeptMapper {

    /**
     * 根据部门编号查询部门信息及该部分的所有员工信息
     * @param deptno 要查询的部门编号
     * @return Dept对象,内部组合了一个Emp的List属性用于封装部门的所有员工信息
     */
    Dept findDeptJoinEmpsByDeptno(int deptno);
}
