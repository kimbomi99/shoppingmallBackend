package com.example.product;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO {
	@Autowired
	SqlSession sqlSession;
	public List<Map<String, Object>> list(String title){
		return sqlSession.selectList("notice.list", "%"+title+"%");
	}
	public void insert(Map<String, Object> map) {
		sqlSession.insert("notice.insert", map);
	}
	public Map<String, Object> detail(String notice_number){
		return sqlSession.selectOne("notice.detail", notice_number);
	}

}
