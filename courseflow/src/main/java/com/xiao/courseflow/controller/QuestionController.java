package com.xiao.courseflow.controller;

import java.util.List;

import com.xiao.courseflow.model.Question;
import com.xiao.courseflow.service.QuestionNotFoundException;
import com.xiao.courseflow.service.UserNotFoundException;
import com.xiao.courseflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.xiao.courseflow.model.QuestionForm;
import com.xiao.courseflow.model.Result;
import com.xiao.courseflow.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired Result result;
	@Autowired QuestionService qService;

	Boolean submitted = false;
	/*  Show Question List  */
	@GetMapping("/course/{id}")
	public String showQuestionList(@PathVariable("id") Integer id, Model model){
		List<Question> listQuestions = qService.listAllQuestions();
		model.addAttribute("listQuestions",listQuestions);
		return "exams/questions";
	}

	/* Manage Questions*/
	@GetMapping("/course/{id}/question/new")
	public String showNewQForm(@PathVariable("id") Integer id,Model model){
		model.addAttribute("question", new Question());
		model.addAttribute("pageTitle", "Add Question");
		return "exams/updateQuestion";
	}

	@PostMapping("/course/{id}/question/save")
	public String saveQuestion(@PathVariable("id") Integer id, Question q, RedirectAttributes ra){
		qService.save(q);
		ra.addFlashAttribute("message","The question has been saved successfully!");
		return "redirect:/course/{id}";
	}

	@GetMapping("/course/{id}/question/delete/{qid}")
	public String deleteQuestion(@PathVariable("id") Integer id, @PathVariable("qid") Integer qid, RedirectAttributes ra) throws QuestionNotFoundException{
		try {
			qService.delete(qid);
			ra.addFlashAttribute("message","The Question ID: " + qid + " has been deleted!");
		} catch (QuestionNotFoundException e) {
			ra.addFlashAttribute("message",e.getMessage());
		}
		return "redirect:/course/{id}";
	}

	@GetMapping("/course/{id}/question/edit/{qid}")
	public String editQuestion(@PathVariable("id") Integer id, @PathVariable("qid") Integer qid, Model m,RedirectAttributes ra) throws QuestionNotFoundException{
		try {
			Question q = qService.get(qid);
			qService.delete(qid);
			m.addAttribute("question",q);
			m.addAttribute("pageTitle","Edit Question (ID: " + qid + " )");
			return "exams/updateQuestion";
		} catch (QuestionNotFoundException e) {
			ra.addFlashAttribute("message",e.getMessage());
		}
		return "redirect:/course/{id}";
	}

	/* Take exams */
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}

	@GetMapping("/user")
	public String showUser(){
		return "user";
	}

	@GetMapping("/manager")
	public String showManager(){
		return "manager";
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
