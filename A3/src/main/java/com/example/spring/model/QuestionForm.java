package com.example.spring.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionForm {
    public List<Question> questions;
    public int score;

    public QuestionForm() {
        super();
        score= -5;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void checkScore(int actual, int expected){
        if(actual == expected){
            score++;
            System.out.println(actual+ "   " +expected);
            System.out.println("SCORE "+score);
        }
    }

    public void clearScore(){
        score=-5;
    }
}
