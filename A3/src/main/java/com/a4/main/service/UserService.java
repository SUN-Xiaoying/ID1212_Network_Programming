package com.devrezaur.main.service;

import com.devrezaur.main.model.User;
import com.devrezaur.main.repository.UserRepo;
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

    public Boolean checkName(String username) throws UserNotFoundException {
        Boolean bool = true;
        User optionUser = repo.findByName(username);
        if (optionUser == null) {
            bool = false;
            throw new UserNotFoundException("User not found");
        }
        return bool;
    }

    public boolean checkUser(User user) throws UserNotFoundException {
        User optionUser = repo.findByName(user.getUsername());
        if (optionUser == null) {
            throw new UserNotFoundException("User not found");
        }
        if(optionUser.getPassword() != user.getPassword()){
            throw new UserNotFoundException("Password not match");
        }
        return true;
    }
}
