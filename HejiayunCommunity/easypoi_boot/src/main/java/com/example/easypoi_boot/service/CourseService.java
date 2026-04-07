package com.example.easypoi_boot.service;

import com.example.easypoi_boot.entity.Course;

import java.util.List;

/**
 * CourseService
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 15:58
 */

public interface CourseService {

    List<Course> findAll();

    void save(List<Course> courses);
}
