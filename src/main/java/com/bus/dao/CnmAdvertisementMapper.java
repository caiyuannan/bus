package com.bus.dao;


import com.bus.javabean.CnmAdvertisementBean;
import com.bus.javabean.CnmAdvertisementWhere;
import com.bus.javabean.CnmCooperativeBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CnmAdvertisementMapper
 {
		//获取所有的CnmAdvertisementBean
		public List<CnmAdvertisementBean> findAdvertisementTable(CnmAdvertisementWhere cnmAdvertisementWhere);

		//获取所有的CnmAdvertisementBean表格总条数
		public int findAdvertisementTableCount(CnmAdvertisementWhere cnmAdvertisementWhere);

		//CnmAdvertisementBean表格删除
		public int deleteAdvertisementTable(int cooperativeId);

		//CnmAdvertisementBean表格添加
		public int addAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean);
		//用合作商名查询合作商的
	    public CnmCooperativeBean findCooperativeInf(String cooperativeName);
//	     查询广告是否有这条记录
	   public CnmAdvertisementBean findAdvertisementInf(CnmAdvertisementBean cnmAdvertisementBean);

		//CnmAdvertisementBean表格修改
		public int updateAdvertisementTable(CnmAdvertisementBean cnmAdvertisementBean);
		//CnmAdvertisementBean获取合作商类型
		public List<CnmAdvertisementBean> findAdvertisementType();

		//修改CnmAdvertisementBean表的状态
	   public int updateAdvertisementState(CnmAdvertisementBean cnmAdvertisementBean);
	 //查询CnmAdvertisementBean表的状态
	   public int findAdvertisementState(String stateName);
//	 查询类型为轮播广告的
	   public List<CnmAdvertisementBean>  findAdvertisement(CnmAdvertisementBean cnmAdvertisementBean);
	}

