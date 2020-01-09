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

/**
 * 查询的（临时）
 */

@Controller
public class CnmInquire
{
	@Resource
	private XhaRouteConfigurationMapper routeMapper;
	@Resource
	CnmAdvertisementService cnmAdvertisementService;
	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();
	@Resource
	CnmNewsBulletinService cnmNewsBulletinService;
	@Resource
	private XhaRouteConfigurationService routeService;
//	@RequestMapping("/inquire")
//	public ModelAndView treeUrl(){
//
//		ModelAndView modelAndView=new ModelAndView();
//		//		路线查询
//		List<XhaRouteOrderBean> routeList = routeMapper.queryAllRoute();
//		for (XhaRouteOrderBean routeOrder : routeList) {
//			System.out.println(routeOrder.getRouteName()+"__---");
//		}
//		modelAndView.addObject("routeLists", routeList);//新闻栏
//
//		List<XhaStationBean> stationList = routeService.queryAllStation();//查询所有站点的信息
//		modelAndView.addObject("stationList", stationList);//新闻栏
//		modelAndView.setViewName("front/inquire");
//
//
//
//
//		return modelAndView;
//	}

	//	cnm通过站点名查询线路
	@RequestMapping("/findRouteStationName")
	@ResponseBody
	public List<XhaRouteOrderBean> queryStation(String stationName){
//		ModelAndView modelAndView2=new ModelAndView();
		List<XhaRouteOrderBean> lis=new ArrayList();


		XhaStationBean xhaStationBean=routeService.queryStation(stationName);
		if (null !=xhaStationBean)
		{
//			modelAndView2.addObject("StationLat", xhaStationBean.getStationLat());//开始站点x,y
//			modelAndView2.addObject("StationLon", xhaStationBean.getStationLon());//开始站点x,y

			String str =xhaStationBean.getRoutes();
			String[] strArr = str.split("\\，");
			for (int i = 0; i < strArr.length; ++i){
//				System.out.println(strArr[i]+"a______");
				XhaRouteOrderBean xhaRouteOrderBean=new XhaRouteOrderBean();
				xhaRouteOrderBean.setRouteName(strArr[i]);
				lis.add(xhaRouteOrderBean);
			}
		}
//		List<XhaStationBean> list = routeService.queryStationInfo(routeName);
//		modelAndView2.addObject("routesNamelis", lis);//显示路线下拉框
//		modelAndView2.setViewName("front/findRoute");
		return lis;
	}

	//	cnm通过站点名查询站点信息x,y
	@RequestMapping("/findRouteStationName2")
	@ResponseBody
	public XhaStationBean queryStation2(String stationName){
//		ModelAndView modelAndView2=new ModelAndView();
//		List<XhaRouteOrderBean> lis=new ArrayList();
//		XhaRouteOrderBean xhaRouteOrderBean=new XhaRouteOrderBean();
		XhaStationBean xhaStationBean=routeService.queryStation(stationName);
//		if (null !=xhaStationBean)
//		{
//			modelAndView2.addObject("StationLat", xhaStationBean.getStationLat());//开始站点x,y
//			modelAndView2.addObject("StationLon", xhaStationBean.getStationLon());//开始站点x,y
//
////			String str =xhaStationBean.getRoutes();
////			String[] strArr = str.split("\\，");
////			for (int i = 0; i < strArr.length; ++i){
////				System.out.println(strArr[i]+"a______");
////				xhaRouteOrderBean.setRouteName(strArr[i]);
////				lis.add(xhaRouteOrderBean);
//			}
//		}
		//		List<XhaStationBean> list = routeService.queryStationInfo(routeName);
//		modelAndView2.addObject("routesNamelis", lis);//显示路线下拉框
//		modelAndView2.setViewName("front/findRoute");
		System.out.println(xhaStationBean.getStationLat()+"a______");
		return xhaStationBean;
	}

}
