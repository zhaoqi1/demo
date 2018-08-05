package com.luohuacanyue.webmagic.demo.crawler.Pipeline;

import java.util.ArrayList;
import java.util.List;

import com.luohuacanyue.webmagic.demo.crawler.entity.DangDangBook;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class DangDangBookPipeline implements Pipeline{

	@Override
	public void process(ResultItems resultItems, Task arg1) {
		Object o =resultItems.get("dangDangBookList");
		List<DangDangBook> dangDangBookList = new ArrayList<DangDangBook>();
		if(o instanceof List) {
			dangDangBookList= (List<DangDangBook>)o;
		}
		for (DangDangBook dangDangBook : dangDangBookList) {
			System.err.println(dangDangBook.toString());
		}
		
	}

}
