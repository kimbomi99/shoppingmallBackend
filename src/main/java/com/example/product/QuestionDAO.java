package com.example.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAO {

	@Autowired
	SqlSession sqlSession;
	public List<Map<String, Object>> list(){
		return sqlSession.selectList("question.list");
	}

	public void insert(Map<String, Object> map) {
		sqlSession.insert("question.insert", map);
	}

	public void delete(int question_number) {
		sqlSession.delete("question.delete", question_number);
	}
}
