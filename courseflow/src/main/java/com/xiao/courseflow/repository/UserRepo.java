package com.xiao.courseflow.repository;

import com.xiao.courseflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByName(String name);
}
