package com.bus.mapper;


import com.bus.javabean.CnmCooperativeBean;
import com.bus.javabean.CnmCooperativeWhere;
import com.bus.javabean.CnmNewsBulletinBean;
import com.bus.javabean.CnmNewsBulletinWhere;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CnmNewsBulletinMapper
 {
		//获取所有的CnmNewsBulletinBean
		public List<CnmNewsBulletinBean> findNewsBulletinTable(CnmNewsBulletinWhere cnmNewsBulletinWhere);

		//获取所有的CnmNewsBulletinBean表格总条数
		public int findNewsBulletinTableCount(CnmNewsBulletinWhere cnmNewsBulletinWhere);

		//CnmNewsBulletinBean表格删除
		public int deleteNewsBulletinTable(int newsBulletinId);

		//CnmNewsBulletinIdBean表格添加
		public int addNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean);

		//CnmNewsBulletinBean表格修改
		public int updateNewsBulletinTable(CnmNewsBulletinBean cnmNewsBulletinBean);
		//CnmNewsBulletinBean获取合作商类型
		public List<CnmNewsBulletinBean> findNewsBulletinType();

		//修改CnmNewsBulletinBean表的状态
	   public int updateNewsBulletinState(CnmNewsBulletinBean cnmNewsBulletinBean);
	 //查询CnmNewsBulletinBean表的状态
	   public int findNewsBulletinState(String stateName);
	}

