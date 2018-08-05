package com.luohuacanyue.webmagic.demo.crawler.PageProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luohuacanyue.webmagic.demo.crawler.Pipeline.DangDangBookDbPipeline;
import com.luohuacanyue.webmagic.demo.crawler.Pipeline.DangDangBookPipeline;

import redis.clients.jedis.Jedis;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DangDangBookPageProcessorTests {
	
	
	@Autowired
	DangDangBookDbPipeline dangDangBookDbPipeline;
	
	@Test
	public void dangDangBookDbPipelineTest() {
		Jedis jedis = new Jedis("localhost");
		jedis.flushAll();
		Spider.create(new DangDangBookPageProcessor()).setScheduler(new RedisScheduler("localhost")).addUrl("http://search.dangdang.com/?key=java&act=input&page_index=1").addPipeline(new DangDangBookPipeline()).addPipeline(dangDangBookDbPipeline/*new DangDangBookDbPipeline()*/)/*.addPipeline(new FilePipeline("D:\\webmagic"))*/.run();	
	}

}
