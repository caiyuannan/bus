package com.bus.controller;

import com.bus.dao.XhaRouteConfigurationMapper;
import com.bus.javabean.*;
import com.bus.service.CnmAdvertisementService;
import com.bus.service.CnmNewsBulletinService;
import com.bus.service.CnmTbRouteOrderService;
import com.bus.service.XhaRouteConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 查询的（临时）
 */

@Controller
public class CnmfindStationController
{
	@Resource
	private CnmTbRouteOrderService cnmTbRouteOrderService;
//	@Resource
//	CnmAdvertisementService cnmAdvertisementService;
//	CnmTablePageInf cnmTablePageInf=new CnmTablePageInf();
//	@Resource
//	CnmNewsBulletinService cnmNewsBulletinService;
//	@Resource
//	private XhaRouteConfigurationService routeService;
//	@RequestMapping("/inquire")
//	public ModelAndView treeUrl(){
//
//		ModelAndView modelAndView=new ModelAndView();
//		//		路线查询
//		List<XhaRouteOrderBean> routeList = routeMapper.queryAllRoute();
//		for (XhaRouteOrderBean routeOrder : routeList) {
//			System.out.println(routeOrder.getRouteName()+"__---");
//		}
//		modelAndView.addObject("routeLists", routeList);//新闻栏
//
//		List<XhaStationBean> stationList = routeService.queryAllStation();//查询所有站点的信息
//		modelAndView.addObject("stationList", stationList);//新闻栏
//		modelAndView.setViewName("front/inquire");
//
//
//
//
//		return modelAndView;
//	}



	//	cnm通过站点名查询站点信息x,y
	@RequestMapping("/findQueryStationByRouteOrderName")
	@ResponseBody
	public CnmFindStationWhere queryStationByRouteOrderName(String startStationName,String endStationName){

		String router;
		int startOrder;
		int endOrder;
		CnmFindStationWhere cnmFindStationWhere=new CnmFindStationWhere();
		cnmFindStationWhere.setEndStop(endStationName);
		cnmFindStationWhere.setStartStop(startStationName);

		CnmFindStationWhere FindStationWhere=cnmTbRouteOrderService.getQueryStationByRouteOrderName(cnmFindStationWhere);
		if(null!=FindStationWhere){
//			 router=FindStationWhere.getRouteName();
//			startOrder=FindStationWhere.getStartOrder();
//			endOrder=FindStationWhere.getEndOrder();
			FindStationWhere.setRouteName(FindStationWhere.getRouteName());
//			cnmFindStationWhere.setRouteName(router);
//			cnmFindStationWhere.setStartOrder(startOrder);
//			cnmFindStationWhere.setEndOrder(endOrder);
			List<XhaRouteOrderBean>RouteOrderBeanLis=cnmTbRouteOrderService.getQueryStationInfoBySE(FindStationWhere);
		    int sum=RouteOrderBeanLis.size();

			FindStationWhere.setSum(sum);
			FindStationWhere.setList(RouteOrderBeanLis);
			//	cnm通过站点名查询线路
			List<XhaStationBean> list=new ArrayList<>();
			for (int i = 0; i < RouteOrderBeanLis.size(); i++)
			{
				XhaStationBean xhaStationBean=cnmTbRouteOrderService.queryStation(RouteOrderBeanLis.get(i).getStationName());
				list.add(xhaStationBean);
			}
			FindStationWhere.setListStation(list);


//			cnm通过站点名查询开始站点信息x,y
			XhaStationBean xhaStationBean=cnmTbRouteOrderService.queryStation(startStationName);
			FindStationWhere.setStartStopX(xhaStationBean.getStationLat());
			FindStationWhere.setStartStopY(xhaStationBean.getStationLon());

			FindStationWhere.setMsg("success");
		}else{
			FindStationWhere.setMsg("查无此路");
			return FindStationWhere;
		}

		return FindStationWhere;
	}


//	@RequestMapping("/ok")
//	@ResponseBody
//	public String  ok()
//	{
//		long l = System.currentTimeMillis();
//		Date date = new Date(l);
//		//转换提日期输出格式
//		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//		System.out.println(dateFormat.format(date));
//		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(dateFormat2.format(date));
//
//		String y=dateFormat2.format(date);
//		String str =dateFormat.format(date);
//		String[] strArr = str.split("\\:");
//		String h=strArr[strArr.length-3];
//		String minutes=strArr[strArr.length-2];
//		String s=strArr[strArr.length-1];
//		System.out.println("minutes"+minutes);
//		String msg=null;
//		if(0<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<15){
//			msg=y+" "+h+":15"+":"+s;
//		}
//		else if(15<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<30){
//			msg=y+" "+h+":30"+":"+s;
//		}else if(30<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<45){
//			msg=y+" "+h+":45"+":"+s;
//	    }else if(45<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<60){
//			msg=y+" "+(Integer.valueOf(h)+1)+":00"+":00";
//		}
//		System.out.println(msg);
//		return msg;
//
//	}

//	@RequestMapping("/ok")
//	@ResponseBody
//	public String  ok()
//	{
//
//		long l = System.currentTimeMillis();
//		Date date = new Date(l);
//		//转换提日期输出格式
//		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//		System.out.println(dateFormat.format(date));
//		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(dateFormat2.format(date));
//
//		String y=dateFormat2.format(date);
//		String str =dateFormat.format(date);
//		String[] strArr = str.split("\\:");
//		String h=strArr[strArr.length-3];
//		String minutes=strArr[strArr.length-2];
//		String s=strArr[strArr.length-1];
////		System.out.println("minutes"+minutes);
//		String msg=null;
//		if(0<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<15){
//			msg=y+" "+h+":15"+":"+s;
//		}
//		else if(15<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<30){
//			msg=y+" "+h+":30"+":"+s;
//		}else if(30<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<45){
//			msg=y+" "+h+":45"+":"+s;
//		}else if(45<Integer.valueOf(minutes)&&Integer.valueOf(minutes)<60){
//
//			if(h.equals("23")){
//				String y2=null;
//				try
//				{
//				Date d = dateFormat2.parse(y);
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(d);
//				calendar.add(Calendar.DATE, 1);
//					y2=dateFormat2.format(calendar.getTime());
//				}catch (Exception e)
//				{
//				}
//
//				msg=y2+" "+"00:00:00";
//			}else{msg=y+" "+(Integer.valueOf(h)+1)+":00"+":00";}
//
//		}
//		System.out.println(msg);
//		return msg;
//
//	}


