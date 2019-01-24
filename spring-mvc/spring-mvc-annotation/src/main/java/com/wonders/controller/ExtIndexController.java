package com.wonders.controller;


import com.wonders.annotation.ExtController;
import com.wonders.annotation.ExtRerequestMapping;

@ExtController
@ExtRerequestMapping("/ext")
public class ExtIndexController {
	//ext/test/?name=122&age=6440654
	@ExtRerequestMapping("/test")
	public String test() {
		System.out.println("手写springmvc框架...");
		return "index";
	}

}
