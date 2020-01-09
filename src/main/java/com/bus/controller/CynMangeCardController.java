package com.bus.controller;

import com.bus.javabean.CynCardBean;
import com.bus.javabean.CynCardSelectBean;
import com.bus.javabean.CynPageSearch;
import com.bus.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理卡片管控操作器
 * by蔡远南
 */
@Controller
public class CynMangeCardController
{
	@Resource
	CynMangeCardService cynMangeCardService;


	@RequestMapping("/mangecardshow")
	@ResponseBody
	public CynPageSearch mangeExitMethod(HttpServletRequest req){
		String limit = req.getParameter("limit");
		int limInt = Integer.valueOf(limit);
		String page = req.getParameter("page");
		int pageInt = Integer.valueOf(page);
		String stateName=req.getParameter("stateName");
		CynCardSelectBean cynCardSelectBean=new CynCardSelectBean();
		cynCardSelectBean.setStateName(stateName);
		cynCardSelectBean.setCount(pageInt * limInt);
		cynCardSelectBean.setStart((pageInt - 1) * limInt);
		List listCards=cynMangeCardService.getAllCard(cynCardSelectBean);

		int count =cynMangeCardService.getAllCardCount(cynCardSelectBean);
		CynPageSearch ps = new CynPageSearch();
		ps.setCount(count);
		ps.setCode(0);
		ps.setData(listCards);
		return ps;
	}

	@RequestMapping("/delCard")
	@ResponseBody
	public String delCard(String id){
		System.out.println(id);
		int bol=cynMangeCardService.delCardById(Integer.valueOf(id));
		if(bol>0){
			return "删除成功！";
		}
		return "删除失败";
	}

	@RequestMapping("/upCard")
	@ResponseBody
	public String upCard(String id,String newStateName){

		int stateId=cynMangeCardService.getStateId(newStateName);

		int bol=cynMangeCardService.updateCardById(stateId,Integer.valueOf(id));
		if(bol>0){
			return "success";
		}
		return "修改失败!";
	}
	@RequestMapping("/addCard")
	@ResponseBody
	public String addCard(String num){
		System.out.println(num);

		int numInt=Integer.valueOf(num);

		//1.获取到卡片的数量
		int count=cynMangeCardService.getAllCardCount(null);

		List<CynCardBean> cynCardBeans=new ArrayList<>();

		for (int i = 1; i <= numInt; i++)
		{
			CynCardBean cynCardBean=new CynCardBean();
			cynCardBean.setCardNum(String.valueOf(count+i));
			cynCardBeans.add(cynCardBean);
			cynCardBean=null;
		}

		int bol=cynMangeCardService.batchInsertCard(cynCardBeans);
		if(bol>0){
			return "success";
		}
		return "批量增加失败!";
	}

}
