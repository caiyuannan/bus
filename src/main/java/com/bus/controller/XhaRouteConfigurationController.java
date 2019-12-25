package com.bus.controller;

import com.bus.dao.XhaRouteConfigurationMapper;
import com.bus.javabean.*;
import com.bus.service.XhaRouteConfigurationService;
import com.bus.service.XhaStationConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 城市路线控制器
 * by 谢海安
 */
@RequestMapping("route")
@Controller
public class XhaRouteConfigurationController {
	@Resource
	private XhaStationConfigurationService stationService;
	@Resource
	private XhaRouteConfigurationService routeService;

	/**城市路线配置管理界面*/
	@RequestMapping("routeView")
	@ResponseBody
	public ModelAndView queryProvinceCity(){
		List<XhaProvinceBean> list = stationService.queryProvinceCity();
		ModelAndView mv = new ModelAndView("backjsp/routeConfiguration");
		mv.addObject("menu",list);
		return mv;
	}

	/**路线表*/
	@RequestMapping("routeTable")
	@ResponseBody
	public ModelAndView routeTable(String cityName){
		ModelAndView mv = new ModelAndView("backjsp/routeTable");
		mv.addObject("cityName",cityName);
		List<XhaStationBean> list = routeService.queryAllStation();
		mv.addObject("stationList",list);
		return mv;
	}

	/**路线查询*/
	@RequestMapping("queryRoute")
	@ResponseBody
	public TableBean queryRoute(String cityName,String page,String limit){
//		XhaPageBean pb = new XhaPageBean(Integer.parseInt(page),Integer.parseInt(limit),cityName,stationName);
		return routeService.queryRoute();
	}

	/**根据线路名查询站点*/
	@ResponseBody
	@RequestMapping("queryStationByRouteName")
	public TableBean queryStationByRouteName(String routeName,String isReverse){
		List<XhaRouteOrderBean> list = routeService.queryStationByRouteName(routeName,isReverse);
		return new TableBean("",list.size(),0,list);
	}

	/**从路线中删除站点*/
	@ResponseBody
	@RequestMapping("deleteStation")
	public String deleteStation(String routeName,String stationName,String routeOrderNumber){
		XhaRouteOrderBean routeOrder = new XhaRouteOrderBean(0,routeName,stationName,Integer.parseInt(routeOrderNumber),0);
		return routeService.deleteStation(routeOrder);
	}

	/**再百度地图中查看站点*/
	@ResponseBody
	@RequestMapping("stationMapView")
	public ModelAndView stationMapView(String cityName,String routeName){
		List<XhaStationBean> list = routeService.queryStationInfo(routeName);
		ModelAndView mv = new ModelAndView("/backjsp/stationMapView");
		mv.addObject("routeName", routeName);
		mv.addObject("list", routeService.queryStationInfo(routeName));
		return mv;
	}
	/**再百度地图中查看站点Ajax*/
	@ResponseBody
	@RequestMapping("stationMapViewAjax")
	public List<XhaStationBean> stationMapViewAjax(String cityName,String routeName){
		return routeService.queryStationInfo(routeName);
	}
}
