package com.a4.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.a4.main.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}