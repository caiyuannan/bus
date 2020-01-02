package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.DyfBusConfigurationService;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author 40651
 * Controller 层
 */
@Controller
public class DyfBusConfigurationController
{
	//bus管理service类
	@Resource
	private DyfBusConfigurationService dbs;
	//用户点击菜单选择所在的城市
	private String province;
	//用户城市附属id
	private String provinceName;
	//分页条数
	private final Integer pageSize = 5;
	//	车辆维修管理用来确认城市
	private String repairCity;
	//	车辆管理用来取别名
	private String repairCityName;
	//车辆排班管理
	private String shfitCity;
	//车辆排班管理取别名
	private String shfitCityName;
	//获取当前时间年月日
	SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
	//	获取当前的小时分钟
	SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
	private final Integer shfitPageSize = 8;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	private String mapStationProvin;
	private String mapStation;
	private List<DyfBusShfitBean> busShfitBeans1;   //始程已排班车辆
	private List<DyfBusShfitBean> busShfitBeans2;    //返程已排班车辆
	private String provinceHelp;
	private String provinceHelpName;
	private List<dyfDateBean> afterTime;
	//用于车辆管理的省份城市的树状图数据获取
	@RequestMapping("/selectAllProvince")
	public String selectProvinceAndcity(HttpServletResponse response, HttpServletRequest request)
	{
		List<DyfProvince> provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList", provincesList);
		return "backjsp/DyfBusWelecomeMain";
	}

	//用于车辆维修管理的省份城市的树状图数据获取
	@RequestMapping("/selectTree")
	public String selectProvinceAndCityManger(HttpServletResponse response, HttpServletRequest request)
	{
		List<DyfProvince> provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList", provincesList);
		return "backjsp/dyfBusrepair";
	}

	//用于车辆救援的省份城市的树状图数据获取
	@RequestMapping("/helpTree")
	public String helpTreeSelect(HttpServletResponse response, HttpServletRequest request)
	{
		List<DyfProvince> provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList", provincesList);
		return "backjsp/dyfHelpResoure";
	}

	//用于公交救援页面的跳转
	@RequestMapping("/helpUrlGoTo")
	public String helpUrlGoTo(String parm, HttpServletRequest request)
	{
		provinceHelp = parm;

		//这里暂时写死，后面从接口拿到数据再操作
		if (provinceHelp.equals("厦门市"))
		{
			provinceHelpName = "闽D";
		} else
		{
			provinceHelpName = "1";
		}
		Date date = new Date();
		String time1 = formatter.format(LocalDateTime.now());
		String str[] = time1.split(":");
		if (Integer.valueOf(str[1]) > 15 && Integer.valueOf(str[1]) <= 40)
		{
			time1 = str[0] + ":" + "30";
		} else if (Integer.valueOf(str[1]) > 40)
		{
			time1 = (Integer.valueOf(str[0])) + 1 + ":" + "00";
		}else {
			time1 = (Integer.valueOf(str[0]))+ ":" + "00";
		}
		afterTime = dbs.selectDateId(time1);
		return "backjsp/dyfHelpTable";
	}

	//用于车辆排班管理的省份城市的树状图数据获取
	@RequestMapping("/shfitProvince")
	public String shfitSelectAllProvince(HttpServletResponse response, HttpServletRequest request)
	{
		List<DyfProvince> provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList", provincesList);
		return "backjsp/dyfBusShfit";
	}

	//用于车辆鸟瞰图的省份城市的树状图数据获取
	@RequestMapping("/selectStationTree")
	public String selectMapStation(HttpServletResponse response, HttpServletRequest request)
	{
		List<DyfProvince> provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList", provincesList);
		return "backjsp/dyfBusMapStation";
	}

	//用于公交管理页面的跳转
	@RequestMapping("/discriminate")
	public String cityDiscriminate(String parm, HttpServletRequest request)
	{
		province = parm;

		//这里暂时写死，后面从接口拿到数据再操作
		if (province.equals("厦门市"))
		{
			provinceName = "闽D";
		} else
		{
			provinceName = "1";
		}
		request.setAttribute("selectAllDriver", dbs.selectDriver(province));
		request.setAttribute("selectAllstate", dbs.selectAllState(null));
		//查询所有站点，将其用于车辆增加是每天结尾车辆必须停放在该站点，方便后面排班
		request.setAttribute("selectAllStation", dbs.selectAllStation());
		//用户前台用户添加车辆信息时判断是否为该地区车辆
		request.getSession().setAttribute("province", provinceName);
		return "backjsp/DyfBusManger";
	}


