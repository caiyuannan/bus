package com.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制测试类
 */
@Controller
public class TestController
{

	@RequestMapping("/test")
	public String Test(){

		return "backjsp/test";
	}
}
