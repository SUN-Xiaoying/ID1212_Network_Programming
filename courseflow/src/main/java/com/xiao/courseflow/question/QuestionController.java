package com.xiao.courseflow.question;

import java.util.ArrayList;
import java.util.List;

import com.xiao.courseflow.cqselector.CQSelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuestionController {

	@Autowired QuestionService qService;
	@Autowired CQSelectorService cqService;

	/*  Show Question List  */
	@GetMapping("/course/{cid}")
	public String showQuestionList(@PathVariable("cid") Integer cid, Model model) throws QuestionNotFoundException {
		List<Integer> qids = cqService.findQuestionByCourse(cid);
		List<Question> listQuestions = new ArrayList<>();
		for (Integer qid : qids) {
			listQuestions.add(qService.get(qid));
		}
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
	public String deleteQuestion(@PathVariable("id") Integer id, @PathVariable("qid") Integer qid, RedirectAttributes ra){
		try {
			qService.delete(qid);
			ra.addFlashAttribute("message","The Question ID: " + qid + " has been deleted!");
		} catch (QuestionNotFoundException e) {
			ra.addFlashAttribute("message",e.getMessage());
		}
		return "redirect:/course/{id}";
	}

	@GetMapping("/course/{id}/question/edit/{qid}")
	public String editQuestion(@PathVariable("id") Integer id, @PathVariable("qid") Integer qid, Model m,RedirectAttributes ra){
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


}
