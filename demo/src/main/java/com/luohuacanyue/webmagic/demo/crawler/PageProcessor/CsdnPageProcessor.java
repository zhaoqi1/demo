package com.luohuacanyue.webmagic.demo.crawler.PageProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class CsdnPageProcessor implements PageProcessor{
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		page.putField("resultTitle", page.getHtml().xpath("//div[@class='album_detail_wrap']//a[@class='album_detail_title']/text()").all());
		/*page.putField("resultContext", field);
		page.putField("downloadNum", field);
		page.putField("resourceSize", field);
		page.putField("uploadPeople", field);
		page.putField("uploadTime", field);
		page.putField("pictUrl", field);*/
		
		// TODO Auto-generated method stub
		
	}
	

	public static void main(String args[]) {
		Spider.create(new CsdnPageProcessor()).addPipeline(new ConsolePipeline()).addUrl("https://download.csdn.net/psearch/0/10/0/0/1/java").run();
		
		
		
		
	}

}
