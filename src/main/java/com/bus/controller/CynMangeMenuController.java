package com.bus.controller;

import com.bus.aoplog.CynLog;
import com.bus.dao.CynMangeMenuMapper;
import com.bus.javabean.*;
import com.bus.service.CynMangeMenuService;
import com.bus.service.CynMangeUserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理用户管控操作器
 * by蔡远南
 */

@Controller
public class CynMangeMenuController
{

	@Resource
	CynMangeMenuService cynMangeMenuService;

	@RequestMapping(value = "/mangemenushow")
	public String getRoleAll(HttpServletRequest request)
	{
		List<CynRoleBean> listRoles = cynMangeMenuService.findRoleAll();
		request.setAttribute("listRoles", listRoles);
		return "backjsp/mangemenu";
	}

	@RequestMapping(value = "/mangemenu")
	@ResponseBody
	public List<CynMenuActionBean> getMenuAll()
	{
		List<CynMenuActionBean> listOne = cynMangeMenuService.MenuOne(new CynRoleBean());
		return listOne;
	}

	@RequestMapping(value = "/modify")
	@ResponseBody
	public JSONObject getMenuModify(String roleId, String checkedData)
	{
		String msg="";
		Gson gson=new Gson();

		//将获取到的选择数据转成list
		List<CynMenuActionBean> ps = gson.fromJson(checkedData, new TypeToken<List<CynMenuActionBean>>(){}.getType());
		//获取到一级和二级的所有id
		List<Integer> idAll=new ArrayList<>();
		for (int i = 0; i < ps.size(); i++)
		{
			//将一级id添加进去
			idAll.add(ps.get(i).getId());
			//将二级id添加进去
			List<CynMenuActionBean> children=ps.get(i).getChildren();
			for (CynMenuActionBean child : children)
			{
				idAll.add(child.getId());
			}
		}
		idAll=removeDuplicate(idAll);


		//根据roleid获取所有的menuId
		CynRoleBean role=new CynRoleBean();
		role.setRoleId(Integer.valueOf(roleId));
		List<CynMenuActionBean> list2=cynMangeMenuService.getMenuIdByRoleID(role);
		boolean bol=JudgeListIsRight(idAll,list2);
		if(bol){
			msg="数据一致，无需分配权限";
		}else{

			List<CynRoleMenuBean> list=cynMangeMenuService.judeMenuRoleIsNull(role);
			if(list.size()>0){
				int del=cynMangeMenuService.deleteRoleMenuByRoleid(role);
				if(del>0){
					int res=cynMangeMenuService.addAllRoleMenuRelation(roleId,idAll);
					if(res>0){
						msg="分配成功";
					}else{
						msg="分配失败";
					}
				}
			}else{
				int res=cynMangeMenuService.addAllRoleMenuRelation(roleId,idAll);
				if(res>0){
					msg="分配成功";
				}else{
					msg="分配失败";
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		return json;
	}

	@RequestMapping(value = "/changedemo")
	@ResponseBody
	@CynLog(operationType = "管理员权限分配",operationName = "操作人")
	public List<CynMenuActionBean> getMenuSelectAll(String roleId)
	{
		CynRoleBean role = new CynRoleBean();
		//获取到角色所有的list
		List<CynMenuActionBean> listAll = cynMangeMenuService.MenuOne(role);
		//获取到角色roleid
		System.out.println("roleId="+roleId);
		if (Integer.valueOf(roleId) != 0)
		{
			role.setRoleId(Integer.valueOf(roleId));
			//获取到该角色的list
			List<CynMenuActionBean> listRole = cynMangeMenuService.getOne(role);
			for (int i = 0; i < listAll.size(); i++)
			{
				CynMenuActionBean menuActionBean = listAll.get(i);
				for (int j = 0; j < listRole.size(); j++)
				{
					for (int k = 0; k < menuActionBean.getChildren().size(); k++)
					{
						for (int l = 0; l < listRole.get(j).getChildren().size(); l++)
						{
							if (menuActionBean.getChildren().get(k).getTitle().equals(listRole.get(j).getChildren().get(l).getTitle()))
							{
								//给二级勾选
								menuActionBean.getChildren().get(k).setChecked(true);
							}
						}
					}
				}
			}
		}
		return listAll;
	}

	/**
	 * 两个list判断是否一致
	 */
	public boolean JudgeListIsRight(List<Integer> list1,List<CynMenuActionBean> list2){
		List<Integer> list3=new ArrayList<>();
		for (int i = 0; i < list2.size(); i++)
		{
			list3.add(list2.get(i).getId());
		}

		if(list1.size() == list3.size() && list1.containsAll(list3)){
			return true;
		}
		return false;
	}

	/**
	 * 去除重复的list
	 */
	public List removeDuplicate(List list){
		List listTemp = new ArrayList();
		for(int i=0;i<list.size();i++){
			if(!listTemp.contains(list.get(i))){
				listTemp.add(list.get(i));
			}
		}
		return listTemp;
	}

}