	@RequestMapping("/DyfBusMangerServlet")
	@ResponseBody
	public TableBean selectAllBus(String carCard, String driverAge, String page, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//保养人名称
		String driverName = request.getParameter("driverName");
		//车辆状态
		String state = request.getParameter("driverSta");
		//车辆使用年限
		String sendYear = request.getParameter("sendYear");

		String str1 = page;
		//str1表示为当前页数，主要从前台数据返回，当前天无数据时，默认为第一页
		if (str1 == null)
		{
			str1 = "1";
		}
		String startPage = String.valueOf((Integer.valueOf(str1) - 1) * 5);
		//获得分页的车辆数据
		List<DyfBusBean> busBeans = dbs.selectBusManger(provinceName, driverName, carCard, driverAge, sendYear, state, Integer.valueOf(startPage), pageSize);
		TableBean tableBean = new TableBean();
		//获得数据库分页页数
		int count = dbs.selectCountBus(provinceName, driverName, carCard, driverAge, sendYear, state).intValue();
		tableBean.setCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		tableBean.setData(busBeans);
		return tableBean;
	}

	//url跳转路径，用来数据库等跳转问题
	@RequestMapping("/demo/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		return "backjsp/" + path;
	}

	//添加车辆操作
	@RequestMapping("/DyfBusAddBus")
	@ResponseBody
	public String addBus(HttpServletRequest request)
	{
		String msg = request.getParameter("msg");
		DyfBusBean dfb = new DyfBusBean();
		String sendMsg = null;
		if (msg != null && msg.length() > 0)
		{
			String str[] = msg.split("=");
			List<stateBean> stateList = dbs.selectAllState("在用");
			dfb.setBusLicense(str[0]);
			dfb.setBusDutyDriver(str[1]);
			dfb.setBusAge(str[3]);
			dfb.setBusMin(str[2]);
			dfb.setBusType(str[4]);
			dfb.setEndStation(str[5]);
			dfb.setBusState(stateList.get(0).getStateId().intValue());
			int num = dbs.insertBus(dfb);
			if (num > 0)
			{
				sendMsg = "success";
			} else
			{
				sendMsg = "fail";
			}
		} else
		{
			sendMsg = "fail";
		}
		System.out.println(sendMsg);
		return sendMsg;
	}

	//修改车辆状态操作
	@RequestMapping("/DyfBusUpdateState")
	@ResponseBody
	public String updateBusState(HttpServletRequest request)
	{
		String msg = request.getParameter("msg");
		if (null != msg && msg.length() > 0)
		{
			String str[] = msg.split("=");
			List<stateBean> stateList = dbs.selectAllState(str[1]);
			int stateNum = stateList.get(0).getStateId().intValue();
			BigDecimal bd = new BigDecimal(str[0]);
			int num = dbs.deleteBusState(stateNum, bd);
			if (num > 0)
			{
				return "success";
			}
		}
		return "fail";
	}

	//修改车辆信息操作
	@RequestMapping("/DyfBusUpdateInFor")
	@ResponseBody
	public String busUpdateInFor(HttpServletRequest request)
	{
		//获取到前段发送过来的所要更改的状态
		DyfBusBean dyfBusBean = (DyfBusBean) JSONObject.toBean(JSONObject.fromObject(request.getParameter("DyfBusBean")), DyfBusBean.class);
		String userState = request.getParameter("beforeBusState");

		if (userState != null && userState.length() > 0)
		{
			List<stateBean> list = dbs.selectAllState(userState);
			if(list.size()>0){
				dyfBusBean.setBusState(list.get(0).getStateId().intValue());
			}
		}
		if (null != userState && userState.equals("维修"))
		{
			Date date = new Date();
			String repairDate = sdf.format(date);
			String repairDateNow = sdf1.format(date);
			BigDecimal g = new BigDecimal("1");
			DyfBusRepairBean dyfBusRepairBean = new DyfBusRepairBean(new BigDecimal(String.valueOf(dyfBusBean.getBusId())), repairDate, repairDateNow, null, null, "9");
			dbs.insertAddRepairBus(dyfBusRepairBean);
		}
		int num = dbs.updateBusInforMation(dyfBusBean);
		if (num > 0)
		{
			return "success";
		}
		return "fail";
	}

