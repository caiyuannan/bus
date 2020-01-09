package com.bus.service;


import com.bus.dao.CnmSimulationMapper;
import com.bus.javabean.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CnmSimulationBeanService
{
	@Resource
	private CnmSimulationMapper cnmSimulationMapper;

	CnmSimulationWhere cnmSimulationWhere=new CnmSimulationWhere();

	//CnmAdvertisementBean表
	public List<CnmSimulationBean> gettable(String stationName, String simulationTime,  int page, int limit)
	{

		cnmSimulationWhere.setSimulationTime(simulationTime);
		cnmSimulationWhere.setStationName(stationName);
		cnmSimulationWhere.setPage(Integer.valueOf(page-1)*Integer.valueOf(limit));
		cnmSimulationWhere.setLimit(Integer.valueOf(limit));
		System.out.println(cnmSimulationWhere.getPage()+"--"+cnmSimulationWhere.getLimit());
		List<CnmSimulationBean> tableList = cnmSimulationMapper.findSimulationTable(cnmSimulationWhere);
		if (tableList != null && tableList.size() > 0)
		{
			return tableList;
		}
		return tableList;
	}

//	CnmAdvertisementBean总条数
	public int getTableCount()
	{
		int count = cnmSimulationMapper.findSimulationTableCount(cnmSimulationWhere);
		return count;
	}


	//查询时间段
	public List<CnmSimulationBean> getFindSimulationInf(){

		List<CnmSimulationBean>list=cnmSimulationMapper.findSimulationInf();
		return list;
	}

//	//删除CnmAdvertisementBean表
//	public int getDeleteAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean)
//	{
//		int num = cnmAdvertisementMapper.deleteAdvertisementTable(cnmAdvertisementBean.getAdvertisementId());
//		return num;
//	}
//
//	//添加CnmAdvertisementBean表
//	public int getaddAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean)
//	{
//		int num = cnmAdvertisementMapper.addAdvertisementTable(cnmAdvertisementBean);
//		return num;
//	}
//	////用合作商名查询合作商的
//	public CnmCooperativeBean getfindCooperativeInf(String cooperativeName)
//	{
//		CnmCooperativeBean cnmCooperativeBean = cnmAdvertisementMapper.findCooperativeInf(cooperativeName);
//		return cnmCooperativeBean;
//	}
//	//	查询广告是否有这条记录
//	public CnmAdvertisementBean getfindAdvertisementInf(CnmAdvertisementBean cnmAdvertisementBean)
//	{
//		CnmAdvertisementBean cnmAdvertisementBean2 = cnmAdvertisementMapper.findAdvertisementInf(cnmAdvertisementBean);
//		return cnmAdvertisementBean2;
//	}
//
//
//	//修改CnmAdvertisementBean表
//	public int getupdateAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean){
//		int num=cnmAdvertisementMapper.updateAdvertisementTable(cnmAdvertisementBean);
//		return num;
//	}
//	//获取CnmAdvertisementBean的合作商类型下拉框
//	public List<CnmAdvertisementBean> getAdvertisementType(){
//
//		List<CnmAdvertisementBean> tableList=cnmAdvertisementMapper.findAdvertisementType();
//		return tableList;
//	}
//	//修改CnmAdvertisementBean表的状态
//	public int getUpdateAdvertisementState(CnmAdvertisementBean cnmAdvertisementBean){
//		int num=cnmAdvertisementMapper.updateAdvertisementState(cnmAdvertisementBean);
//		return num;
//	}
//
//	//查询CnmAdvertisementBean表的状态
//	public int getfindAdvertisementState(String stateName){
//		int stateID=cnmAdvertisementMapper.findAdvertisementState(stateName);
//		return stateID;
//	}
//    //	查询类型为轮播广告的
//	public List<CnmAdvertisementBean>  getfindAdvertisement(CnmAdvertisementBean cnmAdvertisementBean){
//		List<CnmAdvertisementBean> CnmAdvertisementBean=cnmAdvertisementMapper.findAdvertisement(cnmAdvertisementBean);
//		return CnmAdvertisementBean;
//	}
}
