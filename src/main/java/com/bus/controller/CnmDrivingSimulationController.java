package com.bus.controller;

import com.bus.dao.XhaRouteConfigurationMapper;
import com.bus.javabean.*;
import com.bus.service.CnmAdvertisementService;
import com.bus.service.CnmNewsBulletinService;
import com.bus.service.XhaRouteConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CnmDrivingSimulationController
{
//	@Resource
//	CnmAdvertisementService cnmAdvertisementService;
//	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();
//	@Resource
//	CnmNewsBulletinService cnmNewsBulletinService;
    @Resource
    private XhaRouteConfigurationMapper routeMapper;
	@Resource
	private XhaRouteConfigurationService routeService;

	@RequestMapping("/drivingSimulation")
	public ModelAndView treeUrl(){

		ModelAndView modelAndView=new ModelAndView();
//		路线查询
		List<XhaRouteOrderBean> routeList = routeMapper.queryAllRoute();
		for (XhaRouteOrderBean routeOrder : routeList) {
			System.out.println(routeOrder.getRouteName()+"__---");
		}
    	modelAndView.addObject("routeLists", routeList);//新闻栏
		modelAndView.setViewName("front/drivingSimulation");

		return modelAndView;
	}


	@RequestMapping("/StationByRouteName")
	@ResponseBody
	public List<XhaStationBean> getUserTable(String routeName){

		List<XhaStationBean> list = routeService.queryStationInfo(routeName);

		return list;


	}

}
