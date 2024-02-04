package com.example.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	@Autowired
	SqlSession sqlSession;
	public List<Map<String, Object>> list(String product_name){
		return sqlSession.selectList("product.list", "%"+product_name+"%");
	}

	public void insert(Map<String, Object> map) {
		sqlSession.insert("product.insert", map);
	}

}
