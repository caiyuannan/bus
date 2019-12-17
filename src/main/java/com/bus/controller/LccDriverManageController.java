package com.bus.controller;

import com.bus.javabean.LccCrewSchedulingBean;
import com.bus.javabean.LccDriverBean;
import com.bus.javabean.Mssg;
import com.bus.service.LccDriverManageService;
import com.bus.until.GetWeek;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 司机管理控制器
 * by连晨诚
 */
@Controller
public class LccDriverManageController
{
	@Resource
	private LccDriverManageService ldms;

	private ModelAndView modelAndView = new ModelAndView();

	/**
	 * 预加载司机名字
	 * @return
	 */
	@RequestMapping("/driverManage")
	public ModelAndView toCrewSchedulingPage(String date){

		System.out.println("进入司机排班");

		try
		{

			List list = null;
			if(null==date){

				Date nowDate = new Date();
				list = GetWeek.convertWeekByDate(nowDate);
			}else{

				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				list = GetWeek.convertWeekByDate(date1);
			}

			HashMap<String, ArrayList<LccCrewSchedulingBean>> map = ldms.queryWeekWork();
			System.out.println("周Week"+list);
			System.out.println("workmap"+map);
			modelAndView.addObject("workmap",map);
			modelAndView.addObject("week",list);

		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		modelAndView.setViewName("backjsp/driverManage");

		return modelAndView;

	}

	@RequestMapping("toCrewScheduling")
	@ResponseBody
	public void toCrewScheduling(HttpServletRequest request, HttpServletResponse response){

//
//		String s = request.getParameter("msg");
//
//		System.out.println(s);
//
//		Gson g = new Gson();
//
//		Mssg msg = g.fromJson(s, Mssg.class);
//		if(msg.getSendMssg().contains(","))//判断上传的字符串是否有","如果有就做切割
//		{
//			String[] arr = msg.getSendMssg().split(",");
//			String head = arr[0];
//			String strid = arr[1];
//			String strdate = arr[2];
//
//			int sid = Integer.parseInt(strid);
//
//			switch (arr[0]){
//				case "add":
//
//					boolean fg = ldms.checkDriverWork(Integer.parseInt(strid),strdate);
//					if(fg==false)
//					{
//						boolean flag = StaffAction.getInstance().addWork(sid, strdate);
//
//						if (flag)
//						{
//							try
//							{
//								Mssg m = new Mssg();
//								m.setSendMssg("添加成功");
//								String jsonstr = g.toJson(m);
//								System.out.println(jsonstr);
//								response.setHeader("Content-Type", "text/html;charset=GB2312");
//								PrintWriter printWriter = new PrintWriter(response.getOutputStream());
//								printWriter.println(jsonstr);
//								printWriter.flush();
//							} catch (IOException e)
//							{
//								e.printStackTrace();
//							}
//						}
//					}else{
//
//						try
//						{
//							Mssg m = new Mssg();
//							m.setSendMssg("该排班已经存在");
//							String jsonstr = g.toJson(m);
//							System.out.println(jsonstr);
//							response.setHeader("Content-Type", "text/html;charset=GB2312");
//							PrintWriter printWriter = new PrintWriter(response.getOutputStream());
//							printWriter.println(jsonstr);
//							printWriter.flush();
//						} catch (IOException e)
//						{
//							e.printStackTrace();
//						}
//					}
//					break;
//				case "del":
//					boolean b = StaffAction.getInstance().delWork(sid);
//					if (b)
//					{
//						try
//						{
//							Mssg m = new Mssg();
//							m.setSendMssg("删除成功");
//							String jsonstr = g.toJson(m);
//							System.out.println(jsonstr);
//							response.setHeader("Content-Type", "text/html;charset=GB2312");
//							PrintWriter printWriter = new PrintWriter(response.getOutputStream());
//							printWriter.println(jsonstr);
//							printWriter.flush();
//						} catch (IOException e)
//						{
//							e.printStackTrace();
//						}
//					}
//					break;
//
//				default:
//					break;
//
//			}
//
//		}

	}

}
