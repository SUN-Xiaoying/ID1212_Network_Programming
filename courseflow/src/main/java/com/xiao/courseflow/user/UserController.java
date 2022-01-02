package com.xiao.courseflow.user;

import com.xiao.courseflow.course.Course;
import com.xiao.courseflow.course.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService uService;

    @GetMapping("/")
    public String showLoginForm(Model model, RedirectAttributes ra) {
        model.addAttribute("user", new User());
        return "auth/index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model m, RedirectAttributes ra) throws UserNotFoundException {
        if(uService.login(user)){
            User result = uService.findbyName(user.getUsername());
            ra.addAttribute("uid",result.getId());
            if(result.isEnabled()){
                return "redirect:/manager";
            }
            return "redirect:/user/{uid}";
        }else{
            ra.addFlashAttribute("message","Failed to log in!");
            return "redirect:/";
        }
    }

    @GetMapping("/user/{uid}")
    public String showUser(@PathVariable("uid") Integer uid, Model m, RedirectAttributes ra) throws UserNotFoundException, CourseNotFoundException {
        User user = uService.get(uid);
        List<Course> courses = uService.getCourses(uid);
        m.addAttribute("user", user);
        m.addAttribute("courses",courses);
        return "auth/user";
    }

    @GetMapping("/manager")
    public String showManager(){
        return "auth/manager";
    }

    /*  Forget Password  */
    @GetMapping("/forget")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "auth/forget";
    }

    /*  Manage users  */
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = uService.listAll();
        model.addAttribute("listUsers",listUsers);
        return "auth/users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add user");
        return "auth/update";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user = uService.get(id);
            uService.delete(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: "+ id +" )");
            return "auth/update";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) throws UserNotFoundException {
        uService.save(user);
        ra.addFlashAttribute("message","The user has been saved successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            uService.delete(id);
            ra.addFlashAttribute("message","The user ID: " + id + " has been deleted!");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }

        return "redirect:/users";
    }

    /*  Sign Out  */
    @PostMapping("/logout")
    public String signOut(){
        return "redirect:/";
    }
}
