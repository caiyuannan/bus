package com.bus.controller;

import com.bus.javabean.TableBean;
import com.bus.javabean.XdjUser;
import com.bus.service.XdjUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
/**
 * 乘客用户的控制类
 */
@Controller
public class XdjUserController implements Serializable
{
	@Resource
	private XdjUserService xdjUserService;

	//分页条数
	//private  final Integer pageSize = 5;

	 //乘客账号管理

	@RequestMapping("/passengerManagement")
	public String accountManagement(HttpServletRequest request){
		System.out.println("进入到乘客用户管理界面");
		return "backjsp/XdjPassengerManagement";
	}

	@RequestMapping(value = "/XdjPassengerManagementServlet")
	@ResponseBody
	public TableBean getXdjUser(HttpServletRequest request)
	{
		TableBean tableBean=new TableBean();
		tableBean.setMsg("");
		tableBean.setCode(0);
		String limit=request.getParameter("limit");
		int limInt=Integer.valueOf(limit);
		String page=request.getParameter("page");
		int pageInt=Integer.valueOf(page);
		//用户名
		String userName= request.getParameter("userName");
		//用户的手机号
		String userPhonenumber=request.getParameter("userPhonenumber");
		XdjUser xdjUser=new XdjUser();
		xdjUser.setUserName(userName);
		xdjUser.setUserPhonenumber(userPhonenumber);
		List<XdjUser> listXdjUser=xdjUserService.findUserInfAll(xdjUser);
		System.out.println(listXdjUser.toString());

		//int count=xdjUserService.findUserCount(xdjUser);
		tableBean.setCount(7);
		tableBean.setData(listXdjUser);
		return tableBean;


	}
	 //增加insert

	@RequestMapping(value ="/insertXdjUser")
	@ResponseBody
	public String insertXdjUser(XdjUser xdjUser){
		int result = xdjUserService.insertXdjUser(xdjUser);
		if (result>=0) {
			return "增加成功";
		} else {
			return "增加失败";
		}
		//return xdjUserService.deleteXdjUser(xdjUser);
	}

	//删除delete

	@RequestMapping(value ="/deleteXdjUser")
	@ResponseBody
	public String deleteXdjUser(XdjUser xdjUser){
		int result = xdjUserService.deleteXdjUser(xdjUser);
			if (result>=0) {
				return "删除成功";
			} else {
				return "删除失败";
			}
		//return xdjUserService.deleteXdjUser(xdjUser);
		}
	 //update修改

	@RequestMapping(value ="/updateXdjUser")
	@ResponseBody
	public String updateXdjUser(XdjUser xdjUser)
	{
		int result = xdjUserService.updateXdjUser(xdjUser);
		if (result >=1)
		{
			return "修改成功";
		} else
		{
			return "修改失败";
		}
	//	return xdjUserService.updateXdjUser(xdjUser);
	}
	//@RequestMapping("/demo1/{url}")
	//public String matchUrl(@PathVariable(value = "url") String path)
	//{
	//	return "backjsp/"+path;
	//}
}
