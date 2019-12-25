package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.CynWeiXinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 微信小程序：后台控制层
 */
@RestController
@RequestMapping("/wechat")
public class CynWeiXinController
{
	@Resource
	private CynWeiXinService cynWeiXinService;

	/**
	 * 获取所有的城市
	 */
	@RequestMapping("getAllCity")
	public  Map<String,Object> getAllCity (){
		Map<String, Object> map = new HashMap<String, Object>();

		List<CynCityBean> cityList=cynWeiXinService.findAllCitys();

		map.put("list",cityList);
		return map;
	}

	/**
	 * 通过当前市 获取所有站点和线路
	 */
	@RequestMapping("/getAllStationAndRoute")
	public Map<String,List<CynStationAndRouteOrderBean>> getAllStationAndRoute(String location){
		Map<String, List<CynStationAndRouteOrderBean>> map = new HashMap<String, List<CynStationAndRouteOrderBean>>();
		List <CynStationAndRouteOrderBean> stationAndRouteList=cynWeiXinService.findAllStationAndRoute(location);
		if(stationAndRouteList.size()>0)
		{
			map=getMap(stationAndRouteList);
		}
		return map;
	}
	/**
	 * 获取站点和线路的map集合
	 */
	public Map<String, List<CynStationAndRouteOrderBean>> getMap(List <CynStationAndRouteOrderBean> list)
	{
		Map<String, List<CynStationAndRouteOrderBean>> map = new HashMap<>();

		for (int i = 0; i < list.size(); i++)
		{
			CynStationAndRouteOrderBean bean = list.get(i);
			if (map.containsKey(bean.getStationName()))
			{
				List<CynStationAndRouteOrderBean> list1 = map.get(bean.getStationName());
				list1.add(bean);
			} else
			{
				List<CynStationAndRouteOrderBean> list2 = new ArrayList<>();
				list2.add(bean);
				map.put(bean.getStationName(), list2);
			}
		}
		return map;
	}

	/**
	 * 获取当前所有的站点
	 */
	@RequestMapping("/getAllStation")
	public Map<String,Object> getAllStation(String stationName){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("输入的值为："+stationName);
		List<CynStationAndRouteBean> list=new ArrayList<>();
		if(stationName!=null && stationName.length()>0){
			list=cynWeiXinService.findAllStation(stationName);
		}else{
			list=null;
		}
		map.put("list",list);

		return map;
	}

	/**
	 * 根据站点名 获取所需线路以及方向
	 */
	@RequestMapping("/getSelectRoute")
	public Map<String, List<CynRouteSelectBean>> getAllRoute(String stationName){
		System.out.println("stationName="+stationName);
		List<CynRouteSelectBean> list=cynWeiXinService.getAllRoute(stationName);
		Map<String, List<CynRouteSelectBean>> map=getRouteMap(list);
		return map;
	}
	/**
	 * 获取线路的map集合
	 */
	public Map<String, List<CynRouteSelectBean>> getRouteMap(List <CynRouteSelectBean> list)
	{
		Map<String, List<CynRouteSelectBean>> map = new HashMap<>();

		for (int i = 0; i < list.size(); i++)
		{
			CynRouteSelectBean bean = list.get(i);
			if (map.containsKey(bean.getRouteName()))
			{
				List<CynRouteSelectBean> list1 = map.get(bean.getRouteName());
				list1.add(bean);
			} else
			{
				List<CynRouteSelectBean> list2 = new ArrayList<>();
				list2.add(bean);
				map.put(bean.getRouteName(), list2);
			}
		}
		return map;
	}

