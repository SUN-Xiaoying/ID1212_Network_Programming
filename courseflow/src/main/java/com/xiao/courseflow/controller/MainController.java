package com.xiao.courseflow.controller;

import java.util.List;

import com.xiao.courseflow.model.Question;
import com.xiao.courseflow.model.User;
import com.xiao.courseflow.service.UserNotFoundException;
import com.xiao.courseflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xiao.courseflow.model.QuestionForm;
import com.xiao.courseflow.model.Result;
import com.xiao.courseflow.service.QuizService;

@Controller
public class MainController {
	
	@Autowired Result result;
	@Autowired QuizService qService;
	@Autowired UserService uService;

	Boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("user", new User());
		return "index.html";
	}

	/*  Mangae users  */
	@GetMapping("/users")
	public String showUserList(Model model){
		List<User> listUsers = uService.listAll();
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
		uService.save(user);

		return "redirect:/users";
	}

	@PostMapping("/quiz")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) throws UserNotFoundException {
		if(username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}

		submitted = false;
		result.setUsername(username);

		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);

		return "quiz.html";
	}
	
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
		if(!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}
		
		return "result.html";
	}
	
	@GetMapping("/score")
	public String score(Model m) {
		List<Result> sList = qService.getTopScore();
		m.addAttribute("sList", sList);
		
		return "scoreboard.html";
	}

}
