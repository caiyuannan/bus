package com.bus.controller;

import com.bus.javabean.LccCashBean;
import com.bus.javabean.Msg;
import com.bus.javabean.Mssg;
import com.bus.service.LccStatisticsService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收银与高峰期统计控制类
 * by 连晨诚
 */
@Controller
public class LccStatisticsController
{
	@Resource
	private LccStatisticsService lss;

	private ModelAndView modelAndView = new ModelAndView();

	/**
	 * 进入统计页面
	 * @return
	 */
	@RequestMapping("/lccCashStatistics")
	public ModelAndView cashStatistics(){

		System.out.println("进入统计页面");
		modelAndView.setViewName("backjsp/lccRouteCash");

		return modelAndView;
	}


	/**
	 * 路线收银统计
	 * @return
	 */
	@RequestMapping("/toRouteCash")
	@ResponseBody
	public String toRouteCash(String msg){
		System.out.println("进入线路统计方法"+msg);
		String startTime =null;
		String endTime=null;
		Gson gson = new Gson();
		String jsonStr = null;
		if(msg!=null && !"".equals(msg.trim()) && msg.contains(","))
		{
			System.out.println("进入非空>>>>>>");
			String[] arr=msg.split(",");
			startTime = arr[0];
			endTime=arr[1];
			System.out.println("start"+startTime+"end"+endTime);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", startTime);
			map.put("end", endTime);
			List<LccCashBean> list1 = lss.findRouteCashesByDate(map);
			jsonStr = gson.toJson(list1);
			return jsonStr;
		}
		else if(msg==null)
		{
			System.out.println("进入空=====");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", startTime);
			map.put("end", endTime);
			List<LccCashBean> list1 = lss.findRouteCashesByDate(map);
			jsonStr = gson.toJson(list1);
			return jsonStr;
		}
		return null;
	}
}
