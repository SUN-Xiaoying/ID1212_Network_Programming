package com.xiao.courseflow.controller;

import com.xiao.courseflow.model.User;
import com.xiao.courseflow.service.UserNotFoundException;
import com.xiao.courseflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService uService;

    @GetMapping("/")
    public String showLoginForm(Model model, RedirectAttributes ra) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model m, RedirectAttributes ra) throws UserNotFoundException {
        if(uService.login(user)){
            User result = uService.findbyName(user.getUsername());
            if(result.isEnabled()){
                return "redirect:/manager";
            }
            return "redirect:/user";
        }else{
            ra.addFlashAttribute("message","Failed to log in!");
            return "redirect:/";
        }
    }

//    @GetMapping("/home")
//    public String showHome(@ModelAttribute("user") User user) throws UserNotFoundException {
//        User result = uService.findbyName(user.getUsername());
//        if(result.isEnabled()){
//            return "redirect:/manager";
//        }
//        return "redirect:/user";
//    }

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
    public String signOut(HttpServletRequest request, HttpServletResponse response){
        return "redirect:/";
    }
}
