package com.bus.controller;

import com.bus.javabean.CynMangeUserBean;
import com.bus.javabean.CynMenuBean;
import com.bus.service.CynMangeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理管控操作器
 * by蔡远南
 */

@Controller
public class CynMangeUserController
{
	private Map<String, List<CynMenuBean>> menuMap;
	/**
	 * 调用MangeUser服务类
	 */
	@Resource
	CynMangeUserService cynMangeUserService;

	/**
	 * 跳转路径
	 * by蔡远南
	 */
	@RequestMapping("/web/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		return "/backjsp/"+path;
	}
	@RequestMapping("/web2/{url}")
	public String matchUrl2(@PathVariable(value = "url") String path)
	{
		return "/front/"+path;
	}

	/**
	 * 前端首页
	 * by连晨诚
	 * @return
	 */
	@RequestMapping("/welcome")
	public String toHome(){
		System.out.println("进入前端欢迎页面");
		return "front/home";
	}

	/**
	 * 管理员登录
	 * by蔡远南
	 */
	@RequestMapping("/mangeUserLogin")
	public String login(HttpServletRequest request)
	{
		String name = request.getParameter("username");
		CynMangeUserBean cynMangeUserBean=cynMangeUserService.finMangeUser(name);
		if(null != cynMangeUserBean){
			String pass = request.getParameter("password");
			if (pass.equals(cynMangeUserBean.getPassword())){
				request.setAttribute("msg", "登录成功！");
				request.getSession().setAttribute("user", cynMangeUserBean);
				//获取所有的二级菜单
				List<CynMenuBean> menuList= cynMangeUserService.findMenuAllByUserName(name);
				menuMap = getMap(menuList);
				request.setAttribute("menus", menuMap);

				return "backjsp/mange";
			} else
			{
				request.setAttribute("msg", "账号或密码有误，请重新输入！");
			}
		}else
		{
			request.setAttribute("msg", "账号不存在！");
		}
		return "backjsp/mangeuserlogin";
	}
	/**
	 * 获取菜单
	 */
	public Map<String, List<CynMenuBean>> getMap(List<CynMenuBean> menus)
	{
		menuMap = new HashMap<>();
		for (int i = 0; i < menus.size(); i++)
		{
			CynMenuBean menu = menus.get(i);
			if (menuMap.containsKey(menu.getParentMenuName()))
			{
				List<CynMenuBean> list1 = menuMap.get(menu.getParentMenuName());
				list1.add(menu);
			} else
			{
				List<CynMenuBean> list2 = new ArrayList<>();
				list2.add(menu);
				menuMap.put(menu.getParentMenuName(), list2);
			}
		}
		return menuMap;
	}

}
