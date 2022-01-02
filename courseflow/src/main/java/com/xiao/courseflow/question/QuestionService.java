package com.xiao.courseflow.question;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.xiao.courseflow.cqselector.CQSelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.xiao.courseflow.result.Result;
import com.xiao.courseflow.result.ResultRepo;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo qRepo;

	@Autowired
	CQSelectorService cqService;

	public List<Question> listAllQuestions() {
		return (List<Question>) qRepo.findAll();
	}

	public void save(Question q) {
		qRepo.save(q);
	}

	public Question get(Integer qid) throws QuestionNotFoundException {
		Optional<Question> result = qRepo.findById(qid);
		if (result.isPresent()) {
			return result.get();
		}
		throw new QuestionNotFoundException("Question not found!");
	}

	public void delete(Integer id) throws QuestionNotFoundException {
		Long count = qRepo.countById(id);
		if (count == null || count == 0) {
			throw new QuestionNotFoundException("Could not find Question by ID " + id);
		}
		qRepo.deleteById(id);
	}

	public List<Question> getAllQuestions(Integer cid) throws QuestionNotFoundException {
		List<Integer> qids = cqService.findQuestionByCourse(cid);
		List<Question> allQues = new ArrayList<>();

		for (Integer qid : qids) {
			allQues.add(get(qid));
		}

		return allQues;
	}

	public List<Question> getQuestions(Integer uid, Integer cid) throws QuestionNotFoundException {
		List<Question> allQues = getAllQuestions(cid);
		List<Question> questions = new ArrayList<>();

		if (allQues.size() > 5) {
			Random random = new Random();
			for (int i = 0; i < 5; i++) {
				int rand = random.nextInt(allQues.size());
				questions.add(allQues.get(rand));
				allQues.remove(rand);
			}
		} else {
			questions = allQues;
		}

		return questions;
	}

}