	//用于车辆维修查看
	@RequestMapping("/repairBus")
	public String repairBus(String parm, HttpServletRequest request)
	{
		repairCity = parm;
		if (repairCity.equals("厦门市"))
		{
			repairCityName = "闽D";
		} else
		{
			repairCityName = "1";
		}


		return "backjsp/dyfBusRepairTable";
	}

	//用于车辆鸟盾图查看
	@RequestMapping("/aerPlaneView")
	public String aeroPlaneView(String parm, HttpServletRequest request)
	{
		mapStation = parm;
		if (mapStation.equals("厦门市"))
		{
			mapStationProvin = "闽D";
		} else
		{
			mapStationProvin = "1";
		}
		List<dyfAllRouteBean> allRouteBeans = dbs.selectAllRoute(mapStation);
		for (dyfAllRouteBean allRouteBean : allRouteBeans)
		{
			System.out.println(allRouteBean.toString());
		}
		request.setAttribute("allRouteBeans", allRouteBeans);
		return "backjsp/dyfAeroPlaneView";
	}

	//table的表格显示
	@RequestMapping("/ajaxRepairBusTable")
	@ResponseBody
	public TableBean ajaxRepairBusTable(HttpServletRequest request, String page, String license)
	{
		TableBean tableBean = new TableBean();
		String str1 = page;

		//str1表示为当前页数，主要从前台数据返回，当前天无数据时，默认为第一页
		if (str1 == null)
		{
			str1 = "1";
		}
		String startPage = String.valueOf((Integer.valueOf(str1) - 1) * 5);
		tableBean.setData(dbs.selectAllRepair(new BigDecimal(startPage), pageSize, repairCityName, license));
		int count = dbs.countSelectRepair(repairCityName, license).intValue();
		tableBean.setCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		return tableBean;
	}

	//车辆排班界面
	@RequestMapping("/busShfit")
	public String busRepair(String parm, HttpServletRequest request)
	{
		shfitCity = parm;
		if (shfitCity.equals("厦门市"))
		{
			shfitCityName = "闽D";
		} else
		{
			shfitCityName = "1";
		}
		List<DyfRouteBean> list = dbs.routeAllSelect(shfitCity);
		request.setAttribute("tbRoute", list);
		return "backjsp/dyfBusShfitTable";
		//车辆排班所有信息

	}

	//	车辆排班起始站发车数据表格显示
	@RequestMapping("/busShfitAllInforMation")
	@ResponseBody
	public TableBean busShfitAllInforMation(HttpServletRequest request, String page, String shfitBusLine, String d0)
	{
		TableBean tableBean = new TableBean();
		if (shfitBusLine == null)
		{
			shfitBusLine = "1路";
		}
		//		当前时间，每次第一次只传回当天时间
		if (d0 == null)
		{
			d0 = sdf.format(new Date());
		}
		List<DyfRouteOrder> routeOrders = dbs.selectSomeStation("1", shfitBusLine);
		if (null != routeOrders && routeOrders.size() > 0)
		{
			busShfitBeans1 = dbs.shfitSlectAllLine(String.valueOf(d0), shfitCity, shfitBusLine, routeOrders.get(0).getRouteStationName(), "1");
			tableBean.setData(busShfitBeans1);
		}
		tableBean.setCount(1);
		return tableBean;
	}

	//	车辆排班终点站发车数据表格显示
	@RequestMapping("/busShfitEnd")
	@ResponseBody
	public TableBean busShfitEnd(HttpServletRequest request, String shfitBusLine, String d0)
	{
		TableBean tableBean = new TableBean();

		if (shfitBusLine == null)
		{
			shfitBusLine = "1路";
		}
		//	当前时间，每次第一次只传回当天时间
		if (d0 == null)
		{
			d0 = sdf.format(new Date());
		}

		List<DyfRouteOrder> routeOrders = dbs.selectSomeStation("0", shfitBusLine);
		if (null != routeOrders && routeOrders.size() > 0)
		{
			busShfitBeans2 = dbs.shfitSlectAllLine(String.valueOf(d0), shfitCity, shfitBusLine, routeOrders.get(0).getRouteStationName(), "0");
			tableBean.setData(busShfitBeans2);
		}

		tableBean.setCount(1);
		return tableBean;
	}

