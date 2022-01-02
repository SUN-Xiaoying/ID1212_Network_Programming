package com.xiao.courseflow.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired private CourseRepo repo;

    public List<Course> listAll() {return (List<Course>) repo.findAll();}

    public void save(Course c){
        repo.save(c);
    }

    public Course get(Integer id) throws CourseNotFoundException {
        Optional<Course> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }throw new CourseNotFoundException("Course not found!");
    }

    public void delete(Integer id) throws CourseNotFoundException{
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new CourseNotFoundException("Could not find course by ID " +id);
        }
        repo.deleteById(id);
    }

}
