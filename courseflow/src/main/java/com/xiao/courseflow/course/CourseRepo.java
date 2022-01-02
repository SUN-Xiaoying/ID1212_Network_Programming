package com.xiao.courseflow.course;

import com.xiao.courseflow.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    public Long countById(Integer id);

//    @Query("SELECT c.code FROM Course c JOIN CSSelector css ON c.id=css.course_id AND css.student_id=2;")
}
