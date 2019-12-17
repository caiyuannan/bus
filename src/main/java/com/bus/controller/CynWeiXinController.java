package com.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CynWeiXinController
{
	/*@RequestMapping("/web/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		return path;
	}*/
	@RequestMapping("/login")
	public void login()
	{

	}
}
