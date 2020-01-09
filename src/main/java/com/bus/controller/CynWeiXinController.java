package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.CynWeiXinService;
import com.bus.until.Dijkstra;
import net.sf.json.JSONArray;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

	private int count;
	//用于存储路线
	private String routeName;
	//用于存储之前和之后的站点数
	private int leftCount;
	private int rightCount;
	private List<String> leftStations=new ArrayList<>();
	private List<String> RightStations=new ArrayList<>();

	/**
	 * 用户登录
	 */
	@RequestMapping("userLogin")
	public Map<String, Object> userLogin(String account, String pass)
	{
		Map<String, Object> resMap = new HashMap<>();
		List<Object> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		String msg = "";
		CynUserBean cynUserBean = cynWeiXinService.findUserByAccount(account);
		if (null != cynUserBean)
		{
			if (pass.equals(cynUserBean.getPassword()))
			{
				msg = "登录成功";
				resMap.put("id", cynUserBean.getUserId());
				resMap.put("userName", cynUserBean.getUserName());
				resMap.put("password", cynUserBean.getPassword());
				resMap.put("userHead", cynUserBean.getUserHead());
				resMap.put("phone", cynUserBean.getPhone());
				resMap.put("money", cynUserBean.getMoney());
				resMap.put("stateName", cynUserBean.getStateName());
				resMap.put("sex", cynUserBean.getSex());
				resMap.put("home", cynUserBean.getHome());
				resMap.put("company", cynUserBean.getCompany());
				list.add(resMap);
				map.put("userInf", list);
				map.put("message", msg);

			} else
			{
				msg = "账号或密码有误，请重新输入！";
				map.put("message", msg);
			}
		} else
		{
			msg = "账号不存在";
			map.put("message", msg);
		}
		return map;
	}

	/**
	 * 用户登录by人脸识别
	 */
	@RequestMapping("userLoginByFace")
	public Map<String, Object> userLoginByFace(String faceToken)
	{
		Map<String, Object> resMap = new HashMap<>();
		List<Object> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		String msg = "";
		CynUserBean cynUserBean = cynWeiXinService.findUserByFaceToken(faceToken);
		if (null != cynUserBean)
		{
			msg = "登录成功";
			resMap.put("id", cynUserBean.getUserId());
			resMap.put("userHead", cynUserBean.getUserHead());
			resMap.put("userName", cynUserBean.getUserName());
			resMap.put("password", cynUserBean.getPassword());
			resMap.put("phone", cynUserBean.getPhone());
			resMap.put("money", cynUserBean.getMoney());
			resMap.put("sex", cynUserBean.getSex());
			resMap.put("stateName", cynUserBean.getStateName());
			resMap.put("home", cynUserBean.getHome());
			resMap.put("company", cynUserBean.getCompany());
			list.add(resMap);
			map.put("userInf", list);
			map.put("message", msg);
		} else
		{
			msg = "账号不存在";
			map.put("message", msg);
		}
		return map;
	}

	/**
	 * 用户注册
	 */
	@RequestMapping("addUser")
	public Map<String, Object> addUser(String account,String password,String telphone,String home,String company,String sex)
	{
		Map<String, Object> resMap = new HashMap<>();
		CynUserBean cynUserBean=new CynUserBean();
		cynUserBean.setUserName(account);
		cynUserBean.setPassword(password);
		cynUserBean.setPhone(telphone);
		cynUserBean.setMoney(0);
		cynUserBean.setSex(sex);
		cynUserBean.setHome(home);
		cynUserBean.setCompany(company);
		int res=cynWeiXinService.addUser(cynUserBean);
		if(res>0){
			resMap.put("message","注册成功");
			resMap.put("userId",cynUserBean.getUserId());
			return resMap;
		}
		resMap.put("message","注册失败");
		return resMap;
	}

	/**
	 * 用户人脸注册
	 */
	@RequestMapping("addUserFaceToken")
	public Map<String, Object> addUserFaceToken(String userId,String faceToken)
	{
		Map<String, Object> resMap = new HashMap<>();
		CynUserBean cynUserBean=new CynUserBean();
		cynUserBean.setUserId(Integer.valueOf(userId));
		cynUserBean.setFaceToken(faceToken);
		int res=cynWeiXinService.addUserFaceToken(cynUserBean);
		if(res>0){
			resMap.put("message","添加成功");
			return resMap;
		}
		resMap.put("message","添加失败");
		return resMap;
	}

	/**
	 * 添加用户反馈意见
	 */
	@RequestMapping("addAdvice")
	public Map<String, Object> addAdvice(String content,String name,String phone,String type,String img)
	{
		Map<String, Object> map=new HashMap<>();
		//1.添加到用户反馈表
		CynAdviceBean adviceBean=new CynAdviceBean();
		adviceBean.setAccount(name);
		adviceBean.setContent(content);
		adviceBean.setPhone(phone);
		adviceBean.setType(type);
		int id=cynWeiXinService.addAdvice(adviceBean);
		System.out.println("id="+adviceBean.getId());
		int res=1;
		if(!"".equals(img)){
			//2.添加到用户反馈的图片表中
			JSONArray json = JSONArray.fromObject(img);
			List<Object> list = (List<Object>)JSONArray.toCollection(json, Object.class);
			for (Object o : list)
			{
				adviceBean.setImagePath(o.toString());
				res=cynWeiXinService.addAdviceImage(adviceBean);
			}
		}
		if(res>0){
			map.put("message","发表成功");
		}else{
			map.put("message","发表失败");
		}
		return map;
	}

	/**
	 * 获取所有的城市
	 */
	@RequestMapping("getAllCity")
	public Map<String, Object> getAllCity()
	{
		Map<String, Object> map = new HashMap<String, Object>();

		List<CynCityBean> cityList = cynWeiXinService.findAllCitys();

		map.put("list", cityList);
		return map;
	}

	/**
	 * 通过当前市 获取所有站点和线路
	 */
	@RequestMapping("/getAllStationAndRoute")
	public Map<String, List<CynStationAndRouteOrderBean>> getAllStationAndRoute(String location)
	{
		Map<String, List<CynStationAndRouteOrderBean>> map = new HashMap<String, List<CynStationAndRouteOrderBean>>();
		List<CynStationAndRouteOrderBean> stationAndRouteList = cynWeiXinService.findAllStationAndRoute(location);
		if (stationAndRouteList.size() > 0)
		{
			map = getMap(stationAndRouteList);
		}
		return map;
	}

	/**
	 * 获取站点和线路的map集合
	 */
	public Map<String, List<CynStationAndRouteOrderBean>> getMap(List<CynStationAndRouteOrderBean> list)
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
	public Map<String, Object> getAllStation(String stationName)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("输入的值为：" + stationName);
		List<CynStationAndRouteBean> list = new ArrayList<>();
		if (stationName != null && stationName.length() > 0)
		{
			list = cynWeiXinService.findAllStation(stationName);
		} else
		{
			list = null;
		}
		map.put("list", list);

		return map;
	}

	/**
	 * 根据站点名 获取所需线路以及方向
	 */
	@RequestMapping("/getSelectRoute")
	public Map<String, List<CynRouteSelectBean>> getAllRoute(String stationName)
	{
		System.out.println("stationName=" + stationName);
		List<CynRouteSelectBean> list = cynWeiXinService.getAllRoute(stationName);
		Map<String, List<CynRouteSelectBean>> map = getRouteMap(list);
		return map;
	}

	/**
	 * 获取线路的map集合
	 */
	public Map<String, List<CynRouteSelectBean>> getRouteMap(List<CynRouteSelectBean> list)
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
	public Map<String, List<CynTestBean>> getTestData(String route)
	{
		List<CynTestBean> list = cynWeiXinService.testGetAllData(route);
		Map<String, List<CynTestBean>> map = getTestRouteMap(list);
		return map;
	}

	public Map<String, List<CynTestBean>> getTestRouteMap(List<CynTestBean> list)
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
	public Map<String, Object> convert()
	{

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
	public Map<String, String> getStationXYByStationName(String stationName)
	{
		Map<String, String> map = new HashMap<>();
		System.out.println("stationName=" + stationName);
		CynTestBean cynBean = cynWeiXinService.getXYByStationName(stationName);
		map.put("longitude", cynBean.getLon());
		map.put("latitude", cynBean.getLat());
		return map;
	}

	/**
	 * 获取所有标记点的数组
	 */
	@RequestMapping("/getMarkers")
	public Map<String, Object> getMarkers(String route)
	{
		Map<String, Object> map = new HashMap<>();
		List<Object> markers = new ArrayList<>();

		String iconPath = "logo.png";
		String id = "0";
		String width = "40";
		String height = "30";
		List<CynTestBean> list = cynWeiXinService.testGetAllData(route);
		for (CynTestBean cynTestBean : list)
		{
			Map<String, Object> mapSingle = new HashMap<>();
			System.out.println(cynTestBean);
			String title = cynTestBean.getStationName();
			mapSingle.put("title", cynTestBean.getStationName());
			//			mapSingle.put("iconPath",iconPath);
			//			mapSingle.put("id",id);
			//			mapSingle.put("width",width);
			//			mapSingle.put("height",height);
			String x = cynTestBean.getLat();
			String y = cynTestBean.getLon();
			mapSingle.put("latitude", cynTestBean.getLat());
			//			System.out.println("latitude="+cynTestBean.getLat());
			mapSingle.put("longitude", cynTestBean.getLon());
			//			System.out.println("longitude="+cynTestBean.getLon());
			markers.add(mapSingle);
			for (int i = 0; i < markers.size(); i++)
			{
				System.out.println(markers.get(i));
			}
		}
		map.put("markers", markers);
		return map;
	}

	/**
	 * 获取所有线路的路线
	 */
	@RequestMapping("/getPolyline")
	public Map<String, Object> getPolyline(String route)
	{
		Map<String, Object> map = new HashMap<>();

		List<Object> polyline = new ArrayList<>();
		Map<String, Object> polylineMap = new HashMap<>();

		String color = "#ff6600";
		String width = "3";
		String arrowLine = "true";
		String borderColor = "#000";
		String borderWidth = "5";

		List<Object> points = new ArrayList<>();

		List<CynTestBean> list = cynWeiXinService.testGetAllData(route);
		for (CynTestBean cynTestBean : list)
		{
			Map<String, Object> pointsMap = new HashMap<>();
			pointsMap.put("latitude", cynTestBean.getLat());
			pointsMap.put("longitude", cynTestBean.getLon());
			points.add(pointsMap);
		}
		polylineMap.put("points", points);
		polylineMap.put("color", color);
		polylineMap.put("width", width);
		polylineMap.put("arrowLine", arrowLine);
		polylineMap.put("borderColor", borderColor);
		polylineMap.put("borderWidth", borderWidth);

		polyline.add(polylineMap);

		map.put("polyline", polyline);
		return map;
	}


	/**
	 * 获取到行车方案，可能涉及换乘
	 */
	@RequestMapping("/getTestMethod")
	public Map<String, Object> getMethod(String cityName,String startStation, String endStation, String myLat, String myLon){
		List<String> stations=new ArrayList<>();
		String msg="";

		//获取到离我最近的站点
		CynTestBean cynTestBean=getNearStation(cityName,myLat,myLon);
		String nearStation=cynTestBean.getStationName();
		//起始站点和终点站点
		String startLat="";
		String startLon="";
		String endLat="";
		String endLon="";
		String leftRoute="";
		String rightRoute="";
		String changeStation="";
		String style="";
		boolean bol;

		//先判断哪个站是我的位置
		if(startStation.equals("我的位置")){
			startLat=cynTestBean.getLat();
			startLon=cynTestBean.getLon();

			//获取终点站的经纬度
			endLat=getStationLat(endStation,myLat);
			endLon=getStationLon(endStation,myLon);
			//1.判断是否可以直达
			bol=judgeNoStop(nearStation,endStation);
			if(bol){
				style="0";
				msg="可以直达";
				//获取路线
				String routeName=getRouteName();
				stations=getMyRoutes(routeName,nearStation,endStation);
			}else{
				style="1";
				msg="不可以直达";
				//考虑一次换乘，获取到所有站点名
				//style  0:有换乘标志   1:没有换乘标识
				List<String> list2=getStopMethod(nearStation,endStation,0);

				//获取换乘左右两边的线路和换乘站点
				for (int i = 0; i < list2.size(); i++)
				{
					if(list2.get(i).equals("换乘")){
						leftRoute=list2.get(i-1);
						rightRoute=list2.get(i+1);
						changeStation=list2.get(i-2);
					}
				}
				stations=getStopMethod(nearStation,endStation,1);

			}

		}else if(endStation.equals("我的位置")){
			endLat=cynTestBean.getLat();
			endLon=cynTestBean.getLon();
			//获取起始站点的经纬度
			startLat=getStationLat(startStation,myLat);
			startLon=getStationLon(startStation,myLon);
			//1.判断是否可以直达
			bol=judgeNoStop(startStation,nearStation);
			if(bol){
				style="0";
				//获取路线
				String routeName=getRouteName();
				stations=getMyRoutes(routeName,startStation,nearStation);
				msg="可以直达";
			}else{
				style="1";
				stations=getStopMethod(startStation,nearStation,1);
				//考虑一次换乘，获取到所有站点名
				//style  0:有换乘标志   1:没有换乘标识
				List<String> list2=getStopMethod(startStation,nearStation,0);

				//获取换乘左右两边的线路和换乘站点
				for (int i = 0; i < list2.size(); i++)
				{
					if(list2.get(i).equals("换乘")){
						leftRoute=list2.get(i-1);
						rightRoute=list2.get(i+1);
						changeStation=list2.get(i-2);
					}
				}
			}
		}
		System.out.println("我的位置-->最近站点 "+cynTestBean.getMeter()+" 米");


		//获取所有站点
		stations.add(0,"我的位置");
		int weight[][]=getWeight(stations,myLat,myLon);
		//获取长度
		int len=stations.size();

		Dijkstra dijkstra=new Dijkstra(len);
		//依次让各点当源点，并调用dijkstra函数
		for(int i=0;i<stations.size();i++)
		{
			dijkstra.dijkstra(weight, stations, i);
		}
		//获取最短路径长度
		int resultMeter=dijkstra.getMeter();

		Map<String, Object> map=new HashMap<>();

		//方式： 0:直达   1:需要换乘
		if(style.equals("0")){
			//将所有站点放入
			stations.remove(0);
			map.put("stations",stations);
			map.put("start",stations.get(0));
			map.put("end",stations.get(stations.size()-1));
			map.put("sum",stations.size());
			map.put("totalMeter",changeMill(resultMeter));
			map.put("routeName",getRouteName());

		}else{
			//获取之前的和之后的 开始站和终点站
			List<CynTestBean> route1=cynWeiXinService.testGetAllData(leftRoute);
			map.put("leftStart",route1.get(0).getStationName());
			map.put("leftEnd",route1.get(route1.size()-1).getStationName());

			List<CynTestBean> route2=cynWeiXinService.testGetAllData(rightRoute);
			map.put("rightStart",route2.get(0).getStationName());
			map.put("rightEnd",route2.get(route1.size()-1).getStationName());

			//将换乘站点放入
			map.put("changeStation",changeStation);
			//换乘站之前的路线和之后的路线
			map.put("leftRoute",leftRoute);
			map.put("rightRoute",rightRoute);
			//换乘站之前的途径站点数和之后的途径站点数
			map.put("leftSum",getLeftCount());
			map.put("rightSum",getRightCount());
			//换乘站之前的站点集合和之后的站点集合
			map.put("leftStations",getLeftStations());
			map.put("rightStations",getRightStations());
			//换乘站之前的总公里数和之后的总公里数
			List<String> rightStations=new ArrayList<>();
			rightStations.add(changeStation);
			rightStations.addAll(getRightStations());
			int weight2[][]=getWeight(rightStations,myLat,myLon);
			//获取长度
			int len2=rightStations.size();

			Dijkstra dijkstra2=new Dijkstra(len2);
			//依次让各点当源点，并调用dijkstra函数
			for(int i=0;i<rightStations.size();i++)
			{
				dijkstra2.dijkstra(weight2, rightStations, i);
			}

			int rightMeter=dijkstra2.getMeter();
			map.put("rightMeter",changeMill(rightMeter));

			List<String> leftStations=new ArrayList<>();
			leftStations.addAll(getLeftStations());
			leftStations.add(changeStation);
			int weight3[][]=getWeight(leftStations,myLat,myLon);
			//获取长度
			int len3=leftStations.size();
			Dijkstra dijkstra3=new Dijkstra(len3);
			//依次让各点当源点，并调用dijkstra函数
			for(int i=0;i<leftStations.size();i++)
			{
				dijkstra3.dijkstra(weight3, leftStations, i);
			}
			int leftMeter=dijkstra3.getMeter();
			map.put("leftMeter",changeMill(leftMeter));


		}
		map.put("type",style);
		//将我的位置到离我最近的站点的距离放入
		map.put("nearMeter", (cynTestBean.getMeter()).intValue());
		//将离我最近的站点放入
		map.put("nearStation",cynTestBean.getStationName());

		return map;
	}

	/**
	 * 米转公里
	 */
	public double changeMill(int meter){
		return Math.rint(meter/100)/10 ;
	}

	/**
	 * 获取weight[][],用于dijskstra的算法
	 */
	public int[][] getWeight(List<String> stations,String lat,String lon){
		int weight[][]=new int[stations.size()][stations.size()];
		for (int i = 0; i < stations.size(); i++)
		{
			for (int j = 0; j < stations.size(); j++)
			{
				if(i==j){
					weight[i][j]=0;
					continue;
				}
				if(i==0){
					if(j==1){
						//获取临近站点的经纬度
						GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(lat),Double.parseDouble(lon));
						CynTestBean cynBean = cynWeiXinService.getXYByStationName(stations.get(j));
						//获取最终站点的经纬度
						GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(cynBean.getLat()),Double.parseDouble(cynBean.getLon()));
						double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
						weight[i][j]=new Double(meter1).intValue();

					}else{
						weight[i][j]=-1;
					}
				}else if(i<stations.size()-1){
					if(j==i+1){
						CynTestBean cynBean1 = cynWeiXinService.getXYByStationName(stations.get(j-1));
						//获取临近站点的经纬度
						GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(cynBean1.getLat()),Double.parseDouble(cynBean1.getLon()));

						CynTestBean cynBean2 = cynWeiXinService.getXYByStationName(stations.get(j));
						//获取最终站点的经纬度
						GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(cynBean2.getLat()),Double.parseDouble(cynBean2.getLon()));
						//获取两者的距离
						double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
						weight[i][j]=new Double(meter1).intValue();

					}else {
						weight[i][j]=-1;
					}
				}else{
					weight[i][j]=-1;
				}
			}
		}
		return weight;
	}

	/**
	 * 获取到离我最近的站点
	 */
	public CynTestBean getNearStation(String cityName, String myLat, String myLon){

		CynTestBean cynTestBean=new CynTestBean();
		//1.获取到当前城市下的所有站点的经纬度
		List<CynStationAndRouteBean> stations=cynWeiXinService.getAllStationByCityName(cityName);
		double judge = 0;//用于做比较
		//遍历所有站点的经纬度，拿到离自己最近的站点
		for (CynStationAndRouteBean station : stations)
		{
			//获取临近站点的经纬度
			GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(myLat), Double.parseDouble(myLon));
			//获取最终站点的经纬度
			GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(station.getStationLat()),Double.parseDouble(station.getStationLon()));
			//获取两者的距离
			double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
			if (meter1 > judge)
			{
				judge = meter1;
				cynTestBean.setStationName(station.getStationName());
				cynTestBean.setLat(station.getStationLat());
				cynTestBean.setLon(station.getStationLon());
				cynTestBean.setMeter(meter1);

			}
		}
		return cynTestBean;
	}


	/**
	 * 换乘方案
	 */
	public List<String> getStopMethod(String startStation,String endStation,int style){
		//用于存储可以经过终点站的线路
		List<String> getMyRoute=new ArrayList<>();

		//通过起始站，获取到所有经过起始站的线路
		List<CynRouteSelectBean> startRoutes=cynWeiXinService.getAllRoute(startStation);

		//通过终点站，获取到所有经过终点站的线路
		List<CynRouteSelectBean> endRoutes=cynWeiXinService.getAllRoute(endStation);

		for (CynRouteSelectBean route : endRoutes)
		{
			//根据线路名，获取到它之下的所有站点
			List<CynTestBean> stationEndList =cynWeiXinService.getNearStation(route.getRouteName());
			//对获取到的站点进行遍历
			for (CynTestBean cynTestBean : stationEndList)
			{
				//只要不是当前自己站点，就判断是否可以一次换乘通过
				if(!cynTestBean.getStationName().equals(endStation)){
					//对经过起始站点的线路进行遍历
					for (CynRouteSelectBean startRoute : startRoutes)
					{
						//获取到该线路下的所有站点
						List<CynTestBean> stationStartList =cynWeiXinService.getNearStation(startRoute.getRouteName());
						for (CynTestBean testBean : stationStartList)
						{
							//只要不是当前自己站点，就判断是否可以一次换乘通过
							if(!testBean.getStationName().equals(startStation)){
								//如果有相同，则说明可以一次换乘通过
								if(testBean.getStationName().equals(cynTestBean.getStationName())){
//									System.out.println("找到换乘站点");
//									System.out.println("换乘的站在："+testBean.getStationName());
									
									//得到经过换乘的所有的路线
									//1.得到起始站到换乘站的线路
									getMyRoute=getMyRoutes(startRoute.getRouteName(),startStation,testBean.getStationName());

									//设置临时变量，为了存储之前的站点数据数据
									List<String> tempList=new ArrayList<>();
									tempList.addAll(getMyRoute);
									setLeftStations(tempList);
									setLeftCount(getMyRoute.size());

									//2.获取换乘站到终点站的线路
									List<String> list=getMyRoutes(route.getRouteName(),testBean.getStationName(),endStation);

									//设置临时变量，为了存储之后的站点数据数据
									List<String> tempList2=new ArrayList<>();
									tempList2.addAll(list);
									setRightStations(tempList2);
									setRightCount(list.size());
									if(style==0){
										getMyRoute.add(startRoute.getRouteName());
										getMyRoute.add("换乘");
										getMyRoute.add(route.getRouteName());
										list.remove(0);
										getMyRoute.addAll(list);	
									}else if(style==1){

										list.remove(0);
										getMyRoute.addAll(list);
									}
									return getMyRoute;
								}
							}
						}
					}
				}
			}
		}
		return getMyRoute;
	}
	/**
	 * 获取线路
	 */
	public List<String> getMyRoutes(String startRouteName,String startStation,String crossStation){
		List<String> myRoutes=new ArrayList<>();
		int startOrder=0;
		int endOrder=0;
		List<CynTestBean> list=cynWeiXinService.testGetAllData(startRouteName);
		for (CynTestBean bean : list)
		{
			if(bean.getStationName().equals(startStation)){
				startOrder=bean.getOrder();
			}
			if(bean.getStationName().equals(crossStation)){
				endOrder=bean.getOrder();
			}
		}
		if(startOrder>endOrder){
			for (int i = startOrder; i >= endOrder; i--)
			{
				myRoutes.add(list.get(i).getStationName());
			}
		}else{
			for (int i = startOrder; i <= endOrder; i++)
			{
				myRoutes.add(list.get(i).getStationName());
			}
		}
		return myRoutes;
	}

	/**
	 * 判断是否可以直达
	 */
	public boolean judgeNoStop(String startStation,String endStation){
		//通过终点站，获取到所有经过终点站的线路
		List<CynRouteSelectBean> routes=cynWeiXinService.getAllRoute(endStation);
		for (CynRouteSelectBean route : routes)
		{
			//根据线路名，获取到它之下的所有站点
			List<CynTestBean> stationList =cynWeiXinService.getNearStation(route.getRouteName());
			//判断开始站点是否有在线路下的所有站点中，如果有，则直达
			for (CynTestBean cynTestBean : stationList)
			{
				if(cynTestBean.getStationName().equals(startStation)){

					setRouteName(route.getRouteName());
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * 获取到最优方案
	 */
//	@RequestMapping("/getBestThink")
//	public Map<String, Object> getBestThink(String startStation, String endStation, String myLat, String myLon)
//	{
//		//1.获取起始站和终点的经纬度
//		String startLat = getStationLat(startStation, myLat);
//		String startLon = getStationLon(startStation, myLon);
//		String endLat = getStationLat(endStation, myLat);
//		String endLon = getStationLon(endStation, myLon);
//
//		GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(startLat), Double.parseDouble(startLon));
//		GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(endLat), Double.parseDouble(endLon));
//		double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
//
//		//2.获取离自己最近的站点
//		CynTestBean cynTestBean = getNearStation(startStation, endStation, myLat, myLon);
//
//		Map<String, Object> resMap = new HashMap<>();
//		resMap.put("startStation", startStation);
//		resMap.put("step", Math.round(cynTestBean.getMeter()));
//		resMap.put("nearStation", cynTestBean.getStationName());
//		resMap.put("routeName", cynTestBean.getRouteName());
//		resMap.put("start", cynTestBean.getStartStation());
//		resMap.put("end", cynTestBean.getEndStation());
//		resMap.put("count", cynTestBean.getCount());
//		resMap.put("cover", cynTestBean.getWays());
//		double mill = Math.rint(meter1 / 100) / 10;
//		resMap.put("mill", mill);
//		resMap.put("endStation", endStation);
//
//		List<Object> mapSearch = new ArrayList<>();
//		mapSearch.add(resMap);
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("mapSearch", mapSearch);
//
//		return map;
//	}

	/**
	 * 获取经度
	 */
	public String getStationLat(String stationName, String myLat)
	{
		if (stationName.equals("我的位置"))
		{
			return myLat;
		} else
		{
			List<CynStationAndRouteBean> startList = cynWeiXinService.findAllStation(stationName);
			return startList.get(0).getStationLat();
		}
	}

	/**
	 * 获取纬度
	 */
	public String getStationLon(String stationName, String myLon)
	{
		if (stationName.equals("我的位置"))
		{
			return myLon;
		} else
		{
			List<CynStationAndRouteBean> startList = cynWeiXinService.findAllStation(stationName);
			return startList.get(0).getStationLon();
		}
	}

//	/**
//	 * 获取离自己最近的站点
//	 */
//	public CynTestBean getNearStation(String startStation, String endStation, String myLat, String myLon)
//	{
//
//		List<String> routes = new ArrayList<>();
//		List<CynRouteSelectBean> list;
//		if (endStation.equals("我的位置"))
//		{
//			//查询首站所在的线路
//			list = cynWeiXinService.getAllRoute(startStation);
//		} else
//		{
//			//查询尾站所在的线路
//			list = cynWeiXinService.getAllRoute(endStation);
//		}
//		//将所有线路添加进来
//		for (CynRouteSelectBean cynRouteSelectBean : list)
//		{
//			routes.add(cynRouteSelectBean.getRouteName());
//		}
//		//用于存储最近的站点
//		CynTestBean cynBean = new CynTestBean();
//		//用于存储最进站点到终点的所有站点
//		List<Object> stations = new ArrayList<>();
//		double judge = 0;//用于做比较
//		//遍历所有线路，获取其所有站点的经纬度
//		for (String route : routes)
//		{
//			List<CynTestBean> stationList = cynWeiXinService.getNearStation(route);
//			for (int i = 0; i < stationList.size(); i++)
//			{
//				//获取临近站点的经纬度
//				GlobalCoordinates source = new GlobalCoordinates(Double.parseDouble(myLat), Double.parseDouble(myLon));
//				//获取最终站点的经纬度
//				GlobalCoordinates target = new GlobalCoordinates(Double.parseDouble(stationList.get(i).getLat()), Double.parseDouble(stationList.get(i).getLon()));
//				double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
//				if (meter1 > judge)
//				{
//					judge = meter1;
//					cynBean.setStationName(stationList.get(i).getStationName());
//					cynBean.setLat(stationList.get(i).getLat());
//					cynBean.setLon(stationList.get(i).getLon());
//					cynBean.setOrder(stationList.get(i).getOrder());
//					cynBean.setMeter(meter1);
//					cynBean.setRouteName(route);
//					cynBean.setStartStation(stationList.get(0).getStationName());
//					cynBean.setEndStation(stationList.get(stationList.size() - 1).getStationName());
//					String endSta = "";
//					if (startStation.equals("我的位置"))
//					{
//						endSta = endStation;
//					} else
//					{
//						endSta = startStation;
//					}
//					stations = getAllWayStations(stationList, stationList.get(i).getStationName(), endSta);
//					cynBean.setWays(stations);
//					cynBean.setCount(getCount());
//				}
//			}
//		}
//		return cynBean;
//	}

//	/**
//	 * 用于获取最进站点到终点站的途径的所有站点
//	 */
//	public List<Object> getAllWayStations(List<CynTestBean> stationList, String startName, String endName)
//	{
//
//		List<Object> all = new ArrayList<>();
//
//		int startOrder = 0;//获取临近站点的顺序
//		int endOrder = 0;//获取终点站的顺序
//		for (int i = 0; i < stationList.size(); i++)
//		{
//			if (startName.equals(stationList.get(i).getStationName()))
//			{
//				startOrder = stationList.get(i).getOrder();
//			}
//			if (endName.equals(stationList.get(i).getStationName()))
//			{
//				endOrder = stationList.get(i).getOrder();
//			}
//		}
//		Map<String, Object> resMap;
//		if (endOrder < startOrder)
//		{
//
//			for (int j = startOrder; j >= endOrder; j--)
//			{
//				resMap = new HashMap<>();
//				resMap.put("ways", stationList.get(j).getStationName());
//				all.add(resMap);
//			}
//			setCount(startOrder - endOrder + 1);
//		} else
//		{
//			for (int j = startOrder; j <= endOrder; j++)
//			{
//				resMap = new HashMap<>();
//				resMap.put("ways", stationList.get(j).getStationName());
//				all.add(resMap);
//			}
//			setCount(endOrder - startOrder + 1);
//		}
//		return all;
//	}

	//计算坐标的距离
	public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid)
	{
		//创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
		GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
		return geoCurve.getEllipsoidalDistance();
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public int getLeftCount()
	{
		return leftCount;
	}

	public void setLeftCount(int leftCount)
	{
		this.leftCount = leftCount;
	}

	public int getRightCount()
	{
		return rightCount;
	}

	public void setRightCount(int rightCount)
	{
		this.rightCount = rightCount;
	}

	public List<String> getLeftStations()
	{
		return leftStations;
	}

	public void setLeftStations(List<String> leftStations)
	{
		this.leftStations = leftStations;
	}

	public List<String> getRightStations()
	{
		return RightStations;
	}

	public void setRightStations(List<String> rightStations)
	{
		RightStations = rightStations;
	}
}
