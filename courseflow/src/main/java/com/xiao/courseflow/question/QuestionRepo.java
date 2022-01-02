package com.xiao.courseflow.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.xiao.courseflow.question.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    public Long countById(Integer id);
}