package com.bus.controller;

import com.bus.javabean.TableBean;
import com.bus.javabean.XhaPageBean;
import com.bus.javabean.XhaProvinceBean;
import com.bus.javabean.XhaStationBean;
import com.bus.service.XhaStationConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 城市站点控制器
 * by 谢海安
 */
@RequestMapping("station")
@Controller
public class XhaStationConfigurationController {
	@Resource
	private XhaStationConfigurationService stationService;

	/**城市站点配置界面*/
	@RequestMapping("stationView")
	@ResponseBody
	public ModelAndView queryProvinceCity(){
		List<XhaProvinceBean> list = stationService.queryProvinceCity();
		ModelAndView mv = new ModelAndView("backjsp/stationConfiguration");
		mv.addObject("menu",list);
		return mv;
	}

	/**站点配置表*/
	@RequestMapping("stationTable")
	@ResponseBody
	public ModelAndView stationTable(String cityName){
		ModelAndView mv = new ModelAndView("backjsp/stationTable");
		mv.addObject("cityName",cityName);
		return mv;
	}

	/**站点查询*/
	@RequestMapping("queryStation")
	@ResponseBody
	public TableBean queryStation(String cityName, String stationName, String page, String limit){
		XhaPageBean pb = new XhaPageBean(Integer.parseInt(page),Integer.parseInt(limit),cityName,stationName);
		return stationService.queryStationByProvinceName(pb);
	}

	/**删除站点*/
	@RequestMapping("deleteStation")
	@ResponseBody
	public String deleteStation(String stationId,String cityName){
		return stationService.deleteStation(Integer.parseInt(stationId),cityName);
	}

	/**添加站点界面*/
	@ResponseBody
	@RequestMapping("addStationView")
	public ModelAndView addStationView(String cityName){
		ModelAndView mv = new ModelAndView("backjsp/addStation");
		mv.addObject("cityName",cityName);
		return mv;
	}

	/**添加站点*/
	@RequestMapping("addStation")
	@ResponseBody
	public String addStation(String cityName,String stationName,String stationLon,String stationLat){
		XhaStationBean stationBean = new XhaStationBean(cityName,stationName,stationLon,stationLat);
		return stationService.addStataion(stationBean);
	}

	/**站点在百度地图上查看*/
	@ResponseBody
	@RequestMapping("queryBaiduMap")
	public ModelAndView queryBaiduMap(String stationId){
		XhaStationBean stationBean = stationService.queryStationById(Integer.parseInt(stationId));
		ModelAndView mv = new ModelAndView("/backjsp/stationMap");
		mv.addObject("station", stationBean);
		return mv;
	}
}
