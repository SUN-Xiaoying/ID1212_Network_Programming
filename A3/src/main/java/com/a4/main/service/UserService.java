package com.a4.main.service;

import com.a4.main.model.User;
import com.a4.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepo repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user){ repo.save(user);}

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        } throw new UserNotFoundException("Could not find user!");
    }
}
