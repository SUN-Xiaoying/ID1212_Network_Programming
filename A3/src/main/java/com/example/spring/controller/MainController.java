package com.example.spring.controller;

import com.example.spring.model.QuestionForm;
import com.example.spring.model.Result;
import com.example.spring.model.User;
import com.example.spring.service.QuestionService;
import com.example.spring.service.UserNotFoundException;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired private UserService service;
    @Autowired Result result;
    @Autowired QuestionService qService;
    @Autowired QuestionForm qForm;

    Boolean submitted = false;

    @GetMapping("")
    public String home(){
        return "index";
    }

    /*  Log in  */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping("/quiz")
    public String processLogIn(User user) {
        try{
            service.check(user);
        }catch(UserNotFoundException ignored){
            System.err.println("User not found.");
            return "index";
        }finally {
            result.setUsername(user.getUsername());
            return "quiz";
        }
    }

    /*  Mangae users  */
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/users/save")
    public String saveUser(User user){
        service.save(user);

        return "redirect:/users";
    }

    @GetMapping("/question")
    public String question(Model m){
        submitted = false;
        qForm = qService.getQuestions();
        m.addAttribute("qForm", qForm);

        return "question";
    }

    @PostMapping("/submit")
    public String submit(Model m) {
        if(!submitted) {
//            result.setScore(qForm.getScore());
            System.out.println(qService.getResult(qForm));
            result.setScore(qService.getResult(qForm));
            qService.saveScore(result);
            qForm.clearScore();
            submitted = true;
        }
        m.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard";
    }
}
