package com.xiao.courseflow.repository;

import com.xiao.courseflow.model.Course;
import com.xiao.courseflow.model.CSSelector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    public Long countById(Integer id);

//    @Query("SELECT c.code FROM Course c JOIN CSSelector css ON c.id=css.course_id AND css.student_id=2;")
}
