package com.xiao.courseflow.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByName(String name);

    public Long countById(Integer id);
}
