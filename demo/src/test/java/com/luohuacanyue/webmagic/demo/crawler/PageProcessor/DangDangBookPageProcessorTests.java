package com.luohuacanyue.webmagic.demo.crawler.PageProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luohuacanyue.webmagic.demo.crawler.Pipeline.DangDangBookDbPipeline;
import com.luohuacanyue.webmagic.demo.crawler.Pipeline.DangDangBookPipeline;
import com.luohuacanyue.webmagic.demo.util.FileDownloadUtil;

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
	
	/**
	 * 下载文件
	 */
	@Test
	public void downloadFileTest() {
		
		FileDownloadUtil.fileDownload("https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=e7c761355dda81cb51e685cd6264d0a4/4bed2e738bd4b31ccda81d7a8bd6277f9f2ff85f.jpg",null,null);
		
	}

}
