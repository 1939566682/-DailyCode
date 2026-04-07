package com.example.easypoi_boot.mapper;

import com.example.easypoi_boot.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CourseMapper
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 15:58
 */

@Mapper
public interface CourseMapper {

    //查询所有课程
    List<Course> findAll();

    //插入记录
    void save(Course course);
}
