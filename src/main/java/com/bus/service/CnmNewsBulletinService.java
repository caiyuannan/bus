package com.bus.service;


import com.bus.javabean.CnmCooperativeBean;
import com.bus.javabean.CnmCooperativeWhere;
import com.bus.javabean.CnmNewsBulletinBean;
import com.bus.javabean.CnmNewsBulletinWhere;
import com.bus.mapper.CnmCooperativeMapper;
import com.bus.mapper.CnmNewsBulletinMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CnmNewsBulletinService
{
	@Resource
	private CnmNewsBulletinMapper cnmNewsBulletinMapper;

	CnmNewsBulletinWhere cnmNewsBulletinWhere=new CnmNewsBulletinWhere();

	//CnmNewsBulletinBean表
	public List<CnmNewsBulletinBean> gettable(String newsBulletinTitle, String newsBulletinType, String newsBulletinTime, int page, int limit)
	{

		cnmNewsBulletinWhere.setNewsBulletinTitle(newsBulletinTitle);
		cnmNewsBulletinWhere.setNewsBulletinTime(newsBulletinTime);
		cnmNewsBulletinWhere.setNewsBulletinType(newsBulletinType);
		cnmNewsBulletinWhere.setLimit(page*limit);
		cnmNewsBulletinWhere.setPage((page-1)*limit);
		System.out.println(cnmNewsBulletinWhere.getLimit()+"qhw"+cnmNewsBulletinWhere.getPage());
		List<CnmNewsBulletinBean> tableList = cnmNewsBulletinMapper.findNewsBulletinTable(cnmNewsBulletinWhere);
		if (tableList != null && tableList.size() > 0)
		{
			return tableList;
		}
		return tableList;
	}

//	CnmNewsBulletinBean总条数
	public int getTableCount()
	{
		int count = cnmNewsBulletinMapper.findNewsBulletinTableCount(cnmNewsBulletinWhere);
		return count;
	}

	//删除CnmNewsBulletinBean表
	public int getDeleteNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean)
	{
		int num = cnmNewsBulletinMapper.deleteNewsBulletinTable(cnmNewsBulletinBean.getNewsBulletinId());
		return num;
	}

	//添加CnmNewsBulletinBean表
	public int getaddNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean)
	{
		int num = cnmNewsBulletinMapper.addNewsBulletinTable(cnmNewsBulletinBean);
		return num;
	}

	//修改CnmNewsBulletinBean表
	public int getupdateNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean){
		int num=cnmNewsBulletinMapper.updateNewsBulletinTable(cnmNewsBulletinBean);
		return num;
	}
	//获取CnmNewsBulletinBean的合作商类型下拉框
	public List<CnmNewsBulletinBean> getNewsBulletinType(){

		List<CnmNewsBulletinBean> tableList=cnmNewsBulletinMapper.findNewsBulletinType();
		return tableList;
	}
	//修改CnmCooperativeBean表的状态
	public int getUpdateCooperativestate(CnmNewsBulletinBean cnmNewsBulletinBean){
		int num=cnmNewsBulletinMapper.updateNewsBulletinState(cnmNewsBulletinBean);
		return num;
	}

	//查询CnmNewsBulletinBean表的状态
	public int getfindNewsBulletinState(String stateName){
		int stateID=cnmNewsBulletinMapper.findNewsBulletinState(stateName);
		return stateID;
	}

}
