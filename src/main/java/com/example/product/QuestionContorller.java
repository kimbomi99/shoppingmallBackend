package com.example.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionContorller {
	@Autowired
	QuestionDAO questionDao;

	@RequestMapping("/question")
	public List<Map<String, Object>> list(){
		return questionDao.list();
	}

	@RequestMapping("/insertQuestion")
	public void insert(@RequestParam Map<String, Object> map) {
		questionDao.insert(map);
	}

	@RequestMapping("/deleteQuestion")
	public void delete(int question_number) {
		questionDao.delete(question_number);
	}

}
