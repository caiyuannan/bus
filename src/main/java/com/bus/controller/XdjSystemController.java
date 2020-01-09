package com.bus.controller;

import com.bus.javabean.TableBean;
import com.bus.javabean.XdjSystem;
import com.bus.service.XdjSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 系统帐号管理的控制类
 */
@Controller
public class XdjSystemController implements Serializable
{
	@Resource
	private XdjSystemService xdjSystemService;
	TableBean tableBean=new TableBean();

	@RequestMapping("systemManagement")
	public String systemManagement(){
		System.out.println("进入到系统帐号管理界面");
		return "backjsp/XdjSystemManagement";
	}

	@RequestMapping(value ="/XdjSystemManagementServlet")
	@ResponseBody
	public TableBean getXdjSystem(Integer page,Integer limit,HttpServletRequest request){
		TableBean tableBean=new TableBean();
		tableBean.setMsg("");
		tableBean.setCode(0);
		System.out.println(page+','+limit);
		//用户名
		String mangeuserName= request.getParameter("mangeuserName");
		//角色
		String roleName= request.getParameter("roleName");
		XdjSystem xdjSystem=new XdjSystem();
		xdjSystem.setMangeuserName(mangeuserName);
		xdjSystem.setRoleName(roleName);
		List<XdjSystem> listXdjSystem=xdjSystemService.findXdjSystemAll(xdjSystem);
		//List<XdjSystem> listXdjSystem=xdjSystemService.findXdjSystemAll(m,limit,mangeuserName,roleName);
		System.out.println(listXdjSystem.toString());
		tableBean.setCount(5);
		tableBean.setData(listXdjSystem);
		return tableBean;
	}

	//增加insert
	@RequestMapping(value ="/insertXdjSystem")
	public String insertXdjSystem(XdjSystem xdjSystem){
		int result = xdjSystemService.insertXdjSystem(xdjSystem);
		if (result>=0) {
			return "增加成功";
		} else {
			return "增加失败";
		}
		//return xdjUserService.deleteXdjUser(xdjUser);
	}

	//删除delete
	@RequestMapping(value ="/deleteXdjSystem")
	public String deleteXdjSystem(XdjSystem xdjSystem){
		int result = xdjSystemService.deleteXdjSystem(xdjSystem);
		if (result>=0) {
			return "删除成功";
		} else {
			return "删除失败";
		}
		//return xdjUserService.deleteXdjUser(xdjUser);
	}

	//update修改
	@RequestMapping(value ="/updateXdjSystem")
	public String updateXdjSystem(XdjSystem xdjSystem)
	{
		int result = xdjSystemService.updateXdjSystem(xdjSystem);
		if (result >=1)
		{
			return "修改成功";
		} else
		{
			return "修改失败";
		}
		//	return xdjUserService.updateXdjUser(xdjUser);
	}

}
