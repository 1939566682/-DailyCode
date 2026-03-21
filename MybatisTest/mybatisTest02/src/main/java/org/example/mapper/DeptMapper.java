package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Dept;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-21 13:12
 */

@Mapper
public interface DeptMapper {

    int addDept(Dept dept);
}
