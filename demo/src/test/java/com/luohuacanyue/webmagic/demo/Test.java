package com.luohuacanyue.webmagic.demo;

public class Test {
	public static void main (String arg[]) {
		String url = "http://search.dangdang.com/?key=java&act=input&page_index=1";
		String key="page_index";
		System.err.println(getUrlValue(key,url));
		
	}
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
