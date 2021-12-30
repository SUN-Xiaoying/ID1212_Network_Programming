package com.xiao.courseflow.repository;

import com.xiao.courseflow.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    public Long countById(Integer id);

}