	@RequestMapping("/ok2")
	@ResponseBody
	public CnmBusSend  ok2( HttpServletRequest request,String startStationName, String routeName, String directionSelect)
	{
		CnmBusSend cnmBusSend=new CnmBusSend();
		List list=new ArrayList();
		System.out.println(startStationName+routeName+directionSelect);
		long l = System.currentTimeMillis();
		Date date = new Date(l);
		//转换提日期输出格式
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(dateFormat2.format(date));

		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
        System.out.println("当前的时间：" + df.format(new Date()));



		CnmBusWhere cnmBusWhere=new CnmBusWhere();
		cnmBusWhere.setShfitDate(dateFormat2.format(date));
		cnmBusWhere.setShfitBusLine(routeName);
		cnmBusWhere.setShfitThisStation(Integer.valueOf(directionSelect));
		cnmBusWhere.setShfitStartTime(df.format(calendar.getTime()));
		cnmBusWhere.setShfitEndTime(df.format(new Date()));

		List<XhaRouteOrderBean>XhaRouteOrderBean=cnmTbRouteOrderService.getStationByRouteOrderName(cnmBusWhere);

		for (int i = 0; i < XhaRouteOrderBean.size(); i++)
		{
			System.out.println(XhaRouteOrderBean.get(i));
		}

		int Index=getIndex(XhaRouteOrderBean,startStationName);//调用方法
		System.out.println(Index);//输出返回值

		List<String> list2 = Arrays.asList(new String[XhaRouteOrderBean.size()]);

		List<DyfBusShfitBean>DyfBusShfitBean=cnmTbRouteOrderService.getShifSum(cnmBusWhere);

		for (int i = 0; i < DyfBusShfitBean.size(); i++)
		{
//			System.out.println(DyfBusShfitBean.size()+"QQQQQQQQQQQQQ");
//			(DyfBusShfitBean.get(i).getShfitStartTime())
			String s=null;
			try
			{
				Date d1 = df.parse(df.format(new Date()));
				Date d2 = df.parse(DyfBusShfitBean.get(i).getShfitStartTime());
				long diff = d1.getTime() - d2.getTime();
				long days = diff / (1000 * 60 * 60 * 24);

				long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
				long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
				System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
				System.out.println(minutes/15);
				list.add(minutes/15);
				System.out.println(list.size()+"QQQQQQQQQQQQQ");
				 s=(minutes/15)+"";
				System.out.println(s+"ss");

				if(minutes%15==0){
				list2.set(Integer.valueOf(s),"车辆到站");
				}else{list2.set(Integer.valueOf(s),"正在前往下一站");}
//				CnmBusSendMsg cnmBusSendMsg=new CnmBusSendMsg();
				System.out.println(list2.toString());
			}catch (Exception e)
			{
			}
		}
//		request.setAttribute("list2", list2);


		cnmBusSend.setListmsg(list2);
		cnmBusSend.setStartPost(Index);
		cnmBusSend.setListSum(list);
		cnmBusSend.setListRoute(XhaRouteOrderBean);
		cnmBusSend.setListBusShfit(DyfBusShfitBean);

		return cnmBusSend;
	}



