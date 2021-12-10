package com.example.spring;

import com.example.spring.model.Question;
import com.example.spring.model.User;
import com.example.spring.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuestionRepositoryTests {
    @Autowired
    private QuestionRepository repo;

    @Test
    public void testListAll(){
        Iterable<Question> questions = repo.findAll();
        Assertions.assertNotNull(questions);
        for (Question q:questions){
            System.out.println(q.toString());
        }
    }
}
