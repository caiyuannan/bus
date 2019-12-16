package com.bus.controller;

import com.bus.javabean.DyfBusBean;
import com.bus.javabean.DyfProvince;
import com.bus.javabean.TableBean;
import com.bus.service.DyfBusConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


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
	//分页条数
	private  final Integer pageSize = 5;
	//用于省份城市的树状图数据获取
	@RequestMapping("/selectAllProvince")
	public String selectProvinceAndcity(HttpServletResponse response, HttpServletRequest request){
		List<DyfProvince>provincesList = dbs.selectProvinceCity();
		request.setAttribute("provincesList",provincesList);
		return "backjsp/DyfBusWelecomeMain";
	}
	//用于公交管理页面的跳转
	@RequestMapping("/discriminate")
	public String cityDiscriminate(String parm){
		 province = parm;
		return "backjsp/DyfBusManger";
	}
	@RequestMapping("/DyfBusMangerServlet")
	@ResponseBody
	public TableBean selectAllBus(String carCard,String driverAge,String page,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//保养人名称
		String driverName= request.getParameter("driverName");
		//车辆状态
		String state = request.getParameter("driverSta");
		//车辆使用年限
		String sendYear = request.getParameter("sendYear");
		String str1 = page;
		//str1表示为当前页数，主要从前台数据返回，当前天无数据时，默认为第一页
		if (str1==null){
			str1= "1";
		}
		//这里暂时写死，后面从接口拿到数据再操作
		if (province.equals("厦门市")){
			province = "闽D";

		}
		//用户前台用户添加车辆信息时判断是否为该地区车辆
		request.getSession().setAttribute("province",province);
		String startPage = String.valueOf((Integer.valueOf(str1) - 1) * 5);
		//获得分页的车辆数据
		List<DyfBusBean> busBeans = dbs.selectBusManger(province,driverName,carCard,driverAge,sendYear,state,Integer.valueOf(startPage),pageSize);
		TableBean tableBean = new TableBean();
		//获得数据库分页页数
		int count = dbs.selectCountBus(province,driverName,carCard,driverAge,sendYear,state).intValue();
		tableBean.setCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		tableBean.setData(busBeans);
		request.setAttribute("selectAllDriver",dbs.selectDriver());
		return tableBean;
	}

	//url跳转路径，用来数据库等跳转问题
	@RequestMapping("/demo/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		return "backjsp/"+path;
	}



}
