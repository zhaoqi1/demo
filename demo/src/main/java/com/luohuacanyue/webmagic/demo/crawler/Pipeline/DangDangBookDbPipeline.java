package com.luohuacanyue.webmagic.demo.crawler.Pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luohuacanyue.webmagic.demo.crawler.entity.DangDangBook;
import com.luohuacanyue.webmagic.demo.service.DangDangBookService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class DangDangBookDbPipeline  implements Pipeline{
	@Autowired
	private DangDangBookService dangDangBookService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		Object o =resultItems.get("dangDangBookList");
		List<DangDangBook> dangDangBookList = new ArrayList<DangDangBook>();
		if(o instanceof List) {
			dangDangBookList= (List<DangDangBook>)o;
		}
		dangDangBookService.insertBookList(dangDangBookList);
		
	}

}
