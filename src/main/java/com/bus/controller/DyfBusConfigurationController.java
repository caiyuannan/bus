/*
package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.DyfBusConfigurationService;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.List;


*/
/**
 * @author 40651
 * Controller 层
 *//*

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

	//用于车辆排班管理的省份城市的树状图数据获取
	@RequestMapping("/shfitProvince")
	public String shfitSelectAllProvince(HttpServletResponse response, HttpServletRequest request)
	{
		List<DyfProvince> provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList", provincesList);
		return "backjsp/dyfBusShfit";
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
			dyfBusBean.setBusState(list.get(0).getStateId().intValue());
		}
		if (null != userState && userState.equals("维修"))
		{
			Date date = new Date();
			String repairDate = sdf.format(date);
			String repairDateNow = sdf1.format(date);
			System.out.println(repairDate + "" + repairDateNow);
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
	@RequestMapping("/busShfitAllInforMation")
	@ResponseBody
	public TableBean busShfitAllInforMation(HttpServletRequest request,String page){
		TableBean tableBean = new TableBean();
		String str1 = page;
		if (str1==null){
			str1 = "1";
		}
		Date date = new Date();
//		当前时间，每次第一次只传回当天时间
		String dateFormation = sdf.format(date);
		String startPage = String.valueOf((Integer.valueOf(str1) - 1) * 5);
		List<DyfBusShfitBean>busShfitBeans = dbs.shfitSlectAllLine(new BigDecimal(startPage),shfitPageSize,dateFormation,shfitCity);
		tableBean.setData(busShfitBeans);
		Integer count = dbs.shfitCount(dateFormation,shfitCity);
		tableBean.setCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		return tableBean;
	}
}
*/
