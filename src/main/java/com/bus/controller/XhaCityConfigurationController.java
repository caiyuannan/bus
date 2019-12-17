/*
package com.bus.controller;
import com.bus.javabean.*;
import com.bus.service.XhaCityConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * 城市配置控制器
 * by谢海安
 *//*

@Controller
@RequestMapping("city")
public class XhaCityConfigurationController {
	@Resource
	private XhaCityConfigurationService cityConfigurationService;

	*/
/**
	 * 返回界面层
	 * @param path
	 * @return
	 *//*

	@RequestMapping("{url}")
	public String view(@PathVariable(value = "url") String path){
		return "backjsp/"+path;
	}

	*/
/**查询城市配置*//*

	@RequestMapping("findCity")
	@ResponseBody
	public TableBean findCity(String page,String limit){
		List<XhaProvinceBean> provinceBeans = cityConfigurationService.findCity(null,null,0,0);
		System.out.println("省份集合长度:"+provinceBeans.size());
		List<XhaCityConfigurationBean> list = new ArrayList<XhaCityConfigurationBean>();
		for (XhaProvinceBean provinceBean : provinceBeans) {
			List<XhaStationBean> stations = cityConfigurationService.findStation(provinceBean.getCityBean().getCityId());
			System.out.println("站点集合："+stations.size());
			List<XhaRouteBean> routes = cityConfigurationService.findRoute(provinceBean.getCityBean().getCityId());
			System.out.println("路线集合:"+routes.size());
			XhaCityConfigurationBean ccb = new XhaCityConfigurationBean(provinceBean.getProvinceId(),provinceBean.getProvinceName(),provinceBean.getCityBean().getCityName(),stations.size(),routes.size());
			list.add(ccb);
		}
		System.out.println("======================");
		System.out.println("总集合:"+list.size());
		TableBean tableBean = new TableBean("",list.size(),0,list);
		return tableBean;
	}
}
*/
