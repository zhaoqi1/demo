package com.luohuacanyue.webmagic.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luohuacanyue.webmagic.demo.crawler.entity.DangDangBook;
import com.luohuacanyue.webmagic.demo.service.DangDangBookService;

@RestController
public class DangDangBookController {
	
	@Autowired
	private DangDangBookService dangDangBookService;
	
	@GetMapping(value="insert")
	public String insertBookList() {
		dangDangBookService.insertBookList(new ArrayList<DangDangBook>());
		return "insert";
	}

}
