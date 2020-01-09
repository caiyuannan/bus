package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.CnmCooperativeService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
public class CnmController
{
	@Resource
	CnmCooperativeService cnmCooperativeService;
	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();

	@RequestMapping("/cooperative")
	public ModelAndView treeUrl(){

		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("roleSelect",cnmCooperativeService.getCooperativeType());
		modelAndView.setViewName("backjsp/cooperative");
		return modelAndView;
	}


	@RequestMapping("/showCooperativeTable")
	@ResponseBody
	public CnmTablePageInf getUserTable(HttpServletRequest request, String page, String limit){

		String cooperativeName=request.getParameter("uername");
		String cooperativeAreaa=request.getParameter("cooperativeAreaa");
		String cooperativeType=request.getParameter("roleSelect");

		System.out.println(cooperativeName+cooperativeAreaa+cooperativeType+"------------");
		cnmTablePageInf.setCode(0);
		cnmTablePageInf.setMsg("");
		cnmTablePageInf.setData(cnmCooperativeService.gettable(cooperativeName,cooperativeAreaa,cooperativeType,Integer.valueOf(page),Integer.valueOf(limit)));
		cnmTablePageInf.setCount(cnmCooperativeService.getTableCount());

		List<CnmCooperativeBean> tableList=cnmCooperativeService.getCooperativeType();

		return cnmTablePageInf;


	}


	@RequestMapping("/deleteCooperativeTable")
	@ResponseBody
	public String deleteCooperativeTable(CnmCooperativeBean cnmCooperativeBean){
		List<CnmAdvertisementBean> CnmAdvertisementBeanList=cnmCooperativeService.getfindCooperativeTypeInf(cnmCooperativeBean);
		System.out.println(CnmAdvertisementBeanList.size()+"-------------------------");
		if (CnmAdvertisementBeanList.size()>0){
			return "广告位正在使用中，不可删除";
		}else
		{
			int num = cnmCooperativeService.getDeleteCooperativeTable(cnmCooperativeBean);
			if (num > 0)
			{
				return "删除成功";
			}else{
			return "删除失败";}
		}
	}

	@RequestMapping("/addCooperativeTable")
	@ResponseBody
	public String addCooperativeTable(CnmCooperativeBean cnmCooperativeBean){
		int num=cnmCooperativeService.getaddCooperativeTable(cnmCooperativeBean);
		if (num>0){
			return "添加成功";
		}
		return "添加失败";
	}


	@RequestMapping("/updateCooperativeTable")
	@ResponseBody
	public String updateCooperativeTable(CnmCooperativeBean cnmCooperativeBean){

		int num = cnmCooperativeService.getupdateCooperativeTable(cnmCooperativeBean);
		if (num>0){
			return "修改成功";
		}
		return "修改失败";
	}

	@RequestMapping("/updateCooperativeTableState")
	@ResponseBody
	public String updateCooperativeTableState(HttpServletRequest request){

		String stateName=request.getParameter("stateName");
		String cooperativeId=request.getParameter("cooperativeId");

		int stateID=cnmCooperativeService.getfindCooperativeState(stateName);

		CnmCooperativeBean cnmCooperativeBean=new CnmCooperativeBean();
				cnmCooperativeBean.setCooperativeId(Integer.valueOf(cooperativeId));
				cnmCooperativeBean.setStateId(stateID);
		int num = cnmCooperativeService.getUpdateCooperativestate(cnmCooperativeBean);
		if (num>0){
			return "修改成功1";
		}
		return "修改失败1";
	}


}