	//查询所有可排班车辆列表
	@RequestMapping("/canRepaitBus")
	@ResponseBody
	public List<DyfBusBean> selectAllCanRepaitBus(HttpServletRequest request, String shfitBusLine, String d0, String routeOrder, String shfitBusStartStation, String dateBusId, String dateBusTime, String busLicense)
	{
		List<DyfBusBean> busBeanList = null;
		List<DyfRouteOrder> routeOrders = dbs.selectSomeStation(routeOrder, shfitBusLine);
		for (DyfRouteOrder order : routeOrders)
		{
			System.out.println(order.toString());
		}
		if (d0 == null)
		{
			d0 = sdf.format(new Date());
		}

		String str[] = null;
		String ots[] = null;
		if (dateBusTime != null && dateBusTime != "")
		{
			str = dateBusTime.split(":");
		}

		List<Integer> utilList = new ArrayList<>();
		if (routeOrders.size() > 0)
		{
			List<DyfBusShfitBean> list = dbs.selectAllCanRefait(String.valueOf(d0), shfitCity, shfitBusLine, routeOrders.get(0).getRouteStationName(), shfitBusStartStation);

			//			shfitBusStartStation 区分开始发车还是终点发车
			if (shfitBusStartStation.equals("1"))
			{
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).getShfitBusId() != null)
					{
						utilList.add(Integer.valueOf(list.get(i).getShfitBusId()));
					}

				}
			} else if (shfitBusStartStation.equals("0"))
			{
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).getShfitBusId() != null)
					{
						utilList.add(Integer.valueOf(list.get(i).getShfitBusId()));
					}

				}
			}

			String time = null;
			if (!dateBusTime.equals("6:00"))
			{
				time = dateBusTime;
			}

			//当shfitBusStartStation = 0 表示是从终点站发车
			if (shfitBusStartStation.equals("0"))
			{
				busBeanList = dbs.selectAllNoShfitBus(utilList, routeOrders.get(1).getRouteStationName(), time, routeOrders.get(0).getRouteStationName());
				List<DyfBusBean> afterBusShfit = dbs.selectStationId(String.valueOf(Integer.valueOf(dateBusId) - 1), d0, "1", shfitBusLine);
				for (int i = 0; i < afterBusShfit.size(); i++)
				{
					busBeanList.add(afterBusShfit.get(i));
				}
				Set<Integer> busList = new HashSet<>();
				//通过查询整个的排班列表获取到当前时刻两小时内的排班信息并塞入集合中以供后面去除排班列表中的数据
				for (int i = 0; i < busShfitBeans2.size(); i++)
				{
					if (busShfitBeans2.get(i).getShfitBusId() != null && busShfitBeans2.get(i).getShfitBusId().length() > 0)
					{
						if ((Integer.valueOf(busShfitBeans2.get(i).getDateBusId())) >= Integer.valueOf(dateBusId) - 4 && (Integer.valueOf(busShfitBeans2.get(i).getDateBusId())) <= Integer.valueOf(dateBusId) + 4)
						{
							busList.add(Integer.valueOf(busShfitBeans2.get(i).getShfitBusId()));
						}
					}
				}
				//				busShfitBeans 1  始发站
				for (int i = 0; i < busShfitBeans1.size(); i++)
				{
					if (busShfitBeans1.get(i).getShfitBusId() != null && busShfitBeans1.get(i).getShfitBusId().length() > 0)
					{
						if ((Integer.valueOf(busShfitBeans1.get(i).getDateBusId())) > (Integer.valueOf(dateBusId) - 1) && (Integer.valueOf(busShfitBeans1.get(i).getDateBusId())) < (Integer.valueOf(dateBusId) + 1))
						{
							busList.add(Integer.valueOf(busShfitBeans1.get(i).getShfitBusId()));
						}
					}

				}


				List<DyfBusShfitBean> set1 = new ArrayList<>();
				for (int i = 0; i < busShfitBeans1.size(); i++)
				{
					if (busShfitBeans1.get(i).getShfitBusId() != null)
					{
						set1.add(busShfitBeans1.get(i));
					}
				}
				Comparator<DyfBusShfitBean> comparator = (h1, h2) -> Integer.valueOf(h1.getDateBusId()).compareTo(Integer.valueOf(h2.getDateBusId()));
				set1.sort(comparator.reversed());

				set1 = set1.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusShfitBean::getShfitBusId))), ArrayList::new));


				List<DyfBusShfitBean> set2 = new ArrayList<>();
				for (DyfBusShfitBean dyfBusShfitBean : busShfitBeans2)
				{
					if (dyfBusShfitBean.getShfitBusId() != null)
					{
						set2.add(dyfBusShfitBean);
					}
				}
				set2.sort(comparator.reversed());
				set2 = set2.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusShfitBean::getShfitBusId))), ArrayList::new));
				for (int i = 0; i < set1.size(); i++)
				{
					for (int j = 0; j < set2.size(); j++)
					{
						if (set1.get(i).getShfitBusId().equals(set2.get(j).getShfitBusId()))
						{
							if (Integer.valueOf(set1.get(i).getDateBusId()) - Integer.valueOf(set2.get(j).getDateBusId()) < 2)
							{
								busList.add(Integer.valueOf(set1.get(i).getShfitBusId()));
							}
							if (Integer.valueOf(dateBusId) - Integer.valueOf(set2.get(j).getDateBusId()) < 2)
							{
								busList.add(Integer.valueOf(set1.get(i).getShfitBusId()));
							}
						}
					}
					if (Integer.valueOf(dateBusId) - Integer.valueOf(set1.get(i).getDateBusId()) < 2)
					{
						busList.add(Integer.valueOf(set1.get(i).getShfitBusId()));
					}
				}


				busBeanList = busBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusBean::getBusId))), ArrayList::new));

				//判断该集合内两小时内是否有排班
				if (busList.size() > 0)
				{

					for (int i = 0; i < busBeanList.size(); i++)
					{
						System.out.println(busBeanList.get(i).toString());
						for (Integer integer : busList)
						{

							if (busBeanList.get(i).getBusId().equals(integer))
							{
								busBeanList.remove(busBeanList.get(i));
							}
						}

					}

				}

				busBeanList = busBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusBean::getBusLicense))), ArrayList::new));
			} else
			{
				busBeanList = dbs.selectAllNoShfitBus(utilList, routeOrders.get(0).getRouteStationName(), time, routeOrders.get(1).getRouteStationName());
				List<DyfBusBean> afterBusShfit = dbs.selectStationId(String.valueOf(Integer.valueOf(dateBusId) - 1), d0, "0", shfitBusLine);
				for (int i = 0; i < afterBusShfit.size(); i++)
				{
					busBeanList.add(afterBusShfit.get(i));
				}
				Set<Integer> busList = new HashSet<>();
				for (int i = 0; i < busShfitBeans1.size(); i++)
				{
					if (busShfitBeans1.get(i).getShfitBusId() != null && busShfitBeans1.get(i).getShfitBusId().length() > 0)
					{
						if ((Integer.valueOf(busShfitBeans1.get(i).getDateBusId())) >= Integer.valueOf(dateBusId) - 4 && (Integer.valueOf(busShfitBeans1.get(i).getDateBusId())) <= Integer.valueOf(dateBusId) + 4)
						{
							busList.add(Integer.valueOf(busShfitBeans1.get(i).getShfitBusId()));
						}
					}

				}
				for (int i = 0; i < busShfitBeans2.size(); i++)
				{
					if (busShfitBeans2.get(i).getShfitBusId() != null && busShfitBeans2.get(i).getShfitBusId().length() > 0)
					{
						if ((Integer.valueOf(busShfitBeans2.get(i).getDateBusId())) > (Integer.valueOf(dateBusId) - 1) && (Integer.valueOf(busShfitBeans2.get(i).getDateBusId())) < (Integer.valueOf(dateBusId) + 1))
						{
							busList.add(Integer.valueOf(busShfitBeans2.get(i).getShfitBusId()));
						}

					}

				}
				List<DyfBusShfitBean> set1 = new ArrayList<>();
				for (int i = 0; i < busShfitBeans2.size(); i++)
				{
					if (busShfitBeans2.get(i).getShfitBusId() != null)
					{
						set1.add(busShfitBeans2.get(i));
					}
				}
				Comparator<DyfBusShfitBean> comparator = (h1, h2) -> Integer.valueOf(h1.getDateBusId()).compareTo(Integer.valueOf(h2.getDateBusId()));
				set1.sort(comparator.reversed());

				set1 = set1.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusShfitBean::getShfitBusId))), ArrayList::new));


				List<DyfBusShfitBean> set2 = new ArrayList<>();
				for (DyfBusShfitBean dyfBusShfitBean : busShfitBeans1)
				{
					if (dyfBusShfitBean.getShfitBusId() != null)
					{
						set2.add(dyfBusShfitBean);
					}
				}
				set2.sort(comparator.reversed());
				set2 = set2.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusShfitBean::getShfitBusId))), ArrayList::new));
				for (int i = 0; i < set1.size(); i++)
				{
					for (int j = 0; j < set2.size(); j++)
					{
						if (set1.get(i).getShfitBusId().equals(set2.get(j).getShfitBusId()))
						{
							if (Integer.valueOf(set1.get(i).getDateBusId()) - Integer.valueOf(set2.get(j).getDateBusId()) < 2)
							{
								busList.add(Integer.valueOf(set1.get(i).getShfitBusId()));
							}
							if (Integer.valueOf(dateBusId) - Integer.valueOf(set2.get(j).getDateBusId()) < 2)
							{
								busList.add(Integer.valueOf(set1.get(i).getShfitBusId()));
							}
						}
					}
					if (Integer.valueOf(dateBusId) - Integer.valueOf(set1.get(i).getDateBusId()) < 2)
					{
						busList.add(Integer.valueOf(set1.get(i).getShfitBusId()));
					}
				}

				//判断该集合内两小时内是否有排班
				busBeanList = busBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusBean::getBusId))), ArrayList::new));

				if (busList.size() > 0)
				{
					for (int i = 0; i < busBeanList.size(); i++)
					{

						for (Integer integer : busList)
						{
							if (busBeanList.get(i).getBusId().equals(integer))
							{
								busBeanList.remove(busBeanList.get(i));
								break;
							}
						}


					}
				}
				busBeanList = busBeanList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusBean::getBusLicense))), ArrayList::new));
			}

			//用来判断是否是替班的
			if (null != busLicense && busLicense.length() > 0)
			{
				for (DyfBusBean bean : busBeanList)
				{
					if (bean.getBusLicense().equals(busLicense))
					{
						busBeanList.remove(bean);
					}
				}
			}
		}
		return busBeanList;
	}

	@RequestMapping("/addShfitServlet")
	@ResponseBody
	public String addShfitBus(HttpServletRequest request, String dateBusId, String dateBusTime, String license, String shfitBusLine, String d0, String shfitBusStartStation, String oldBusLicense)
	{
		//当有传入表格上的车牌号是表示是进行换班
		if (oldBusLicense != null && oldBusLicense.length() > 0)
		{
			DyfBusBean dyfBusBean = dbs.selectBusLinsece(oldBusLicense);
			if (dyfBusBean != null)
			{
				int douMer = dbs.updateShfitBus(license, shfitBusLine, shfitBusStartStation, dateBusId, d0, String.valueOf(dyfBusBean.getBusId()));
				if (douMer > 0)
				{
					return "success1";
				}
			}
			//当没有传入表格上的车牌号是表示是进行排班
		} else
		{
			int num = dbs.addShfit(d0, dateBusTime, license, shfitBusLine, dateBusId, shfitBusStartStation);
			if (num > 0)
			{
				return "success";
			}
		}
		return "fail";
	}

	//	查询线路站点的经纬度
	@RequestMapping("/selectAllStationServlet")
	@ResponseBody
	public List<dyfStationBean> returnStationLonLat(HttpServletRequest request, String shfitBusLine)
	{
		List<dyfStationBean> stationBeans = dbs.testGetAllData(shfitBusLine, mapStation);
		stationBeans.sort(dyfStationBean.Comparators.AGE);

		return stationBeans;
	}

	//	查询车辆时间轴
	@RequestMapping("/selectTimer")
	@ResponseBody
	public List<DyfBusShfitBean> returnTimerShfit(HttpServletRequest request, String value, String lisecenId)
	{
		List<DyfBusShfitBean> busShfitBeans = null;

		if (null != value && null != lisecenId)
		{
			busShfitBeans = dbs.timerShfit(lisecenId, value);
		}
		return busShfitBeans;
	}

	//	车辆救援管理
	@RequestMapping("/helpAllTime")
	@ResponseBody
	public TableBean helpTable(HttpServletRequest request,String station,String lisenceCard)
	{
		TableBean tableBean = new TableBean();
		String date = sdf.format(new Date());
		List<DyfBusShfitBean> busShfitBeans = dbs.selectHelpBus(date, String.valueOf(afterTime.get(0).getDateId()));
		//倒序 将最新的车辆数据倒序排放
		Comparator<DyfBusShfitBean> comparator = (h1, h2) -> Integer.valueOf(h1.getThisDateId()).compareTo(Integer.valueOf(h2.getThisDateId()));
		busShfitBeans.sort(comparator.reversed());
		//去重 过滤更新最前的数据
		busShfitBeans = busShfitBeans.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DyfBusShfitBean::getBusLicense))), ArrayList::new));
		List<DyfBusShfitBean> dyfBusShfitBeanList = new ArrayList<>();
		List<Integer> busIdList = new ArrayList<>();
		for (int i = 0; i < busShfitBeans.size(); i++)
		{
			if (afterTime.get(0).getDateId() - Integer.valueOf(busShfitBeans.get(i).getThisDateId()) > 2)
			{
				dyfBusShfitBeanList.add(busShfitBeans.get(i));
				busIdList.add(Integer.valueOf(busShfitBeans.get(i).getShfitBusId()));
			}
		}
		List<DyfRouteOrder> list = dbs.selectAllStation();

		List<dyfHelpBean> dyfHelpBeanList = new ArrayList<>();
		for (int i = 0; i < dyfBusShfitBeanList.size(); i++)
		{
			dyfHelpBean dyfHelpBean = new dyfHelpBean();
			int num = 0;
			if (dyfBusShfitBeanList.get(i).getShfitBusLine().equals("1路"))
			{
				if (dyfBusShfitBeanList.get(i).getShfitThisStation().equals("0")){
					List<DyfBusShfitBean> beginShfitBean = dbs.timeNameLisence("1",dyfBusShfitBeanList.get(i).getShfitDate(),dyfBusShfitBeanList.get(i).getShfitBusId(),String.valueOf(afterTime.get(0).getDateId()));
					dyfHelpBean.setLiscense(dyfBusShfitBeanList.get(i).getBusLicense());
					dyfHelpBean.setStopStation(list.get(0).getRouteStationName());
					dyfHelpBean.setNewStation(list.get(0).getRouteStationName());
					dyfHelpBean.setJionTime(dyfBusShfitBeanList.get(i).getShfitStartTime());
					Integer nowTime = afterTime.get(0).getDateId() - Integer.valueOf(dyfBusShfitBeanList.get(i).getThisDateId());

					String nowTime1 =null;
					if (nowTime%2==0){
						nowTime1 = String.valueOf(nowTime/2)+"小时"+"00分";
					}else if (nowTime%2>0){
						nowTime1 = nowTime/2+"小时"+"30分";
					}
					dyfHelpBean.setNewTime(nowTime1);
					if (beginShfitBean.size()>0){
						dyfHelpBean.setBeginTime(beginShfitBean.get(0).getShfitStartTime());
					}
					dyfHelpBeanList.add(dyfHelpBean);

				}else if (dyfBusShfitBeanList.get(i).getShfitThisStation().equals("1")){
					List<DyfBusShfitBean> beginShfitBean = dbs.timeNameLisence("0",dyfBusShfitBeanList.get(i).getShfitDate(),dyfBusShfitBeanList.get(i).getShfitBusId(),String.valueOf(afterTime.get(0).getDateId()));
					dyfHelpBean.setLiscense(dyfBusShfitBeanList.get(i).getBusLicense());
					dyfHelpBean.setStopStation(list.get(1).getRouteStationName());
					dyfHelpBean.setNewStation(list.get(1).getRouteStationName());
					dyfHelpBean.setJionTime(dyfBusShfitBeanList.get(i).getShfitStartTime());
					Integer nowTime = afterTime.get(0).getDateId() - Integer.valueOf(dyfBusShfitBeanList.get(i).getThisDateId());
					String nowTime1 =null;
					if (nowTime%2==0){
						nowTime1 = String.valueOf(nowTime/2)+"小时"+"00分";
					}else if (nowTime%2>0){
						nowTime1 = nowTime/2+"小时"+"30分";
					}
					dyfHelpBean.setNewTime(nowTime1);
					if (beginShfitBean.size()>0){
						dyfHelpBean.setBeginTime(beginShfitBean.get(0).getShfitStartTime());
					}
					dyfHelpBeanList.add(dyfHelpBean);
				}

			}

			if (dyfBusShfitBeanList.get(i).getShfitBusLine().equals("2路"))
			{
				if (dyfBusShfitBeanList.get(i).getShfitThisStation().equals("0")){
					List<DyfBusShfitBean> beginShfitBean = dbs.timeNameLisence("1",dyfBusShfitBeanList.get(i).getShfitDate(),dyfBusShfitBeanList.get(i).getShfitBusId(),String.valueOf(afterTime.get(0).getDateId()));
					dyfHelpBean.setLiscense(dyfBusShfitBeanList.get(i).getBusLicense());
					dyfHelpBean.setStopStation(list.get(2).getRouteStationName());
					dyfHelpBean.setNewStation(list.get(2).getRouteStationName());
					dyfHelpBean.setJionTime(dyfBusShfitBeanList.get(i).getShfitStartTime());
					Integer nowTime = afterTime.get(0).getDateId() - Integer.valueOf(dyfBusShfitBeanList.get(i).getThisDateId());
					String nowTime1 =null;
					if (nowTime%2==0){
						nowTime1 = String.valueOf(nowTime/2)+"小时"+"00分";
					}else if (nowTime%2>0){
						nowTime1 = nowTime/2+"小时"+"30分";
					}
					dyfHelpBean.setNewTime(nowTime1);
					if (beginShfitBean.size()>0){
						dyfHelpBean.setBeginTime(beginShfitBean.get(0).getShfitStartTime());
					}
				}
				if (dyfBusShfitBeanList.get(i).getShfitThisStation().equals("1")){
					List<DyfBusShfitBean> beginShfitBean = dbs.timeNameLisence("0",dyfBusShfitBeanList.get(i).getShfitDate(),dyfBusShfitBeanList.get(i).getShfitBusId(),String.valueOf(afterTime.get(0).getDateId()));
					dyfHelpBean.setLiscense(dyfBusShfitBeanList.get(i).getBusLicense());
					dyfHelpBean.setStopStation(list.get(3).getRouteStationName());
					dyfHelpBean.setNewStation(list.get(3).getRouteStationName());
					dyfHelpBean.setJionTime(dyfBusShfitBeanList.get(i).getShfitStartTime());
					Integer nowTime = afterTime.get(0).getDateId() - Integer.valueOf(dyfBusShfitBeanList.get(i).getThisDateId());
					String nowTime1 =null;
					if (nowTime%2==0){
						nowTime1 = String.valueOf(nowTime/2)+"小时"+"00分";
					}else if (nowTime%2>0){
						nowTime1 = nowTime/2+"小时"+"30分";
					}
					dyfHelpBean.setNewTime(nowTime1);
					if (beginShfitBean.size()>0){
						dyfHelpBean.setBeginTime(beginShfitBean.get(0).getShfitStartTime());
					}

				}
				dyfHelpBeanList.add(dyfHelpBean);
			}
		}
		List<DyfBusBean>dyfBusBeans = dbs.selectAllNoShfitBus(busIdList,null,null,null);

		for (DyfBusBean dyfBusBean : dyfBusBeans)
		{
			dyfHelpBean dyfHelpBean = new dyfHelpBean();
			dyfHelpBean.setLiscense(dyfBusBean.getBusLicense());
			dyfHelpBean.setStopStation(dyfBusBean.getEndStation());
			dyfHelpBean.setNewStation("当天还未排班车辆");
			dyfHelpBeanList.add(dyfHelpBean);
		}
		//下面是用户站点车牌查询判断
		List<dyfHelpBean>returnHelpBean = new ArrayList<>();
		for (int i = 0; i < dyfHelpBeanList.size(); i++)
		{
			if (lisenceCard!=null && lisenceCard.length()>0){
				if (lisenceCard.length()<dyfHelpBeanList.get(i).getLiscense().length()){
					String st = dyfHelpBeanList.get(i).getLiscense().substring(0,lisenceCard.length());
					if (st.equals(lisenceCard)){
						returnHelpBean.add(dyfHelpBeanList.get(i));
					}
				}

			}
			if (station!=null && station.length()>0){
				if (station.length()<dyfHelpBeanList.get(i).getStopStation().length()){
					String st = dyfHelpBeanList.get(i).getStopStation().substring(0,station.length());
					if (st.equals(station)){
						returnHelpBean.add(dyfHelpBeanList.get(i));
					}
				}

			}
		}
		if (returnHelpBean.size()>0){
			tableBean.setCount(1);
			tableBean.setData(returnHelpBean);
			return tableBean;
		}else {
			tableBean.setData(dyfHelpBeanList);
			tableBean.setCount(1);
		}

		return tableBean;
	}
}
