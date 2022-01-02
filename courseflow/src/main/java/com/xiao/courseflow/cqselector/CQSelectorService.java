package com.xiao.courseflow.cqselector;

import com.xiao.courseflow.course.Course;
import com.xiao.courseflow.question.Question;
import com.xiao.courseflow.question.QuestionNotFoundException;
import com.xiao.courseflow.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CQSelectorService {
    @Autowired private CQSelectorRepo cqRepo;

    public List<Integer> findQuestionByCourse(Integer cid) {
        List<Integer> qids = cqRepo.findQuestionByCourse(cid);
        return qids;
    }
}
