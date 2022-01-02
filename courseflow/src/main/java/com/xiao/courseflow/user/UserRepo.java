package com.xiao.courseflow.repository;

import com.xiao.courseflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByName(String name);

    public Long countById(Integer id);

    @Query("SELECT u.username, c.subject FROM User u JOIN u.courses c WHERE c.id=1")
    public String getFirstCourse();
}
