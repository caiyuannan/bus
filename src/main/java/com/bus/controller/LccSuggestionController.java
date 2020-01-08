package com.bus.controller;

import com.bus.javabean.LccAdviceBean;
import com.bus.javabean.Msg;
import com.bus.service.LccSuggestionService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 建议控制器
 * by连晨诚
 */
@Controller
public class LccSuggestionController
{
	@Resource
	private LccSuggestionService sggs;
	private ModelAndView modelAndView = new ModelAndView();

	/**
	 * 进入投诉建议页面
	 * @return
	 */
	@RequestMapping("/toSuggesting")
	public ModelAndView toSuggesting(){

		System.out.println("进入提交页面");
		modelAndView.setViewName("backjsp/lccSuggesting");

		return modelAndView;
	}


	/**
	 * 进入用户投诉建议
	 * @return
	 */
	@RequestMapping("/toUpload")
	@ResponseBody
	public String uploadFile(MultipartFile file,String desc,HttpServletRequest request)
	{
		String st = request.getSession().getAttribute("userPhone").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		System.out.println("--------"+userName+"-----------"+st);
		System.out.println("进入用户个人中心投诉建议方法"+desc+"//");
		String name= userName;
		String phone= st;
		String content= desc;
		int res = 1;
		LccAdviceBean adviceBean = new LccAdviceBean();
		adviceBean.setAdviceName(name);
		adviceBean.setAdvicePhone(phone);
		adviceBean.setAdviceSth(content);
		int id= sggs.addAdvice(adviceBean);
		System.out.println("返回ID"+adviceBean.getAdviceId());

		adviceBean.setAdviceId(adviceBean.getAdviceId());
		try
		{
			String imgPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\tempImage\\"+ file.getOriginalFilename();
			file.transferTo(new File(imgPath));
			adviceBean.setImagePath(imgPath);
			res=sggs.addAdviceImage(adviceBean);
			if(res>0){

				return "200";
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}


		return "0";
	}


	/**
	 * 进入处理建议页面
	 * @return
	 */
	@RequestMapping("/toHandlingSuggesting")
	public ModelAndView toHandlingSuggesting(){

		System.out.println("进入提交页面");
		modelAndView.setViewName("backjsp/lccHandlingSuggestion");

		return modelAndView;
	}


	@RequestMapping("/HandlingSuggesting")
	@ResponseBody
	public Msg searchDriverWage(String page, String limit){

		System.out.println("处理用户表格");
		Map<String,Object> map=new HashMap<String,Object>();
		Msg msg = new Msg();

		int pg = Integer.parseInt(page);
		int lt = Integer.parseInt(limit);
		int pg1 = (pg-1)*lt;
		map.put("start",pg1);
		map.put("end",lt);
		List<LccAdviceBean> list = sggs.findAdvice(map);
		System.out.println("返回集合"+list);
		int count = sggs.getTotalPages2(map);

		msg = new Msg(0, null, count, list);

		return msg;
	}
}
