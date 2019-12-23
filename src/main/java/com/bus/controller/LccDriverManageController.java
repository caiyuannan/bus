package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.LccDriverManageService;
import com.bus.until.GetWeek;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 司机管理控制器
 * by连晨诚
 */
@Controller
public class LccDriverManageController
{
	@Resource
	private LccDriverManageService ldms;

	int count=0;

	private ModelAndView modelAndView = new ModelAndView();

	/**
	 * 预加载司机名字
	 * @return
	 */
	@RequestMapping("/driverManage")
	public ModelAndView toCrewSchedulingPage(String date){

		System.out.println("进入司机排班"+count);

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

			HashMap<String, ArrayList<LccCrewSchedulingBean>> map = ldms.queryWeekWork(null);
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
	/**
	 * 进入上一周排班
	 * @return
	 */
	@RequestMapping("/toPreWeek")
	@ResponseBody
	public ModelAndView preWeek(String nwd)
	{
		System.out.println("进入上一周方法"+count);

		try
		{

				//int count =0;
				//String dt = request.getParameter("nwd");
			if(count>0)
			{
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Date dat = format1.parse(nwd);
				Date date2 = new Date();

				List list = null;
				count--;

				Calendar calendar = Calendar.getInstance();

				//calendar.setTime(dat);
				calendar.add(Calendar.WEEK_OF_MONTH, count);//相当于在当前日期上加7天，例如现在是周一，加完后是下周一
				calendar.set(Calendar.DAY_OF_WEEK, 7);//把当前日期所在的周设置到周六（7 就是一周的最后一天：周六）

				list = GetWeek.convertWeekByDate(calendar.getTime());

				HashMap<String, ArrayList<LccCrewSchedulingBean>> map = ldms.queryWeekWork(null);
				modelAndView.addObject("workmap", map);
				modelAndView.addObject("week", list);
				modelAndView.setViewName("backjsp/driverManage");
			}
			if(count==0){
				modelAndView.addObject("msg1", "过去排班已作废");
				modelAndView.setViewName("backjsp/driverManage");
			}

		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return modelAndView;
	}

	/**
	 * 进入下一周排班
	 * @return
	 */
	@RequestMapping("/toNextWeek")
	@ResponseBody
	public ModelAndView nextWeek()
	{

		System.out.println("进入下一周方法"+count);


		List list = null;
		int a = 0;
		LccCrewSchedulingBean lcdb = new LccCrewSchedulingBean();
		count++;
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		Date date2 = new Date();
		calendar.setTime(date2);
		calendar.add(Calendar.WEEK_OF_MONTH,count);//相当于在当前日期上加7天，例如现在是周一，加完后是下周一
		calendar.set(Calendar.DAY_OF_WEEK,7);//把当前日期所在的周设置到周六（7 就是一周的最后一天：周六）

		list = GetWeek.convertWeekByDate(calendar.getTime());
		System.out.println("下周时间"+list);

//		List<Integer> ilist = ldms.findDriverId();
//
//		for (Integer j : ilist) {
//
//			for (int i = 0; i <list.size() ; i++)
//
//			{
//				lcdb.setDriverId(j);
//				lcdb.setWorkTime(String.valueOf(list.get(i)));
//				lcdb.setWorkType("操作");
//				a = ldms.insertBlankWork(lcdb);
//			}
//
//		}

		HashMap<String, ArrayList<LccCrewSchedulingBean>> map = ldms.queryWeekWork(null);
		modelAndView.addObject("workmap",map);
		modelAndView.addObject("week",list);
		modelAndView.setViewName("backjsp/driverManage");

		return modelAndView;
	}
	/**添加排班方法
	 * @return
	 */
	@RequestMapping("/toAddBlankWork")
	@ResponseBody
	public String addBlankWork(String msg)
	{

		System.out.println("进入排班添加方法" + msg);

		if (msg.contains(","))
		{
			String[] arr = msg.split(",");

			int did = ldms.queryDriverIdByDriverName(arr[2]);


			if(arr[0].equals("排班")&&arr[1]!=null && !"".equals(arr[1].trim()))
			{

				boolean flag = ldms.addDriverWork(arr[1], did,arr[3]);
				if (flag)
				{
					return "排班";
				}
			}
			if(arr[0].equals("休息")){
				boolean flag = ldms.addDriverWork(arr[0], did,arr[3]);
				if (flag)
				{
					return "休息";
				}
			}
		}
		return null;
	}


	/**修改排班方法
	 * @return
	 */
	@RequestMapping("/toAddWork")
	@ResponseBody
	public String addWork(String msg)
	{

		System.out.println("进入排班修改方法" + msg);

		if (msg.contains(","))
		{
			String[] arr = msg.split(",");
			//System.out.println(arr[0]+"//"+arr[1]+"//"+arr[2]);

			if(arr[0].equals("排班")&&arr[1]!=null && !"".equals(arr[1].trim()))
			{

				boolean flag = ldms.updateDriverWork(arr[1], arr[2]);
				if (flag)
				{
					return "排班";
				}
			}
			if(arr[0].equals("休息")){
				boolean flag = ldms.updateDriverWork(arr[0], arr[2]);
				if (flag)
				{
					return "休息";
				}
			}
		}
		return null;
	}

	/**
	 * 进入按车牌查询排班
	 * @return
	 */
	@RequestMapping("/findPlateNumber")
	public ModelAndView findPlateNumber(String title,String start,String end){
		System.out.println(title+"//"+start+"//"+end);
//		//将json字符串转化为JSONObject
		////		JSONObject jsonObject = JSONObject.fromObject(msg);
		////		//通过getString("")分别取出里面的信息,title对应前台input的name
		////		String name = jsonObject.getString("title");
		LccCrewSchedulingBean lcsb = new LccCrewSchedulingBean();
		lcsb.setWorkType(title);
		lcsb.setStartTime(start);
		lcsb.setEndTime(end);
		Date nowDate = new Date();
		List list = GetWeek.convertWeekByDate(nowDate);
		HashMap<String, ArrayList<LccCrewSchedulingBean>> map = ldms.queryWeekWork(lcsb);
		System.out.println("周Week"+list);
		System.out.println("workmap"+map);
		modelAndView.addObject("workmap",map);
		modelAndView.addObject("week",list);
		modelAndView.setViewName("backjsp/driverManage");

		return modelAndView;
	}

	@RequestMapping("/lccDriverNotarize")
	public ModelAndView toNotarizeWorkload(String busnum,String route,String driver){

		System.out.println("进入出站确认页面");

		List<DyfBusBean> lst = ldms.findAllBuses();
		List<LccRouteBean> rst = ldms.findAllRoutes();
		List<LccDriverBean> dst = ldms.findAllDriver();
		System.out.println("获取线路集合"+rst);

		LccBusShfitBean lcsb = ldms.findBusShfit();
		String satTime = lcsb.getShfitDate()+","+lcsb.getShfitStartTime();
		//获取时间
		modelAndView.addObject("start",satTime);
		//获取车牌
		modelAndView.addObject("busnum",busnum);
		modelAndView.addObject("olist",lst);

		//获取线路
		modelAndView.addObject("route",route);
		modelAndView.addObject("rlist",rst);

		//获取司机
		modelAndView.addObject("driver",driver);
		modelAndView.addObject("dlist",dst);
		//return "backjsp/lccDriverNotarize";
		modelAndView.setViewName("backjsp/lccDriverNotarize");

		return modelAndView;
	}

	@RequestMapping("/toDriverNotarize")
	public ModelAndView lccToDriverNotarize(String start,String busnum,String route,String driver){
		//进入方法
		System.out.println("进入方法"+start+"//"+busnum+"//"+route+"//"+driver);

		//System.out.println("成功"+i);
		//添加考勤表
		Map<String,Object> map1 = new HashMap<String,Object>();
		String[] arr = start.split(",");
		String theDate = arr[0];
		String theTime = arr[1];
		System.out.println("切割后的时间"+arr[0]+"//"+arr[1]);
		map1.put("driverName",driver);
		map1.put("shfit_start_time",theTime);
		map1.put("shfit_date",theDate);
		int j = ldms.addGateCard(map1);
		//查询考勤ID
		int wkid = ldms.queryAttendanceId();
		//添加工作量表
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("busName",busnum);
		map.put("routeName",route);
		map.put("driverName",driver);
		map.put("attendanceId",wkid);
		int i =ldms.insertDriverWorkload(map);
		//修改汽车排班  发车状态和关联车牌
		Map<String,Object> map2 = new HashMap<String,Object>();
		LccBusShfitBean lcsb = ldms.findBusShfit();
		int sid = lcsb.getShfitId();
		map2.put("shfitId",sid);
		map2.put("busName",busnum);
		boolean flag = ldms.updateBusShfitState(map2);

		if(flag)
		{
			modelAndView.addObject("msg1", "出站确认成功!");
			modelAndView.setViewName("backjsp/lccDriverNotarize");
		}
		return modelAndView;
	}


    @RequestMapping("/lccDriver")
	public ModelAndView toDriver(){
	    System.out.println("进入司机页面...");
	    modelAndView.setViewName("backjsp/lccDriver");
		return modelAndView;
    }

	//查询司机方法

	@RequestMapping("/showDriverTable")
	@ResponseBody
	//@Log(operationType="数据表格查询用户",operationName="查询用户方法")
	public Msg totestRequstParam(String page, String limit, String driverName, String driverCellphone, String stationName){
		System.out.println("page:" + page + ",limit=" + limit + ",driverName =" + driverName+ ",driverCellphone =" + driverCellphone+ ",stationName =" + stationName);

		Map<String,Object> map=new HashMap<String,Object>();
		int pg = Integer.parseInt(page);
		int lt = Integer.parseInt(limit);
		int pg1 = (pg-1)*lt;

		if (null == driverName || "".equals(driverName)){
			driverName="";
		}else if(null == driverCellphone || "".equals(driverCellphone)){
			driverCellphone="";
		}else if(null == stationName || "".equals(stationName)){
			stationName="";
		}
		map.put("start",pg1);
		map.put("end",lt);

		map.put("driverName",driverName);
		map.put("driverCellphone",driverCellphone);
		map.put("stationName",stationName);
		System.out.println(map);
		List<LccDriverBean> eilist = ldms.queryAllDrivers(map);

		int count = ldms.getTotalPages(map);

		Msg msg = new Msg(0, null, count, eilist);

		System.out.println(msg+"111111");
		return msg;

	}

	//修改司机方法
	@RequestMapping("/toUpdateDriver")
	@ResponseBody
	//@Log(operationType="修改用户",operationName="修改用户方法")
	public String toUpdate(String msg){
		System.out.println(msg);
		if(msg.contains(","))
		{
			String[] arr = msg.split(",");
			LccDriverBean ldb = new LccDriverBean();
			ldb.setDriverId(Integer.parseInt(arr[3]));
			ldb.setDriverName(arr[0]);
			ldb.setDriverCellphone(arr[1]);
			ldb.setStationName(arr[2]);

			boolean flag = ldms.updateDrivers(ldb);
			if(flag){
				return "200";
			}

		}

		return null;
	}

	//查看司机工作量页面
	@RequestMapping("/toCheckWorkload")

	public ModelAndView checkWorkload(String did){

		modelAndView.addObject("driverId", did);
		modelAndView.setViewName("backjsp/lccCheckDriverWorkload");

		return modelAndView;
	}

	//查看工作量数据表格带条件分页搜索
	@RequestMapping("/toDriverWorkload")
	@ResponseBody
	private Msg checkWorkloadMethod(String page, String limit, String date1,String driverID){

		System.out.println("进入查看工作量方法"+driverID+"page:" + page + ",limit:" + limit +",date1:" +date1);

		Map<String,Object> map=new HashMap<String,Object>();
		Msg msg = new Msg();

		int pg = Integer.parseInt(page);
		int lt = Integer.parseInt(limit);
		int pg1 = (pg-1)*lt;

		if (null == date1 || "".equals(date1)){
			date1="";
			map.put("start",pg1);
			map.put("end",lt);
			map.put("driverId",driverID);
			//System.out.println(map);
			List<LccDriverWorkloadBean> dwblist = ldms.findDriverWorkload(map);
			System.out.println("返回集合"+dwblist);
			int count = ldms.getTotalPages1(map);

			msg = new Msg(0, null, count, dwblist);
		}else{

			String[] arr = date1.split("-");

			map.put("start",pg1);
			map.put("end",lt);
			map.put("driverId",driverID);
			map.put("startTime",arr[0]);
			map.put("endTime",arr[1]);
			//System.out.println(map);
			List<LccDriverWorkloadBean> dwblist = ldms.findDriverWorkload(map);
			System.out.println("返回集合"+dwblist);
			int count = ldms.getTotalPages1(map);
			msg = new Msg(0, null, count, dwblist);
		}
		return msg;
	}

	@RequestMapping("/DriverWage")
	public ModelAndView driverWage(){
		//modelAndView.addObject("driverId", did);
		System.out.println("进入司机工资页面");
		modelAndView.setViewName("backjsp/lccDriverWage");

		return modelAndView;
	}

	@RequestMapping("/toDriverWage")
	@ResponseBody
	public Msg searchDriverWage(String page, String limit){

		System.out.println("司机工资表格");
		Map<String,Object> map=new HashMap<String,Object>();
		Msg msg = new Msg();

		int pg = Integer.parseInt(page);
		int lt = Integer.parseInt(limit);
		int pg1 = (pg-1)*lt;
		map.put("start",pg1);
		map.put("end",lt);
		List<LccDriverWageBean> list = ldms.findDriverWage(map);
		System.out.println("返回集合"+list);
		int count = ldms.getTotalPages2(map);

		msg = new Msg(0, null, count, list);

		return msg;
	}
}