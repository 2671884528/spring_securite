package com.gyg.user.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@RequestMapping("/success")
	public String success(){
		return "home";
	}


	@RequestMapping("/index")
	@ResponseBody
	public String index(){
		return "index";
	}



	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}

	/**
	 * 通过注解的形式来设置请求接口的权限，
	 * 需要在Application开启 @EnableGlobalMethodSecurity(securedEnabled = true)
	 * Secured：注解可以设置访问接口的权限
	 * @return
	 */
	@RequestMapping("/admin")
	@ResponseBody
	@Secured({"ROLE_admin"})
	public String admin(){
		return "admin";
	}

	@RequestMapping("/admins")
	@ResponseBody
	@Secured({"ROLE_admins"})
	public String admins(){
		return "admins";
	}

	/**
	 * PreAuthorize注解传的value参数是方法通过反射相当于是config配置
	 * @return
	 */
	@RequestMapping("/gyg")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('gyg')")
	public String admin1() {
		return "gyg";
	}
}

