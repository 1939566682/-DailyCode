package com.example.easypoi_boot.service.impl;

import com.example.easypoi_boot.entity.Course;
import com.example.easypoi_boot.mapper.CourseMapper;
import com.example.easypoi_boot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CourseServiceImpl
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 15:59
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public void save(List<Course> courses) {
        courses.forEach(course -> {
            course.setCid(null); //自动生成id,不需要使用Excel中的编号
            courseMapper.save(course);
        });
    }
}
