package com.luohuacanyue.webmagic.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	@RequestMapping(value= "hello")
	@PreAuthorize("hasAnyRole('USER')")
//	@PreAuthorize("hasAuthority('USER')")
	public String test() {
		return "hello";
	}
	
	@RequestMapping(value= "loginSuccess")
	public String loginSuccess() {
		return "loginSuccess";
	}
	
	@RequestMapping(value= "loginFail")
	public String loginFail() {
		return "loginFail";
	}
	@RequestMapping(value= "hello1")
	public String hello1() {
		return "hello1";
	}

}
