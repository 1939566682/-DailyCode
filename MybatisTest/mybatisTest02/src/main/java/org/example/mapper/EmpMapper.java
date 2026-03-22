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

    List<Emp> findEmpList();

    Emp findEmpByEmpno(int empno);

    List<Emp> findEmpByDeptnoAndSal(@Param("deptno") int deptno, @Param("sal") double sal);
    // List<Emp> findEmpByDeptnoAndSal(int deptno, double sal);
    // 多个参数依旧可以放入集合或者对象中

    Emp findEmpByCondition(Emp emp);

    Integer updateEmpByCondition(Emp emp);

    Emp findEmpByCondition2(Emp emp);

    Emp findEmpByCondition3(Emp emp);

    // foreach
    List<Emp> findEmpByEmpnos(List<Integer> empnos);

    List<Emp> findEmpByCondition4(Emp emp);

}
