package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Dept;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-22 19:36
 */

public interface DeptMapper {
    Dept findDeptByDeptno(Integer deptno);

    // 级联查询(积极加载和懒加载)
    Dept findDeptByDeptno2(Integer deptno);

    @Select("select * from dept where deptno =#{deptno}")
    Dept findByDeptno(Integer deptno);

    @Update("update dept set dname =#{dname}, loc =#{loc} where deptno =#{deptno}")
    Integer updateDept(Dept dept);

    @Insert("insert into dept values(DEFAULT,#{dname},#{loc})")
    Integer addDept(Dept dept);

    @Delete("delete from dept where deptno =#{deptno}")
    Integer removeDept(Integer deptno);
}
