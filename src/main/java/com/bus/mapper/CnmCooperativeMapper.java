package com.bus.mapper;


import com.bus.javabean.CnmCooperativeBean;
import com.bus.javabean.CnmCooperativeWhere;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CnmCooperativeMapper
 {
		//获取所有的CnmCooperativeBean
		public List<CnmCooperativeBean> findCooperativeTable(CnmCooperativeWhere cnmCooperativeWhere);

		//获取所有的CnmCooperativeBean表格总条数
		public int findCooperativeTableCount(CnmCooperativeWhere cnmCooperativeWhere);

		//CnmCooperativeBean表格删除
		public int deleteCooperativeTable(int cooperativeId);

		//CnmCooperativeBean表格添加
		public int addCooperativeTable(CnmCooperativeBean cnmCooperativeBean);

		//CnmCooperativeBean表格修改
		public int updateCooperativeTable(CnmCooperativeBean cnmCooperativeBean);
		//CnmCooperativeBean获取合作商类型
		public List<CnmCooperativeBean> findCooperativeType();

		//修改CnmCooperativeBean表的状态
	   public int updateCooperativestate(CnmCooperativeBean cnmCooperativeBean);
	 //查询CnmCooperativeBean表的状态
	   public int findCooperativeState(String stateName);
	}

