package com.luohuacanyue.webmagic.demo.crawler.entity;

import java.util.Date;

public class DangDangBook {
	private String searchKey;//搜索关键字
	private String img;//图书图片
	private String title;//标题
	private String nowPrice;//现价
	private String prePrice;//原价
	private String discount;//折扣
	private String author;//作者
	private String time;//出版时间
	private String press;//出版社
	private String commentNum;//评论数量
	private String detail;//详情
	private String pageNo;//页码
	private Integer numSort;//每页的排序号
	private String pageUrl;//所在页面的url
	private Date createDate;//创建时间
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getNumSort() {
		return numSort;
	}
	public void setNumSort(Integer numSort) {
		this.numSort = numSort;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "DangDangBook [searchKey=" + searchKey + ", img=" + img + ", title=" + title + ", nowPrice=" + nowPrice
				+ ", prePrice=" + prePrice + ", discount=" + discount + ", author=" + author + ", time=" + time
				+ ", press=" + press + ", commentNum=" + commentNum + ", detail=" + detail + ", pageNo=" + pageNo
				+ ", numSort=" + numSort + ", pageUrl=" + pageUrl + "]";
	}
	

}
