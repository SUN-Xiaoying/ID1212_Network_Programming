package com.xiao.courseflow.user;

import com.xiao.courseflow.course.Course;
import com.xiao.courseflow.course.CourseNotFoundException;
import com.xiao.courseflow.csselector.CSSelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepo repo;
    @Autowired
    CSSelectorService cssService;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user){
        if(user.getUsername().contains("@kth.se")){
            repo.save(user);
        }
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        } throw new UserNotFoundException("User not found!");
    }

    public User findbyName(String username)throws UserNotFoundException{
        User result = repo.findByName(username);
        if(Objects.nonNull(result)){
            return result;
        } throw new UserNotFoundException("User not found!");
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find user by ID " +id);
        }
        repo.deleteById(id);
    }

    /* Login */
    public boolean login(User user) {
        User optionUser = repo.findByName(user.getUsername());
        if(Objects.nonNull(optionUser)){
            if(optionUser.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    /* Get Courses */
    public List<Course> getCourses(Integer uid) throws CourseNotFoundException {
        List<Course> courses = cssService.findCourseByStudent(uid);
//        System.out.println(Arrays.toString(courses.toArray()));
        return courses;
    }
}
