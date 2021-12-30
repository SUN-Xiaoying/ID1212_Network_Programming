package com.xiao.courseflow;

import com.xiao.courseflow.model.Course;
import com.xiao.courseflow.model.Question;
import com.xiao.courseflow.model.User;
import com.xiao.courseflow.repository.CourseRepo;
import com.xiao.courseflow.repository.QuestionRepo;
import com.xiao.courseflow.repository.UserRepo;
import com.xiao.courseflow.service.CourseService;
import com.xiao.courseflow.service.QuestionNotFoundException;
import com.xiao.courseflow.service.QuestionService;
import com.xiao.courseflow.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CourseflowApplicationTests {
    @Autowired private UserRepo repo;
    @Autowired UserService uService;
    @Autowired CourseService cService;
    @Autowired private CourseRepo cRepo;
    @Autowired QuestionService qService;
    @Autowired QuestionRepo qRepo;

    @Test
    public void testPassword() {
        User optionUser = repo.findByName("xsu@kth.se");
        System.out.println(optionUser.toString());
        assertThat(optionUser.getPassword()).isEqualTo("sxy123");
    }

    @Test
    public void testEnable() {
        User user1 = repo.findByName("xsu@kth.se");
        System.out.println(user1.toString());
        assertThat(user1.isEnabled()).isEqualTo(true);
        User user2 = repo.findByName("test@kth.se");
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
        q.setId(22);
        q.setTitle("Test");
        q.setOptionA("A");
        q.setOptionB("B");
        q.setOptionC("C");
        q.setAns(1);
        q.setChose(-1);
        qRepo.save(q);

        Optional<Question> result = Optional.ofNullable(qService.get(22));
        assertThat(result.isPresent()).isEqualTo(true);
        assertThat(result.get().getTitle()).isEqualTo("Test");


    }
}
