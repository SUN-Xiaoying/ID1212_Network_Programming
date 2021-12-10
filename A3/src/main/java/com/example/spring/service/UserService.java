package com.example.spring.service;

import com.example.spring.model.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

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

    public void check(User user) throws UserNotFoundException {
        User optionUser = repo.findByName(user.getUsername());
        if (optionUser == null) {
            throw new UserNotFoundException("User not found");
        }
        if (!Objects.equals(optionUser.getPassword(), user.getPassword())){
            throw new UserNotFoundException("Username or Password did not match");
        }

    }

}
