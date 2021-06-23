package com.gyg.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gyg
 * @since 2021-06-19
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@PostMapping("/login")
	@ResponseBody
	public String login(){
		return "login";
	}


	@RequestMapping("/success")
	public String success(){
		return "hello";
	}

	@RequestMapping("/index")
	@ResponseBody
	public String index(){
		return "index";
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}

}

