package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.Emp;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-21 15:31
 */

@Mapper
public interface EmpMapper {

    /**
     * 根据员工编号查询员工的所有信息并携带所在的部门信息
     * @param empno 要查询的员工编号
     * @return Emp对象，组合了Dept对象作为属性，对部门信息进行存储*
     */
    Emp findEmpJoinDeptByEmpno(Integer empno);

}
