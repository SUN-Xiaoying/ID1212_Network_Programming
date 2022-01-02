package com.xiao.courseflow.csselector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CSSelectorRepo extends JpaRepository<CSSelector, Integer> {
    @Query("SELECT css.course_id FROM CSSelector css WHERE css.student_id = ?1")
    public List<Integer> findCourseByStudent(Integer uid);
}
