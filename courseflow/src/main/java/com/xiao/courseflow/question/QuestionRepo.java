package com.xiao.courseflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.xiao.courseflow.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    public Long countById(Integer id);
}