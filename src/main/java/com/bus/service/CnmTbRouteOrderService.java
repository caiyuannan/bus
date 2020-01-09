package com.bus.service;


import com.bus.dao.CnmTbRouteOrderMapper;
import com.bus.javabean.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CnmTbRouteOrderService
{
	@Resource
	CnmTbRouteOrderMapper cnmTbRouteOrderMapper;
//	private CnmAdvertisementMapper cnmAdvertisementMapper;
//
//	CnmAdvertisementWhere cnmAdvertisementWhere=new CnmAdvertisementWhere();
//
//	//CnmAdvertisementBean表
//	public List<CnmAdvertisementBean> gettable(String cooperativeName, String advertisingType, String stateName, int page, int limit)
//	{
//
//		cnmAdvertisementWhere.setCooperativeName(cooperativeName);
//		cnmAdvertisementWhere.setAdvertisingType(advertisingType);
//		cnmAdvertisementWhere.setStateName(stateName);
//		cnmAdvertisementWhere.setLimit(page*limit);
//		cnmAdvertisementWhere.setPage((page-1)*limit);
//		System.out.println(cnmAdvertisementWhere.getLimit()+"qhw"+cnmAdvertisementWhere.getPage());
//		List<CnmAdvertisementBean> tableList = cnmAdvertisementMapper.findAdvertisementTable(cnmAdvertisementWhere);
//		if (tableList != null && tableList.size() > 0)
//		{
//			return tableList;
//		}
//		return tableList;
//	}
//
////	CnmAdvertisementBean总条数
//	public int getTableCount()
//	{
//		int count = cnmAdvertisementMapper.findAdvertisementTableCount(cnmAdvertisementWhere);
//		return count;
//	}
//
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
    //cnm通过路线名查询路线名
	public CnmFindStationWhere getQueryStationByRouteOrderName(CnmFindStationWhere cnmFindStationWhere2){
		CnmFindStationWhere cnmFindStationWhere=cnmTbRouteOrderMapper.queryStationByRouteOrderName(cnmFindStationWhere2);
		return cnmFindStationWhere;
	}

	public List<XhaRouteOrderBean> getQueryStationInfoBySE(CnmFindStationWhere cnmFindStationWhere){
		List<XhaRouteOrderBean>RouteOrderBeanLis=cnmTbRouteOrderMapper.queryStationInfoBySE(cnmFindStationWhere);
	    return RouteOrderBeanLis;
	}
	//	cnm通过站点名查询站点信息
	public XhaStationBean queryStation(String stationName)
	{
		XhaStationBean xhaStationBean=cnmTbRouteOrderMapper.queryStation(stationName);
		return xhaStationBean;
	}

    //查询时间段在线车
	public List<DyfBusShfitBean>  getShifSum(CnmBusWhere cnmBusWhere)
	{
		List<DyfBusShfitBean>DyfBusShfitBean=cnmTbRouteOrderMapper.ShifSum(cnmBusWhere);
		return DyfBusShfitBean;
	}

	//通过线路名查询线路信息上
	public List<XhaRouteOrderBean>getStationByRouteOrderName(CnmBusWhere cnmBusWhere){

		List<XhaRouteOrderBean>XhaRouteOrderBean=cnmTbRouteOrderMapper.StationByRouteOrderName(cnmBusWhere);
		return XhaRouteOrderBean;
	}


}
