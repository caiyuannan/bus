package com.bus.config;

import com.bus.interceptor.CynLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CynMyConfigurer implements WebMvcConfigurer
{

	//@Override
	//public void addInterceptors(InterceptorRegistry registry) {
	//
	//	registry.addInterceptor(new CynLoginInterceptor())
	//			.addPathPatterns("/**")
	//			.excludePathPatterns("/login","/login.jsp", "/error")
	//			.excludePathPatterns("/layui/**","/css/**", "/js/**","/img/**");
	//}
}