	/**
	 * 获取所有的数据
	 */
	@RequestMapping("/getRoute")
	public Map<String,List<CynTestBean>> getTestData(String route){
		List<CynTestBean> list=cynWeiXinService.testGetAllData(route);
		Map<String, List<CynTestBean>> map=getTestRouteMap(list);
		return map;
	}
	public Map<String, List<CynTestBean>> getTestRouteMap(List <CynTestBean> list)
	{
		Map<String, List<CynTestBean>> map = new HashMap<>();

		for (int i = 0; i < list.size(); i++)
		{
			CynTestBean bean = list.get(i);
			if (map.containsKey(bean.getRouteName()))
			{
				List<CynTestBean> list1 = map.get(bean.getRouteName());
				list1.add(bean);
			} else
			{
				List<CynTestBean> list2 = new ArrayList<>();
				list2.add(bean);
				map.put(bean.getRouteName(), list2);
			}
		}
		return map;
	}
//	@RequestMapping("/getTestData")
	public Map<String,Object> convert(){

//		List<CynTestBean> list=cynWeiXinService.testGetAllData();
//		Map<String, List<CynTestBean>> map=getTestRouteMap(list);
//
//
//		List<Map<String,String>> entrance = new ArrayList<Map<String,String>>();
//
//		Map<String, String> nameMap = new HashMap<String, String>();
//		nameMap.put("name", "");
//		nameMap.put("direction", "");
//		nameMap.put("detail", "");
//
//		entrance.add(nameMap);
//
//		Map<String,Object> resMap=new HashMap<>();
//		List<Object> data1=new ArrayList<Object>();
//
//		for (Map.Entry<String, List<CynTestBean>> stringListEntry : map.entrySet())
//		{
//			Map<String, Object> data = new HashMap<String, Object>();
//			List<Object> station1 = new ArrayList<Object>();
//			data.put("line", 1);
//			for (CynTestBean cynTestBean : stringListEntry.getValue())
//			{
//				if(stringListEntry.getKey().equals(cynTestBean.getRouteName()))
//				{
//					Map<String, Object> stationDetail = new HashMap<String, Object>();
//					stationDetail.put("name", cynTestBean.getStationName());
//					stationDetail.put("longitude", cynTestBean.getLon());
//					stationDetail.put("latitude", cynTestBean.getLat());
//					stationDetail.put("entrance", entrance);
//
//					station1.add(stationDetail);
//					data.put("station", station1);
//				}
//			}
//
//			data1.add(data);
//		}
//
//		resMap.put("data",data1);
//
		return null;
	}

	/**
	 * 根据站点名字获取X轴Y轴
	 */
	@RequestMapping("/getStationXYByStationName")
	public Map<String,String> getStationXYByStationName(String stationName){
		Map<String,String> map=new HashMap<>();
		System.out.println("stationName="+stationName);
		CynTestBean cynBean=cynWeiXinService.getXYByStationName(stationName);
		map.put("longitude",cynBean.getLon());
		map.put("latitude",cynBean.getLat());
		return map;
	}

	/**
	 * 获取所有标记点的数组
	 */
	@RequestMapping("/getMarkers")
	public Map<String,Object> getMarkers(String route){
		Map<String,Object> map=new HashMap<>();
		List<Object> markers=new ArrayList<>();


		String iconPath="logo.png";
		String id="0";
		String width="40";
		String height="30";
		List<CynTestBean> list=cynWeiXinService.testGetAllData(route);
		for (CynTestBean cynTestBean : list)
		{
			Map<String,Object> mapSingle=new HashMap<>();
			System.out.println(cynTestBean);
			String title=cynTestBean.getStationName();
			mapSingle.put("title",cynTestBean.getStationName());
//			mapSingle.put("iconPath",iconPath);
//			mapSingle.put("id",id);
//			mapSingle.put("width",width);
//			mapSingle.put("height",height);
			String x=cynTestBean.getLat();
			String y=cynTestBean.getLon();
			mapSingle.put("latitude",cynTestBean.getLat());
//			System.out.println("latitude="+cynTestBean.getLat());
			mapSingle.put("longitude",cynTestBean.getLon());
//			System.out.println("longitude="+cynTestBean.getLon());
			markers.add(mapSingle);
			for (int i = 0; i < markers.size(); i++)
			{
				System.out.println(markers.get(i));
			}
		}


		map.put("markers",markers);
		return map;
	}
}
