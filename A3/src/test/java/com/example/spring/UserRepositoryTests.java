package com.example.spring;

import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;
import com.example.spring.service.UserNotFoundException;
import com.example.spring.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    @Autowired
    private UserService service;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setUsername("xsu@kth.se");
        user.setPassword("sxy123");

        User savedUser = repo.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals("xsu@kth.se", savedUser.getUsername());
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertNotNull(users);
        for (User user :users){
            System.out.println(user.toString());
        }
    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertNotNull(optionalUser);

        System.out.println(optionalUser.toString());
    }

    @Test
    public void testFindByName(){
        String name="xsu@kth.se";

        User user = repo.findByName(name);

        Assertions.assertNotNull(user);
        System.out.println(user.toString());
    }
}
