package com.xiao.courseflow;

import com.xiao.courseflow.course.Course;
import com.xiao.courseflow.course.CourseNotFoundException;
import com.xiao.courseflow.cqselector.CQSelectorService;
import com.xiao.courseflow.csselector.CSSelectorRepo;
import com.xiao.courseflow.csselector.CSSelectorService;
import com.xiao.courseflow.question.Question;
import com.xiao.courseflow.user.User;
import com.xiao.courseflow.course.CourseRepo;
import com.xiao.courseflow.question.QuestionRepo;
import com.xiao.courseflow.user.UserRepo;
import com.xiao.courseflow.course.CourseService;
import com.xiao.courseflow.question.QuestionNotFoundException;
import com.xiao.courseflow.question.QuestionService;
import com.xiao.courseflow.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CourseflowApplicationTests {
    @Autowired private UserRepo uRepo;
    @Autowired UserService uService;
    @Autowired CourseService cService;
    @Autowired QuestionService qService;
    @Autowired QuestionRepo qRepo;
    @Autowired
    CSSelectorRepo cssRepo;
    @Autowired
    CSSelectorService cssService;
    @Autowired
    CQSelectorService cqService;

    @Test
    public void testPassword() {
        User optionUser = uRepo.findByName("xsu@kth.se");
        System.out.println(optionUser.toString());
        assertThat(optionUser.getPassword()).isEqualTo("sxy123");
    }

    @Test
    public void testEnable() {
        User user1 = uRepo.findByName("xsu@kth.se");
        System.out.println(user1.toString());
        assertThat(user1.isEnabled()).isEqualTo(true);
        User user2 = uRepo.findByName("test@kth.se");
        System.out.println(user2.toString());
        assertThat(user2.isEnabled()).isEqualTo(false);
    }

    @Test
    public void listAllCourses() {
        List<Course> courses=cService.listAll();
        for(Course c: courses){
            System.out.println(c.toString());
        }
    }

    @Test
    public void testFindQuestionById() throws QuestionNotFoundException {
        int qid = 1;
        Optional<Question> result = Optional.ofNullable(qService.get(qid));
        assertThat(result.isPresent()).isEqualTo(true);
        assertThat(result.get().getTitle()).isEqualTo("Test");
        System.out.println(result.get().toString());
    }

    @Test
    public void testDeleteQuestionById() throws QuestionNotFoundException {
        int qid = 1;
        Long count = qRepo.countById(qid);
        System.out.println(count);
        qRepo.deleteById(qid);
        Optional<Question> result = Optional.ofNullable(qService.get(qid));
        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void testSaveQuestion() throws QuestionNotFoundException {
        Question q = new Question();
        q.setId(11);
        q.setTitle("What is a correct syntax to output \"Hello World\" in Java?");
        q.setOptionA("echo \"Hello World\"");
        q.setOptionB("printf(\"Hello World\")");
        q.setOptionC("System.out.println(\"Hello World\")");
        q.setAns(3);
        q.setChose(-1);
        qRepo.save(q);
    }

    @Test
    public void testFindCourse() throws CourseNotFoundException {
        int uid=2;
        List<Course> result = uService.getCourses(uid);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getCode()).isEqualTo("ID1212");
        for(Course i: result){
            System.out.println(i);
        }
    }

    @Test
    public void testFindQuestions() throws QuestionNotFoundException {
        int id1=1;
        int id2=2;
        List<Question> result1 = qService.getQuestions(2, id1);
        List<Question> result2 = qService.getQuestions(2, id2);

        assertThat(result1.size()).isEqualTo(9);
        System.out.println(result1.get(0));
        assertThat(result2.size()).isEqualTo(1);

    }
}
