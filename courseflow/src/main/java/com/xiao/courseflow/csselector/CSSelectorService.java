package com.xiao.courseflow.csselector;

import com.xiao.courseflow.course.Course;
import com.xiao.courseflow.course.CourseNotFoundException;
import com.xiao.courseflow.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CSSelectorService {
    @Autowired private CSSelectorRepo cssRepo;
    @Autowired
    CourseService cService;

    public List<Course> findCourseByStudent(Integer uid) throws CourseNotFoundException {
        List<Integer> cids = cssRepo.findCourseByStudent(uid);
        List<Course> courses = new ArrayList<>();
        for(Integer cid: cids){
            courses.add(cService.get(cid));
        }
        return courses;
    }
}
