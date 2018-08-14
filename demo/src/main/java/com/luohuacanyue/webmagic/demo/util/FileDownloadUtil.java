package com.luohuacanyue.webmagic.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.util.StringUtils;

public class FileDownloadUtil {
	
	private final  static int cache = 10*1024;
	
	private final static String localPath="D://zhaoqi/文档/";
	
	public static void fileDownload(String url ,String filePath,CookieStore cookieStore) {
		if(filePath == null) {
			filePath=localPath;
		}
//		HttpClient client = HttpClientBuilder.create();
		DefaultHttpClient client = new DefaultHttpClient();
		if(cookieStore != null) {
			client.setCookieStore(cookieStore);
		}
		
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			pirntHeaders(response);
			
			HttpEntity entity=response.getEntity();
			InputStream is = entity.getContent();
			
//			filePath+=getFileNameByHeader(response);
			filePath+=getFileNameByUrl(url);
			File file = new File(filePath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			
			byte[] buffer = new byte[cache];
			int ch =0;
			while ((ch=is.read(buffer))!=-1) {
				fileout.write(buffer, 0, ch);
			}
			
			
			is.close();
			fileout.flush();
			fileout.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 根据获取的HTTPResponse从相应头的获取文件的名字
	 * @param response
	 * @return
	 */
	public static String getFileNameByHeader(HttpResponse response) {
		
		Header contentHeader =response.getFirstHeader("Content-Disposition");
		String fileName = null;
		if(contentHeader!= null) {
			HeaderElement[] values =contentHeader.getElements();
			if(values.length == 1) {
				NameValuePair param = values[0].getParameterByName("filename");
				if(param !=null) {
					fileName = param.getValue();
				}
			}
			
		}
		
		if(fileName==null) {
			fileName=String.valueOf(System.currentTimeMillis())+"无法从响应头中获取文件名称和类型";
		}
		return fileName;
		
	}
	
	/**
	 * 从请求的url中获取文件名称和类型
	 * @param url
	 * @return
	 */
	public static String getFileNameByUrl(String url) {
		int endIndex =url.indexOf("?");
		if(endIndex == -1) {
			endIndex=url.length();
		}
		String subUrl = url.substring(0, endIndex);
		int startIndex = subUrl.lastIndexOf("/")+1;
		if(startIndex!=-1 && startIndex<endIndex) {
			return subUrl.substring(startIndex, endIndex);
		}
		return String.valueOf(System.currentTimeMillis())+"无法根据url识别文件类型和文件名";
	}
	
	/**
	 * 打印获取httpresponse的相应头
	 * @param response
	 */
	public static void  pirntHeaders(HttpResponse response) {
		Header[] headers =response.getAllHeaders();
		for (Header header : headers) {
			System.err.println(header);
		}
		
	}
}
