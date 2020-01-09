package com.bus.controller;

import com.bus.dao.XhaRouteConfigurationMapper;
import com.bus.javabean.*;
import com.bus.service.CnmAdvertisementService;
import com.bus.service.CnmNewsBulletinService;
import com.bus.service.CynMangeUserService;
import com.bus.service.XhaRouteConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理用户管控操作器
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
	@Resource
	private XhaRouteConfigurationMapper routeMapper;
	@Resource
	private XhaRouteConfigurationService routeService;

	/**
	 * 跳转路径
	 * by蔡远南
	 */
	@RequestMapping("/web/{url}")
	public String matchUrl(@PathVariable(value = "url") String path)
	{
		return "/backjsp/" + path;
	}

	@RequestMapping("/web2/{url}")
	public String matchUrl2(@PathVariable(value = "url") String path)
	{
		return "/front/" + path;
	}

	@Resource
	CnmAdvertisementService cnmAdvertisementService;
	@Resource
	CnmNewsBulletinService cnmNewsBulletinService;

	@RequestMapping("/welcome")
	public ModelAndView treeUrl()
	{

		ModelAndView modelAndView = new ModelAndView();
		List<CnmAdvertisementBean> tableList = cnmAdvertisementService.getfindAdvertisement(null);
		int x = 1 + (int) (Math.random() * (tableList.size()));
		if (x <= 1)
		{
			x = x + 1;
		}
		System.out.println("进入前端欢迎页面" + x);
		List nList = tableList.subList(x - 1, x);
		modelAndView.addObject("tableLists", tableList);//轮
		modelAndView.addObject("tableListss", nList);//飘

		List<CnmNewsBulletinBean> NewsBulletinList = cnmNewsBulletinService.getfindNewsBulletinInf();
		modelAndView.addObject("NewsBulletinLists", NewsBulletinList);//新闻栏
		modelAndView.setViewName("front/home");
		return modelAndView;
	}

	/**
	 * 线路查询页面
	 * by连晨诚
	 *
	 * @return
	 */
	@RequestMapping("/findRoute")
	public ModelAndView findRoute()
	{

		ModelAndView modelAndView = new ModelAndView();
		//		路线查询
		//		List<XhaRouteOrderBean> routeList = routeMapper.queryAllRoute();
		//		for (XhaRouteOrderBean routeOrder : routeList) {
		//			System.out.println(routeOrder.getRouteName()+"__---");
		//		}
		//		modelAndView.addObject("routeLists", routeList);

		List<XhaStationBean> stationList = routeService.queryAllStation();//查询所有站点的信息
		modelAndView.addObject("stationList", stationList);
		modelAndView.setViewName("front/findRoute");

		return modelAndView;
	}

	/**
	 * 站点查询页面
	 * by连晨诚
	 *
	 * @return
	 */
	@RequestMapping("/findStation")
	public ModelAndView findStation()
	{
		ModelAndView modelAndView = new ModelAndView();
		List<XhaStationBean> stationList = routeService.queryAllStation();//查询所有站点的信息
		modelAndView.addObject("stationList", stationList);
		modelAndView.setViewName("front/findStation");
		return modelAndView;
	}

	@RequestMapping("/findbus")
	public ModelAndView findbus()
	{
		ModelAndView modelAndView = new ModelAndView();
		List lists = new ArrayList();
		lists.add(0);
		lists.add(1);
		lists.add(2);
		lists.add(3);
		lists.add(4);

		List<XhaRouteOrderBean> routeList = routeMapper.queryAllRoute();
		modelAndView.addObject("routeLists", routeList);//线路


		modelAndView.addObject("Listss", lists);
		List<XhaStationBean> stationList = routeService.queryAllStation();//查询所有站点的信息
		modelAndView.addObject("stationList", stationList);
		modelAndView.setViewName("front/findbus");
		return modelAndView;
	}

	/**
	 * 页面
	 * by连晨诚
	 *
	 * @return
	 */
	@RequestMapping("/account")
	public String toAccount()
	{
		System.out.println("进入个人中心页面");
		return "front/account";
	}

	/**
	 * 管理员登录
	 * by蔡远南
	 */
	@RequestMapping("/mangeUserLogin")
	//	@CynLog(operationType = "管理员登录",operationName = "操作人")
	public String mangeUserLogin(HttpServletRequest request)
	{
		String name = request.getParameter("username");
		CynMangeUserBean cynMangeUserBean = cynMangeUserService.finMangeUser(name);
		if (null != cynMangeUserBean)
		{
			String pass = request.getParameter("password");
			if (pass.equals(cynMangeUserBean.getPassword()))
			{
				request.setAttribute("msg", "登录成功！");
				request.getSession().setAttribute("user", cynMangeUserBean);
				//获取所有的二级菜单
				List<CynMenuBean> menuList = cynMangeUserService.findMenuAllByUserName(name);
				menuMap = getMap(menuList);
				request.setAttribute("menus", menuMap);

				return "backjsp/mange";
			} else
			{
				request.setAttribute("msg", "账号或密码有误，请重新输入！");
			}
		} else
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

	/**
	 * 日志操作
	 */
	@RequestMapping("/mangelog")
	@ResponseBody
	public CynPageSearch mangeLog(HttpServletRequest req)
	{
		String limit = req.getParameter("limit");
		int limInt = Integer.valueOf(limit);
		String page = req.getParameter("page");
		int pageInt = Integer.valueOf(page);
		String name = req.getParameter("username");
		CynLogSelectBean logSelectBean = new CynLogSelectBean();
		logSelectBean.setActionName(name);
		logSelectBean.setCount(pageInt * limInt);
		logSelectBean.setStart((pageInt - 1) * limInt);
		List listUsers = cynMangeUserService.findLogAllByOperatorName(logSelectBean);

		//获取到总页数
		int count = cynMangeUserService.findLogAllCountByOperatorName(logSelectBean);
		CynPageSearch ps = new CynPageSearch();
		ps.setCount(count);
		ps.setCode(0);
		ps.setData(listUsers);
		return ps;
	}

	/**
	 * 日志删除
	 */
	@RequestMapping("/delLog")
	@ResponseBody
	public String delLog(String logId)
	{
		int res = cynMangeUserService.deleteLogInf(Integer.valueOf(logId));
		if (res > 0)
		{
			return "删除成功！";
		}
		return "删除失败！";
	}

	@RequestMapping("/exit")
	@ResponseBody
	public String mangeExitMethod(HttpServletRequest req)
	{
		String msg = "";
		//清空session域
		HttpSession ht = req.getSession(true);
		ht.removeAttribute("user");
		//跳转回登录界面
		msg = "退出成功!";
		return msg;
	}

}
