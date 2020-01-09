package com.bus.controller;

import com.bus.javabean.CynRoleBean;
import com.bus.javabean.TableBean;
import com.bus.service.XdjRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 角色配置的控制类
 */
@Controller
public class XdjRoleController implements Serializable
{
	@Resource
	private XdjRoleService xdjRoleService;
	TableBean tableBean=new TableBean();

	@RequestMapping("role")
	public ModelAndView role(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("backjsp/Xdjrole");
		System.out.println("进入到系统帐号管理界面");
		return modelAndView;
	}

	@RequestMapping(value ="/XdjRoleServlet")
	@ResponseBody
	public TableBean getXdjRole(Integer page, Integer limit, HttpServletRequest request){
		String roleName=request.getParameter("roleName");
		String roleSelect=request.getParameter("roleSelect");
		TableBean tableBean=new TableBean();
		tableBean.setMsg("");
		tableBean.setCode(0);
		System.out.println(page+','+limit);
		CynRoleBean cynRoleBean=new CynRoleBean();
		List<CynRoleBean> listXdjRole=xdjRoleService.findXdjRoleAll(roleName,Integer.valueOf(page),Integer.valueOf(limit));
		System.out.println(listXdjRole.toString());
		tableBean.setCount(xdjRoleService.getTableCount());
		tableBean.setData(listXdjRole);
		return tableBean;
	}

	//增加insert
	@RequestMapping(value ="/insertXdjRole")
	@ResponseBody
	public String insertXdjRole(CynRoleBean cynRoleBean){
		int result = xdjRoleService.getinsertXdjRole(cynRoleBean);
		if (result>=0) {
			return "增加成功";
		} else {
			return "增加失败";
		}
		//return xdjUserService.deleteXdjUser(xdjUser);
	}

	//删除delete
	@RequestMapping(value ="/deleteXdjRole")
	@ResponseBody
	public String deleteXdjRole(CynRoleBean cynRoleBean){
		int result = xdjRoleService.getdeleteXdjRole(cynRoleBean);
		if (result>=0) {
			return "删除成功";
		} else {
			return "删除失败";
		}
		//return xdjUserService.deleteXdjUser();
	}

	//update修改
	@RequestMapping(value ="/updateXdjRole")
	@ResponseBody
	public String updateXdjRole(CynRoleBean cynRoleBean)
	{
		int result = xdjRoleService.getupdateXdjRole(cynRoleBean);
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
