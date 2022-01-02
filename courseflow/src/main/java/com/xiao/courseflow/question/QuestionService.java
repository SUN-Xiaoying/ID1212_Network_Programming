package com.xiao.courseflow.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.xiao.courseflow.model.Question;
import com.xiao.courseflow.model.QuestionForm;
import com.xiao.courseflow.model.Result;
import com.xiao.courseflow.repository.QuestionRepo;
import com.xiao.courseflow.repository.ResultRepo;

@Service
public class QuestionService {
	
	@Autowired
	Question question;
	@Autowired
	private QuestionForm qForm;
	@Autowired
	QuestionRepo qRepo;
	@Autowired
	private Result result;
	@Autowired
	ResultRepo rRepo;

	public List<Question> listAllQuestions(){
		return (List<Question>) qRepo.findAll();
	}

	public void save(Question q) {qRepo.save(q);}

	public Question get(Integer qid) throws QuestionNotFoundException {
		Optional<Question> result = qRepo.findById(qid);
		if(result.isPresent()){
			return result.get();
		}throw new QuestionNotFoundException("Question not found!");
	}

	public void delete(Integer id) throws QuestionNotFoundException{
		Long count = qRepo.countById(id);
		if (count == null || count == 0){
			throw new QuestionNotFoundException("Could not find Question by ID " +id);
		}
		qRepo.deleteById(id);
	}

	public QuestionForm getQuestions() {
		List<Question> allQues = qRepo.findAll();
		List<Question> qList = new ArrayList<Question>();
		
		Random random = new Random();
		
		for(int i=0; i<5; i++) {
			int rand = random.nextInt(allQues.size());
			qList.add(allQues.get(rand));
			allQues.remove(rand);
		}

		qForm.setQuestions(qList);
		
		return qForm;
	}
	
	public int getResult(QuestionForm qForm) {
		int correct = 0;
		
		for(Question q: qForm.getQuestions())
			if(q.getAns() == q.getChose())
				correct++;
		
		return correct;
	}
	
	public void saveScore(Result result) {
		Result saveResult = new Result();
		saveResult.setUsername(result.getUsername());
		saveResult.setTotalCorrect(result.getTotalCorrect());
		rRepo.save(saveResult);
	}
	
	public List<Result> getTopScore() {
		List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
		
		return sList;
	}
}
