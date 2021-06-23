package com.gyg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郭永钢
 */
@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}

	@ResponseBody
	@RequestMapping("/hi")
	public String hi(){
		return "hi";
	}
}
