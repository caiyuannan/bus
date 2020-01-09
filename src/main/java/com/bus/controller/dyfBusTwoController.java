package com.bus.controller;

import com.bus.factorybean.DyfMessageSend;
import com.bus.javabean.DyfAliPayBean;
import com.bus.javabean.DyfHtmlUserBean;
import com.bus.javabean.DyfMessageBean;
import com.bus.javabean.TableBean;
import com.bus.service.dyfBusTwoService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class dyfBusTwoController
{
	@Resource
	private dyfBusTwoService dbs;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	private final Integer pageSize = 5;
	private DyfHtmlUserBean dyfHtmlUserBean;
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@RequestMapping("/front/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		return "front/" + path;
	}
	//用户账号密码登入
	@RequestMapping("/htmlLogin")
	@ResponseBody
	public DyfHtmlUserBean htmlLogin(HttpServletRequest request, String userName, String userPs)
	{

		DyfHtmlUserBean dyfHtmlUserBean = dbs.userLogin(userName, userPs);
		redisTemplate.boundHashOps("testMap").put(dyfHtmlUserBean.getUserId(),dyfHtmlUserBean);
		List<DyfHtmlUserBean> list=redisTemplate.boundHashOps("testMap").values();
		System.out.println(list.size());
		if (dyfHtmlUserBean != null)
		{
			request.getSession().setAttribute("userName", dyfHtmlUserBean.getUserName());
			request.getSession().setAttribute("userPhone", dyfHtmlUserBean.getUserPhoneNumber());
			return dyfHtmlUserBean;
		}
		return null;
	}



	//手机验证码发送
	@RequestMapping("/userRegisetMessage")
	@ResponseBody
	public DyfMessageBean userRegisetMessage(HttpServletRequest request, String phoneNum, String loginOrRegister)
	{
		DyfHtmlUserBean repairUserPhoneNumber = dbs.userSelectAllRepeatTwo(phoneNum);
		DyfMessageBean dyfMessageBean = new DyfMessageBean();
		if (repairUserPhoneNumber == null)
		{
			dyfMessageBean = new DyfMessageSend().messageReturn(phoneNum);
			if (dyfMessageBean != null)
			{
				return dyfMessageBean;
			}
		} else
		{
			dyfMessageBean.setFee("repair");
			return dyfMessageBean;
		}

		return null;
	}

	//登入时手机验证码发送
	@RequestMapping("/userMsgLogin")
	@ResponseBody
	public DyfMessageBean userMsgLogin(HttpServletRequest request, String phoneNum, String loginOrRegister)
	{
		DyfHtmlUserBean htmlUserBean = dbs.userSelectAllRepeatTwo(phoneNum);
		DyfMessageBean dyfMessageBean;
		if (htmlUserBean != null)
		{
			dyfMessageBean = new DyfMessageSend().messageReturn(phoneNum);
			if (dyfMessageBean != null)
			{
				return dyfMessageBean;
			}
		}
		return null;
	}

	//验证码已验证，开始注册操作
	@RequestMapping("/userRegisetUser")
	@ResponseBody
	public String userRegisetAddUser(HttpServletRequest request, String userName, String passWord1, String phoneNum)
	{
		int num = dbs.userRegisterInto(userName, passWord1, phoneNum);
		if (num > 0)
		{
			return "success";
		}
		return "fail";
	}

	//手机验证码登入
	@RequestMapping("/userLoginMsg")
	@ResponseBody
	public DyfHtmlUserBean userMsgLogin(HttpServletRequest request, String userLoginView)
	{
		DyfHtmlUserBean htmlUserBean = dbs.userSelectAllRepeatTwo(userLoginView);
		if (htmlUserBean != null)
		{
			request.getSession().setAttribute("userName", htmlUserBean.getUserName());
			return htmlUserBean;
		}
		return null;
	}

	//前台a标签跳转路径携带数据
	@RequestMapping("/aUserMassage")
	public String userRequest(HttpServletRequest request, String userBean)
	{
		dyfHtmlUserBean = dbs.userSelectAllRepeatTwo(userBean);
		String userName = String.valueOf(request.getSession().getAttribute("userName"));
		if (userName != null && userName != "")
		{
			if (dyfHtmlUserBean != null)
			{
				request.setAttribute("userBean", dyfHtmlUserBean);
				request.getSession().setAttribute("userPhone", dyfHtmlUserBean.getUserPhoneNumber());
			}
			return "front/account";
		} else
		{
			return "front/home";
		}

	}

	//页面跳转
	@RequestMapping("/HtmlUserInforMation")
	protected String HtmlUserInforMation(HttpServletRequest request)
	{
		if (dyfHtmlUserBean != null)
		{
			request.setAttribute("userBean", dyfHtmlUserBean);
		}
		return "/front/DyfMyInforMation";
	}


	//头像上传
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	protected JSONObject uploadFile(MultipartFile file, HttpServletRequest request) throws IOException
	{
		String path = "img/";
		String url = request.getRequestURI();
		JSONObject resObj = new JSONObject();
		String basePath = request.getServletContext().getRealPath("/");
		String str[] = basePath.split("\\\\");
		String imgSrc = "";
		for (int i = 0; i < str.length - 1; i++)
		{
			if (i == 0)
			{
				imgSrc += str[i];
			} else
			{
				imgSrc += ("\\\\" + str[i]);
			}
		}
		//将图片存放于工程目录下的img文件夹内
		try
		{
			file.transferTo(new File(imgSrc + "\\resources\\static\\img\\" + file.getOriginalFilename()));


		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			resObj.put("msg", "fail");
		}
		//用户更改头像sql语句
		int num = dbs.updateImg(file.getOriginalFilename(), request.getSession().getAttribute("userPhone").toString());
		if (num > 0)
		{
			resObj.put("msg", "success");
			resObj.put("url", file.getOriginalFilename());
		}
		return resObj;
	}

	//用户信息修改
	@RequestMapping("/setUserInforMation")
	@ResponseBody
	protected String setUserInforMation(HttpServletRequest request, String userHome, String hid1, String hid2, String userPhone, String userCompany, String hid3, String hid4, String sex)
	{
		DyfHtmlUserBean userBean = new DyfHtmlUserBean();
		userBean.setUserPhoneNumber(userPhone);
		userBean.setUserSex(sex);
		userBean.setUserHome(userHome);
		userBean.setUserHomeLongitude(hid1);
		userBean.setUserHomeLatitude(hid2);
		userBean.setUserCompany(userCompany);
		userBean.setUserCompanyLongitude(hid3);
		userBean.setUserCompanyLatitude(hid4);
		int num = dbs.updateUserInforMation(userBean);
		if (num > 0)
		{
			return "success";
		}

		return "fail";
	}

	//用户信息修改
	@RequestMapping("/twoDisMensional")
	public String twoDisMensional(HttpServletRequest request)
	{
		String userPhone = request.getSession().getAttribute("userPhone").toString();
		DyfHtmlUserBean bean = dbs.userSelectAllRepeatTwo(userPhone);
		request.setAttribute("userBean", bean);
		return "/front/DyfTwoDimensional";
	}

	//用户消费明细
	@RequestMapping("/findSaveMoney")
	@ResponseBody
	public TableBean findSaveMoney(HttpServletRequest request, String value, String page)
	{
		TableBean tableBean = new TableBean();
		String phoneNum = request.getSession().getAttribute("userPhone").toString();
		if (null == page)
		{
			page = "1";
		}
		Integer startPage = (Integer.valueOf(page) - 1) * 5;
		List<DyfAliPayBean>list = dbs.selectSaveMoney(phoneNum, value, startPage, pageSize);
		/*for (DyfAliPayBean dyfAliPayBean : list)
		{
			if (dyfAliPayBean.getNowDate()!=null){
				dyfAliPayBean.setNowDate(sdf.format());
			}
		}*/
		tableBean.setData(list);
		Integer count = dbs.selectSaveMoneyCount(phoneNum, value, startPage, pageSize);
		tableBean.setCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
		return tableBean;
	}
}
