package com.bus.controller;

import com.bus.service.XdjUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 许达军 乘客用户的控制类
 */
@Controller
public class XdjUserController
{
	@Resource
	private XdjUserService xdjUserService;
	private ModelAndView modelAndView = new ModelAndView();

	//登录
	@RequestMapping("/userlogin")
	public String login(){
		return "front/userlogin";

	}
   //乘客账号管理
	@RequestMapping("/accountManagement")
	public ModelAndView accountManagement(String userName,String userPhonenumber){
		System.out.println("进入到乘客用户管理界面");
		modelAndView.setViewName("backjsp/XdjAccountManagement");
		return modelAndView;

	}

}