	//查询元素在list的索引值
	public static int getIndex(List<XhaRouteOrderBean> list, String value) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getStationName()+"----");
			if (list.get(i).getStationName().equals(value))  {

				return i;                  //字符串时，换为 equals
			}
		}
		return -1;//如果未找到返回-1
	}

//	@RequestMapping("/ok2")
//	@ResponseBody
//	public CnmBusSend  ok2(String startStationName,String routeName,String directionSelect)
//	{
//		CnmBusSend cnmBusSend=new CnmBusSend();
//		List<CnmBusSendMsg> listSum=new ArrayList<>();
//		//		List list=new ArrayList();
//		System.out.println(startStationName+routeName+directionSelect);
//		long l = System.currentTimeMillis();
//		Date date = new Date(l);
//		//转换提日期输出格式
//		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(dateFormat2.format(date));
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
//		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//		System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
//		System.out.println("当前的时间：" + df.format(new Date()));
//
//
//
//		CnmBusWhere cnmBusWhere=new CnmBusWhere();
//		cnmBusWhere.setShfitDate(dateFormat2.format(date));
//		cnmBusWhere.setShfitBusLine(routeName);
//		cnmBusWhere.setShfitThisStation(Integer.valueOf(directionSelect));
//		cnmBusWhere.setShfitStartTime(df.format(calendar.getTime()));
//		cnmBusWhere.setShfitEndTime(df.format(new Date()));
//
//		List<DyfBusShfitBean>DyfBusShfitBean=cnmTbRouteOrderService.getShifSum(cnmBusWhere);
//
//		for (int i = 0; i < DyfBusShfitBean.size(); i++)
//		{
//			//			System.out.println(DyfBusShfitBean.size()+"QQQQQQQQQQQQQ");
//			//			(DyfBusShfitBean.get(i).getShfitStartTime())
//
//			try
//			{
//				Date d1 = df.parse(df.format(new Date()));
//				Date d2 = df.parse(DyfBusShfitBean.get(i).getShfitStartTime());
//				long diff = d1.getTime() - d2.getTime();
//				long days = diff / (1000 * 60 * 60 * 24);
//
//				long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
//				long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
//				System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
//				System.out.println(minutes/15);
//
//				String s=(minutes/15)+"";
//				System.out.println(s+"ss");
//				CnmBusSendMsg cnmBusSendMsg=new  CnmBusSendMsg();
//				if(minutes%15==0){
//					cnmBusSendMsg.setMsg("车辆到站");
//				}else{cnmBusSendMsg.setMsg("正在前往下一站");}
//
//				cnmBusSendMsg.setSum(Integer.valueOf(s));
//				listSum.add(cnmBusSendMsg);
//				//				list.add(minutes/15);
//
//
//
//				//				System.out.println(list.size()+"QQQQQQQQQQQQQ");
//			}catch (Exception e)
//			{
//			}
//		}
//
//		List<XhaRouteOrderBean>XhaRouteOrderBean=cnmTbRouteOrderService.getStationByRouteOrderName(cnmBusWhere);
//
//		for (int i = 0; i < XhaRouteOrderBean.size(); i++)
//		{
//			System.out.println(XhaRouteOrderBean.get(i));
//		}
//
//		int Index=getIndex(XhaRouteOrderBean,startStationName);//调用方法
//		System.out.println(Index);//输出返回值
//
//
//
//		cnmBusSend.setListSum(listSum);
//		cnmBusSend.setListRoute(XhaRouteOrderBean);
//		cnmBusSend.setListBusShfit(DyfBusShfitBean);
//
//		return cnmBusSend;
//	}


	}
