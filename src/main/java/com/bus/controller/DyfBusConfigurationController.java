package com.bus.controller;

import com.bus.javabean.DyfBusBean;
import com.bus.javabean.DyfProvince;
import com.bus.javabean.TableBean;
import com.bus.service.DyfBusConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class DyfBusConfigurationController
{
	//bus管理service类
	@Resource
	private DyfBusConfigurationService dbs;
	//用户点击菜单选择所在的城市
	private String province;
	private  final Integer page = 5;
	@RequestMapping("/selectAllProvince")
	public String selectProvinceAndcity(HttpServletResponse response, HttpServletRequest request){
		List<DyfProvince>provincesList = dbs.selectProvinceCity();
		for (DyfProvince dyfProvince : provincesList)
		{
			System.out.println(dyfProvince.getCity().get(0).getCityName());
		}
		request.setAttribute("provincesList",provincesList);
		return "backjsp/DyfBusWelecomeMain";
	}
	@RequestMapping("/discriminate")
	public String cityDiscriminate(String parm){
		 province = parm;
		return "backjsp/DyfBusManger";
	}
	@RequestMapping("/DyfBusMangerServlet")
	@ResponseBody
	public TableBean selectAllBus(String driverName,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String str1 = request.getParameter("page");
		if (str1==null){
			str1= "1";
		}
		if (province.equals("厦门市")){
			province = "闽D";
		}
		String startPage = String.valueOf((Integer.valueOf(str1) - 1) * 5);
		List<DyfBusBean> busBeans = dbs.selectBusManger(province,driverName,null,null,null,null,Integer.valueOf(startPage),page);
		TableBean tableBean = new TableBean();
		int count = dbs.selectCountBus(province,driverName,null,null,null,null).intValue();
		tableBean.setCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		tableBean.setData(busBeans);
		return tableBean;
	}

}
