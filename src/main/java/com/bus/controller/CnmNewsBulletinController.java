package com.bus.controller;

import com.bus.javabean.CnmCooperativeBean;
import com.bus.javabean.CnmNewsBulletinBean;
import com.bus.javabean.CnmTablePageInf;
import com.bus.service.CnmCooperativeService;
import com.bus.service.CnmNewsBulletinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CnmNewsBulletinController
{
	@Resource
	CnmNewsBulletinService cnmNewsBulletinService;
	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();

	@RequestMapping("/newsbulletin")
	public ModelAndView treeUrl(){

		ModelAndView modelAndView=new ModelAndView();
//		modelAndView.addObject("roleSelect",cnmNewsBulletinService.getNewsBulletinType());
		modelAndView.setViewName("backjsp/newsBulletin");
		return modelAndView;
	}


	@RequestMapping("/showNewsBulletinTable")
	@ResponseBody
	public CnmTablePageInf getUserTable(HttpServletRequest request, String page, String limit){

		String newsBulletinTitlee=request.getParameter("newsBulletinTitlee");
		String newsBulletinTimee=request.getParameter("newsBulletinTimee");
		String newsBulletinTypeSelect=request.getParameter("newsBulletinTypeSelect");

		System.out.println(newsBulletinTitlee+newsBulletinTimee+newsBulletinTypeSelect+"------------");
		cnmTablePageInf.setCode(0);
		cnmTablePageInf.setMsg("");
		cnmTablePageInf.setData(cnmNewsBulletinService.gettable(newsBulletinTitlee,newsBulletinTypeSelect,newsBulletinTimee,Integer.valueOf(page),Integer.valueOf(limit)));
		cnmTablePageInf.setCount(cnmNewsBulletinService.getTableCount());

//		List<CnmNewsBulletinBean> tableList=cnmNewsBulletinService.getNewsBulletinType();

		return cnmTablePageInf;


	}


	@RequestMapping("/deleteNewsBulletinTable")
	@ResponseBody
	public String deleteNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean){
		int num=cnmNewsBulletinService.getDeleteNewsBulletinTable(cnmNewsBulletinBean);
		if (num>0){
			return "删除成功";
		}
		return "删除失败";
	}

	@RequestMapping("/addNewsBulletinTable")
	@ResponseBody
	public String addNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean){
		int num=cnmNewsBulletinService.getaddNewsBulletinTable(cnmNewsBulletinBean);
		if (num>0){
			return "添加成功";
		}
		return "添加失败";
	}


	@RequestMapping("/updateNewsBulletinTable")
	@ResponseBody
	public String updateNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean){

		int num = cnmNewsBulletinService.getupdateNewsBulletinTable(cnmNewsBulletinBean);
		if (num>0){
			return "修改成功";
		}
		return "修改失败";
	}

	@RequestMapping("/updateNewsBulletinTableState")
	@ResponseBody
	public String updateNewsBulletinTableState(HttpServletRequest request){

		String stateName=request.getParameter("stateName");
		String NewsBulletinId=request.getParameter("NewsBulletinId");

		int stateID=cnmNewsBulletinService.getfindNewsBulletinState(stateName);

		CnmNewsBulletinBean cnmNewsBulletinBean=new CnmNewsBulletinBean();
				cnmNewsBulletinBean.setNewsBulletinId(Integer.valueOf(NewsBulletinId));
				cnmNewsBulletinBean.setStateId(stateID);
		int num = cnmNewsBulletinService.getupdateNewsBulletinTable(cnmNewsBulletinBean);
		if (num>0){
			return "修改成功1";
		}
		return "修改失败1";
	}


}
