package com.luohuacanyue.webmagic.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luohuacanyue.webmagic.demo.crawler.entity.DangDangBook;
import com.luohuacanyue.webmagic.demo.dao.DangDangBookMapper;

@Service
public class DangDangBookService {
	@Autowired
	private DangDangBookMapper dangDangBookDao;
	
	
	@Transactional
	public void insertBookList(List<DangDangBook> list) {
		for (DangDangBook dangDangBook : list) {
			dangDangBookDao.insertBook(dangDangBook);
		}
		
	}
	
	

}
