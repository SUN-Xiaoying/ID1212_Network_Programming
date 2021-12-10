package com.example.spring;

import com.example.spring.model.Result;
import com.example.spring.repository.QuestionRepository;
import com.example.spring.repository.ResultRepositery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ResultRepoTest {
    @Autowired
    private ResultRepositery repo;

    @Test
    public void testListAll(){
        Iterable<Result> res = repo.findAll();
        Assertions.assertNotNull(res);
        for (Result r:res){
            System.out.println(r.toString());
        }
    }
}
