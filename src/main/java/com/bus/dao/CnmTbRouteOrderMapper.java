package com.bus.dao;


import com.bus.javabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CnmTbRouteOrderMapper
 {
		//cnm通过路线名查询路线名
		public CnmFindStationWhere queryStationByRouteOrderName(CnmFindStationWhere CnmFindStationWhere);

		//通过起始点的站号查询经过的站信息
	    public List<XhaRouteOrderBean> queryStationInfoBySE(CnmFindStationWhere CnmFindStationWhere);

	 //	cnm通过站点名查询站点信息
	 public XhaStationBean queryStation(@Param("stationName") String stationName);

//	 查询时间段在线车
	 public List<DyfBusShfitBean>  ShifSum(CnmBusWhere cnmBusWhere);

	 //通过线路名查询线路信息上
	 public List<XhaRouteOrderBean>StationByRouteOrderName(CnmBusWhere cnmBusWhere);




//		//CnmAdvertisementBean表格修改
//		public int updateAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean);
//		//CnmAdvertisementBean获取合作商类型
//		public List<CnmAdvertisementBean> findAdvertisementType();
//
//		//修改CnmAdvertisementBean表的状态
//	   public int updateAdvertisementState(CnmAdvertisementBean cnmAdvertisementBean);
//	 //查询CnmAdvertisementBean表的状态
//	   public int findAdvertisementState(String stateName);


 }

