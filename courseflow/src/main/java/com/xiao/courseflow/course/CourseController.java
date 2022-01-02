package com.xiao.courseflow.controller;

import com.xiao.courseflow.model.Course;
import com.xiao.courseflow.service.CourseNotFoundException;
import com.xiao.courseflow.service.CourseService;
import com.xiao.courseflow.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CourseController {
    @Autowired CourseService cService;

    /*  Manage Courses  */
    @GetMapping("/courses")
    public String showUserList(Model model){
        List<Course> listCourses = cService.listAll();
        model.addAttribute("listCourses",listCourses);
        return "exams/courses";
    }

    @GetMapping("/courses/new")
    public String showNewForm(Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("pageTitle", "Add Course");
        return "exams/update";
    }

    @PostMapping("/courses/save")
    public String saveUser(Course course, RedirectAttributes ra){
        cService.save(course);
        ra.addFlashAttribute("message","The course has been saved successfully!");
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Course course = cService.get(id);
            cService.delete(id);
            model.addAttribute("course", course);
            model.addAttribute("pageTitle", "Edit User (ID: "+ id +" )");
            return "exams/update";
        } catch (CourseNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/courses";
        }
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            cService.delete(id);
            ra.addFlashAttribute("message","The user ID: " + id + " has been deleted!");
        } catch (CourseNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/courses";
    }

}
