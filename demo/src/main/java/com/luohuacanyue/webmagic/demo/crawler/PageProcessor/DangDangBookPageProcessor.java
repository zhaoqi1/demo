package com.luohuacanyue.webmagic.demo.crawler.PageProcessor;

import java.util.ArrayList;
import java.util.List;

import com.luohuacanyue.webmagic.demo.crawler.Pipeline.DangDangBookPipeline;
import com.luohuacanyue.webmagic.demo.crawler.entity.DangDangBook;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

public class DangDangBookPageProcessor implements PageProcessor{
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	public static int num=0;

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		List<DangDangBook>  dangDangBookList = new ArrayList<DangDangBook>();
		int index = Integer.parseInt(page.getHtml().css("ul[class='bigimg'] li:last-of-type","ddt-pit").toString());
		
		for(int i = 0;i<index;i++) {
			DangDangBook book = new DangDangBook();
			//获取图片url
			book.setImg(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/a").css("img", "data-original").toString());
			if( book.getImg().equals("")) {
				book.setImg(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/a").css("img", "src").toString());
			}
			book.setTitle(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='name']/a/font/text()").toString()+page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='name']/a/text()").toString().trim());

			//获取现价
			book.setNowPrice(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']//span[@class='search_now_price']/text()").toString().trim());
			
			//获取原价
			if(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']").toString().indexOf("search_pre_price")>=0) {
				book.setPrePrice(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='price']/span[@class='search_pre_price']/text()").toString().trim());
			}
			//获取折扣
			if(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']").toString().indexOf("<span class=\"search_discount\">")>=0) {
				book.setDiscount(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']//span[@class='search_discount']/text()").toString().trim());
			}
			book.setAuthor(getTagAllText( page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='search_book_author']/span[1]").toString()).trim());
			book.setTime(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='search_book_author']/span[2]/text()").toString());
			book.setPress(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='search_book_author']/span[3]/a/text()").toString());
			book.setCommentNum(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='search_star_line']/a/text()").toString());
			book.setDetail(page.getHtml().xpath("//ul[@class='bigimg']/li[@ddt-pit='"+(i+1)+"']/p[@class='detail']/text()").toString());
			book.setPageNo(getUrlValue("page_index",page.getRequest().getUrl()));
			book.setPageUrl(page.getRequest().getUrl());
			book.setSearchKey(getUrlValue("key",page.getRequest().getUrl()));;
			book.setNumSort((i+1));
			dangDangBookList.add(book);
		}
		
		page.putField("dangDangBookList", dangDangBookList);
	
		List<String> urlList = page.getHtml().links().regex("\\?key=java&act=input&page_index=[0-9]{1,}").all();
		for (String url : urlList) {
			url="http://search.dangdang.com/"+url;
			System.err.println(url);
		}
		num++;
		System.err.println("已抓取个"+num+"页面");
		page.addTargetRequests(urlList);
		


	}
	
	public static void main (String args[]) {
		Spider.create(new DangDangBookPageProcessor()).setScheduler(new RedisScheduler("localhost")).addUrl("http://search.dangdang.com/?key=java&act=input&page_index=8").addPipeline(new DangDangBookPipeline()).addPipeline(new FilePipeline("D:\\webmagic")).run();
		/*Jedis jedis = new Jedis("localhost");
		jedis.flushAll();*/
	
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
	//从get的url中获取参数的值
	public static String getUrlValue(String key,String url) {
		if(key!=null && url!=null) {
			int start = url.indexOf("?");
			int fromIndex = url.indexOf(key, start);
			if(fromIndex==-1) {
				return null;
			}
			int firstEnd = url.indexOf("&", fromIndex);
			if(firstEnd!=-1) {
				return url.substring(fromIndex+key.length()+1, firstEnd);
			}else {
				return url.substring(fromIndex+key.length()+1);
			}
			
			
		}
		return null;
		
	}

}
