package com.bus.controller;

import com.bus.service.XdjUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 许达军  乘客用户的控制类
 */
@Controller
public class XdjUserController
{
	@Resource
	private XdjUserService xdjUserService;

	//登录
	@RequestMapping("/userlogin")
	public String login(){
		return "front/userlogin";

	}

}
