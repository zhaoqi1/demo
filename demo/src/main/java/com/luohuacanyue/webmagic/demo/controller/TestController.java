package com.luohuacanyue.webmagic.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping(value= "hello")
	public String test() {
		return "hello";
	}

}
