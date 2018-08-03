package com.luohuacanyue.webmagic.demo.crawler.PageProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class DangDangBookPageProcessor implements PageProcessor{
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public void process(Page page) {
		//图片
		page.putField("img", page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']").css("img", "src").toString());
		//标题
		page.putField("title", page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='name']/a/font/text()").toString()+page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='name']/a/text()").toString().trim());
		//现价
		page.putField("nowPrice", page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='price']/span[@class='search_now_price']/text()").toString().trim());
		//原价
		page.putField("prePrice", page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='price']/span[@class='search_pre_price']/text()").toString().trim());
		//折扣
		page.putField("discount", page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='price']/span[@class='search_discount']/text()").toString().trim());
		//作者
		page.putField("author",getTagAllText( page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='search_book_author']/span[1]").toString()).trim());
		//出版时间
		page.putField("time",page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='search_book_author']/span[2]/text()").toString());
		//出版社
		page.putField("press",page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='search_book_author']/span[3]/a/text()").toString());
		//评论条数
		page.putField("commentNum",page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='search_star_line']/a/text()").toString());
		//详情
		page.putField("detail",page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='1']/p[@class='detail']/text()").toString());
	}
	
	public static void main (String args[]) {
		Spider.create(new DangDangBookPageProcessor()).addUrl("http://search.dangdang.com/?key=java&act=input").addPipeline(new ConsolePipeline()).run();
	}
	
	//将标签删除
	public static String getTagAllText(String tag) {
		if(tag == null || tag.length() ==0) {
			return tag;
		}
		int index1 = tag.indexOf("<");
		int index2 = tag.indexOf(">");
		int length = tag.length();
		while(index1>=0 && index2>=0 && index1<index2) {
			tag=tag.substring(0, index1)+tag.substring(index2+1, length);
			index1 = tag.indexOf("<");
			index2 = tag.indexOf(">");
			length = tag.length();
		}	
		return tag;
	}

}
