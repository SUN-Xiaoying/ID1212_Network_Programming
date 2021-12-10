package com.example.spring.repository;

import com.example.spring.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepositery extends JpaRepository<Result, Integer> {
}
