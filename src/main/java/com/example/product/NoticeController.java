package com.example.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

	@Autowired
	NoticeDAO noticeDao;

	@RequestMapping("/notice")
	public List<Map<String, Object>> list(@RequestParam(defaultValue="") String title){
		return noticeDao.list(title);
	}

	@RequestMapping("/insertNotice")
	public void insert(@RequestParam Map<String, Object> map) {
		noticeDao.insert(map);
	}

	@RequestMapping("/detailNotice/{notice_number}")
	public Map<String, Object> detail(@PathVariable String notice_number){
		return noticeDao.detail(notice_number);
	}
}
