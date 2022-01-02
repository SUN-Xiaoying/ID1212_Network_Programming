package com.xiao.courseflow.cqselector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CQSelectorRepo extends JpaRepository<CQSelector, Integer> {
    @Query("SELECT cqs.question_id FROM CQSelector cqs WHERE cqs.course_id = ?1")
    public List<Integer> findQuestionByCourse(Integer cid);
}
