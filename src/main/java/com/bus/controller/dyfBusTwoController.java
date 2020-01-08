package com.bus.controller;

import com.bus.factoryBean.DyfMessageSend;
import com.bus.javabean.DyfHtmlUserBean;
import com.bus.javabean.DyfMessageBean;
import com.bus.service.DyfBusConfigurationService;
import com.bus.service.dyfBusTwoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@Controller
public class dyfBusTwoController
{
	@Resource
	private dyfBusTwoService dbs;
	@RequestMapping("/front/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		System.out.println(path);
		return "front/" + path;
	}
	//用户账号密码登入
	@RequestMapping("/htmlLogin")
	@ResponseBody
	public DyfHtmlUserBean htmlLogin(HttpServletRequest request,String userName,String userPs)
	{
		DyfHtmlUserBean dyfHtmlUserBean = dbs.userLogin(userName,userPs);
		if (dyfHtmlUserBean!=null){
			request.getSession().setAttribute("userName",dyfHtmlUserBean.getUserName());
			request.getSession().setAttribute("userPhone",dyfHtmlUserBean.getUserPhoneNumber());
			return dyfHtmlUserBean;
		}
		return null;
	}
	//手机验证码发送
	@RequestMapping("/userRegisetMessage")
	@ResponseBody
	public DyfMessageBean userRegisetMessage(HttpServletRequest request,String phoneNum,String loginOrRegister){
		DyfHtmlUserBean repairUserPhoneNumber = dbs.userSelectAllRepeatTwo(phoneNum);
		DyfMessageBean dyfMessageBean = new DyfMessageBean();
		if (repairUserPhoneNumber==null){
			 dyfMessageBean = new DyfMessageSend().messageReturn(phoneNum);
			if (dyfMessageBean!=null){
				return dyfMessageBean;
			}
		}else {
			dyfMessageBean.setFee("repair");
			return dyfMessageBean;
		}

		return null;
	}
	//登入时手机验证码发送
	@RequestMapping("/userMsgLogin")
	@ResponseBody
	public DyfMessageBean userMsgLogin(HttpServletRequest request,String phoneNum,String loginOrRegister)
	{
		DyfHtmlUserBean htmlUserBean = dbs.userSelectAllRepeatTwo(phoneNum);
		DyfMessageBean dyfMessageBean;
		if (htmlUserBean!=null){
			dyfMessageBean = new DyfMessageSend().messageReturn(phoneNum);
			if (dyfMessageBean!=null){
				return dyfMessageBean;
			}
		}
		return null;
	}
	//验证码已验证，开始注册操作
	@RequestMapping("/userRegisetUser")
	@ResponseBody
	public String userRegisetAddUser(HttpServletRequest request,String userName,String passWord1,String phoneNum)
	{
		int num = dbs.userRegisterInto(userName,passWord1,phoneNum);
		if (num>0){
			return "success";
		}
		return "fail";
	}
	//手机验证码登入
	@RequestMapping("/userLoginMsg")
	@ResponseBody
	public DyfHtmlUserBean userMsgLogin(HttpServletRequest request,String userLoginView){
		DyfHtmlUserBean htmlUserBean = dbs.userSelectAllRepeatTwo(userLoginView);
		if (htmlUserBean!=null){
			request.getSession().setAttribute("userName",htmlUserBean.getUserName());
			return htmlUserBean;
		}
		return null;
	}
	//前台a标签跳转路径携带数据
	@RequestMapping("/aUserMassage")
	public String userRequest(HttpServletRequest request,String userBean){
		System.out.println(userBean);
		DyfHtmlUserBean dyfHtmlUserBean = dbs.userSelectAllRepeatTwo(userBean);
		String userName = String.valueOf(request.getSession().getAttribute("userName"));
		if (userName!=null&&userName!=""){
			if (dyfHtmlUserBean!=null){
				request.setAttribute("userBean",dyfHtmlUserBean);
			}
			return  "front/account";
		}else {
			return "front/home";
		}


	}

	}
