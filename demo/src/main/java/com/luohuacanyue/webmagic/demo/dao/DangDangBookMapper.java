package com.luohuacanyue.webmagic.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.luohuacanyue.webmagic.demo.crawler.entity.DangDangBook;

@Mapper
public interface DangDangBookMapper {

	void insertBook(DangDangBook dangDangBook);

}
