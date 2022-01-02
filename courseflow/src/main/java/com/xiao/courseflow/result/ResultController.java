package com.xiao.courseflow.result;

import com.xiao.courseflow.course.CourseNotFoundException;
import com.xiao.courseflow.course.CourseService;
import com.xiao.courseflow.question.Question;
import com.xiao.courseflow.question.QuestionForm;
import com.xiao.courseflow.question.QuestionNotFoundException;
import com.xiao.courseflow.question.QuestionService;
import com.xiao.courseflow.user.UserNotFoundException;
import com.xiao.courseflow.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ResultController {
    @Autowired Result result;
    @Autowired UserService uService;
    @Autowired CourseService cService;
    @Autowired ResultService rService;
    @Autowired QuestionService qService;
    @Autowired QuestionForm qForm;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @GetMapping("/user/{uid}/course/{cid}/quiz")
    public String quiz(@PathVariable("uid") Integer uid, @PathVariable("cid") Integer cid, Model m, RedirectAttributes ra){

        submitted = false;

        result.setUid(uid);
        result.setCid(cid);
        try{
            result.setUsername(uService.get(uid).getUsername());
        }catch (UserNotFoundException ue){
            ra.addFlashAttribute("message",ue.getMessage());
        }
        try{
            result.setSubject(cService.get(cid).getSubject());
        }catch (CourseNotFoundException ce){
            ra.addFlashAttribute("message",ce.getMessage());
        }

        try{
            qForm.setQuestions(qService.getQuestions(uid,cid));
            m.addAttribute("qForm", qForm);
        }catch (QuestionNotFoundException qe){
            ra.addFlashAttribute("message",qe.getMessage());
            return "redirect:/user/{uid}";
        }

        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
        if(!submitted) {
            result.setTotalCorrect(rService.getResult(qForm));
            rService.saveScore(result);
            m.addAttribute("result",result);
            submitted = true;
        }

        return "result";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = rService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard";
    }

}
