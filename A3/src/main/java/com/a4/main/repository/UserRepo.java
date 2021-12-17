package com.devrezaur.main.repository;

import com.devrezaur.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByName(String name);
}
