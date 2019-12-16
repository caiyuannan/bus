package com.bus.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class CynLoginInterceptor implements HandlerInterceptor
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
	{
		//获取请求发来的路径
		String url=request.getRequestURI();

		if(url.endsWith("/login")){
			return true;
		}
		String contextPath=request.getContextPath();
		Object obj=request.getSession().getAttribute("user");
		if(obj==null){
			response.sendRedirect(contextPath+"/web/login");
		}else{
			return true;
		}
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception
	{

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception
	{

	}
}
