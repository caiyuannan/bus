package com.bus.controller;

import com.bus.javabean.CnmAdvertisementBean;
import com.bus.javabean.CnmNewsBulletinBean;
import com.bus.javabean.CnmTablePageInf;
import com.bus.service.CnmAdvertisementService;
import com.bus.service.CnmNewsBulletinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 应该无用
 */

@Controller
public class CnmFrontPage
{
	@Resource
	CnmAdvertisementService cnmAdvertisementService;
	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();
	@Resource
	CnmNewsBulletinService cnmNewsBulletinService;

	@RequestMapping("/frontPage")
	public ModelAndView treeUrl(){

		ModelAndView modelAndView=new ModelAndView();
		List<CnmAdvertisementBean> tableList=cnmAdvertisementService.getfindAdvertisement(null);
		int x=1+(int)(Math.random()*(tableList.size()));
		if(x<=1){
			x=x+1;
		}
		List nList=tableList.subList(x-1, x);
		modelAndView.addObject("tableLists", tableList);//轮
		modelAndView.addObject("tableListss", nList);//飘

		List<CnmNewsBulletinBean> NewsBulletinList=cnmNewsBulletinService.getfindNewsBulletinInf();
		modelAndView.addObject("NewsBulletinLists", NewsBulletinList);//新闻栏
		modelAndView.setViewName("front/frontPage");
//		System.out.println(tableList.size());
//		System.out.println(nList.size()+"111");



		return modelAndView;
	}

//	@RequestMapping("/showAdvertisementTable2")
//	@ResponseBody
//	public CnmTablePageInf getUserTable(HttpServletRequest request, String page, String limit){
//
//
//		List<CnmAdvertisementBean> tableList=cnmAdvertisementService.getfindAdvertisement(null);
//		System.out.println(tableList.size());
//		List nList=tableList.subList(0, 2);
//		request.setAttribute("tableLists", nList);
//		System.out.println(nList.size()+"111");
//		return cnmTablePageInf;
//
//
//	}

}
