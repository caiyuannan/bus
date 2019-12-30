package com.bus.service;


import com.bus.javabean.CnmCooperativeBean;
import com.bus.javabean.CnmCooperativeWhere;
import com.bus.mapper.CnmCooperativeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CnmCooperativeService
{
	@Resource
	private CnmCooperativeMapper cnmCooperativeMapper;

	CnmCooperativeWhere cnmCooperativeWhere=new CnmCooperativeWhere();

	//CnmCooperativeBean表
	public List<CnmCooperativeBean> gettable(String cooperativeName,String cooperativeArea,String cooperativeType, int page, int limit)
	{

		cnmCooperativeWhere.setCooperativeName(cooperativeName);
		cnmCooperativeWhere.setCooperativeArea(cooperativeArea);
		cnmCooperativeWhere.setCooperativeType(cooperativeType);
		cnmCooperativeWhere.setLimit(page*limit);
		cnmCooperativeWhere.setPage((page-1)*limit);
		System.out.println(cnmCooperativeWhere.getLimit()+"qhw"+cnmCooperativeWhere.getPage());
		List<CnmCooperativeBean> tableList = cnmCooperativeMapper.findCooperativeTable(cnmCooperativeWhere);
		if (tableList != null && tableList.size() > 0)
		{
			return tableList;
		}
		return tableList;
	}

//	CnmCooperativeBean总条数
	public int getTableCount()
	{
		int count = cnmCooperativeMapper.findCooperativeTableCount(cnmCooperativeWhere);
		return count;
	}

	//删除CnmCooperativeBean表
	public int getDeleteCooperativeTable(CnmCooperativeBean cnmCooperativeBean)
	{
		int num = cnmCooperativeMapper.deleteCooperativeTable(cnmCooperativeBean.getCooperativeId());
		return num;
	}

	//添加CnmCooperativeBean表
	public int getaddCooperativeTable(CnmCooperativeBean cnmCooperativeBean)
	{
		int num = cnmCooperativeMapper.addCooperativeTable(cnmCooperativeBean);
		return num;
	}

	//修改CnmCooperativeBean表
	public int getupdateCooperativeTable(CnmCooperativeBean cnmCooperativeBean){
		int num=cnmCooperativeMapper.updateCooperativeTable(cnmCooperativeBean);
		return num;
	}
	//获取CnmCooperativeBean的合作商类型下拉框
	public List<CnmCooperativeBean> getCooperativeType(){

		List<CnmCooperativeBean> tableList=cnmCooperativeMapper.findCooperativeType();
		return tableList;
	}
	//修改CnmCooperativeBean表的状态
	public int getUpdateCooperativestate(CnmCooperativeBean cnmCooperativeBean){
		int num=cnmCooperativeMapper.updateCooperativestate(cnmCooperativeBean);
		return num;
	}

	//查询CnmCooperativeBean表的状态
	public int getfindCooperativeState(String stateName){
		int stateID=cnmCooperativeMapper.findCooperativeState(stateName);
		return stateID;
	}

}
