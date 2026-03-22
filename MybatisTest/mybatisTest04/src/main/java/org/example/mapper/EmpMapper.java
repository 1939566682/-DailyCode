package org.example.mapper;

import org.example.pojo.Emp;

import java.util.List;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-22 19:36
 */

public interface EmpMapper {

    List<Emp> findEmpsByDeptno(Integer deptno);
}
