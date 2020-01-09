package com.bus.controller;

import com.bus.javabean.*;
import com.bus.service.LccDriverManageService;
import com.bus.until.GetTon;
import com.bus.until.GetWeek;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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

	private static String accessToken;

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

				Date workDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				list = GetWeek.convertWeekByDate(workDate);
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
	private Msg checkWorkloadMethod(String page, String limit, String workDate,String driverID){

		System.out.println("进入查看工作量方法"+driverID+"page:" + page + ",limit:" + limit +",workDate:" +workDate);

		Map<String,Object> map=new HashMap<String,Object>();
		Msg msg = new Msg();

		int pg = Integer.parseInt(page);
		int lt = Integer.parseInt(limit);
		int pg1 = (pg-1)*lt;

		if (null == workDate || "".equals(workDate)){
			workDate="";
			map.put("start",pg1);
			map.put("end",lt);
			map.put("driverId",driverID);
			//System.out.println(map);
			List<LccDriverWorkloadBean> dwblist = ldms.findDriverWorkload(map);
			System.out.println("返回集合"+dwblist);
			int count = ldms.getTotalPages1(map);

			msg = new Msg(0, null, count, dwblist);
		}else{

			String[] arr = workDate.split(" - ");
			System.out.println(arr[0]+"//"+arr[1]);
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


	//司机进入人脸识别页面
	@RequestMapping("/AddFace")
	public ModelAndView toAddFace(){
		//modelAndView.addObject("driverId", did);
		System.out.println("进入添加人脸识别页面");
		modelAndView.setViewName("front/AddWorker");

		return modelAndView;
	}



	//添加司机人脸
	@RequestMapping("/addface")
	@ResponseBody
	public Msg AddFace(Face face){

		int i= ldms.save(face);
		Msg msg=new Msg();
		if(i>0){

			msg.setMsg("1");

		}else {
			msg.setMsg("2");
		}
		return msg;



	}


	//司机进入人脸识别页面
	@RequestMapping("/toFaceLogin")
	public ModelAndView toFaceLogin(){
		//modelAndView.addObject("driverId", did);
		System.out.println("进入人脸识别页面");
		modelAndView.setViewName("front/faceLogin");

		return modelAndView;
	}

	//后台人脸识别的登录
	@RequestMapping("/facelogin")
	@ResponseBody
	public Face onListStudent(HttpServletRequest request,
	                          HttpServletResponse response, HttpSession httpSession, Model model) {
		// 获取前端页面传过来的参数
		String base = request.getParameter("base");
		try {
			Face u = new Face();
			u.setFace(base.getBytes());
			//把前端抓取到的图片保存到数据库
			//				      adminLoginService.save(u);
			List<Face> users = ldms.selectAllFace();
			String base64 = "";
			PrintWriter writer = response.getWriter();
			response.reset();
			for (Face user : users) {
				base64 = new String(user.getFace());
				boolean result = getResult(base, base64);
				if (result==true) {
					Face Face=new Face();
//					Face.setFaceaccount(user.getFaceaccount());
//					Face.setFacepass(user.getFacepass());
//					Face.setFlage(true);
					return Face;

				}


			}

		} catch (Exception e) {
			e.printStackTrace();
			//			return "redirect:/page/404.jsp";
		}

		return null;
	}


	/** 人脸识别 比对 */
	public boolean getResult(String imStr1, String imgStr2) {

		accessToken = GetTon.getToken();
		boolean flag = false;
		BufferedReader br = null;
		String result = "";
		// 定义请求地址
		String mathUrl = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		try {
			//页面抓拍到的人脸
			List<JSONObject> images = new ArrayList<>();
			JSONObject image1 = new JSONObject();
			image1.put("image", imStr1);
			image1.put("image_type", "BASE64");
			image1.put("face_type", "LIVE");
			image1.put("quality_control", "LOW");
			image1.put("liveness_control", "NORMAL");

			//数据库中人脸
			JSONObject image2 = new JSONObject();
			image2.put("image", imgStr2);
			image2.put("image_type", "BASE64");
			image2.put("face_type", "LIVE");
			image2.put("quality_control", "LOW");
			image2.put("liveness_control", "NORMAL");
			images.add(image1);
			images.add(image2);
			// 调用百度云 【人脸对比】接口
			String genrearlURL = mathUrl + "?access_token=" + accessToken;
			// 创建请求对象
			URL url = new URL(genrearlURL);
			// 打开请求链接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置请求方法
			connection.setRequestMethod("POST");
			// 设置通用的请求属性
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			// 获得请求输出流对象
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(images.toString());
			// 刷新流
			out.flush();
			// 关闭流
			out.close();
			// 建立实际链接
			connection.connect();
			// 读取URL的响应
			br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		// result ="{"error_msg":"Unsupported openapi method","error_code":3}"
		System.out.println(result);
		JSONObject fromObject = JSONObject.fromObject(result);

		JSONObject jsonArray = fromObject.getJSONObject("result");

		double resultList = jsonArray.getDouble("score");
		if (resultList >= 90) {
			System.out.println("tttt");
			flag = true;

		}
		return flag;
	}

}