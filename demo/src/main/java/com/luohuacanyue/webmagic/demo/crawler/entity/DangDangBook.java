package com.luohuacanyue.webmagic.demo.crawler.entity;

public class DangDangBook {
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
	@Override
	public String toString() {
		return "DangDangBook [img=" + img + ", title=" + title + ", nowPrice=" + nowPrice + ", prePrice=" + prePrice
				+ ", discount=" + discount + ", author=" + author + ", time=" + time + ", press=" + press
				+ ", commentNum=" + commentNum + ", detail=" + detail + "]";
	}
	

}
