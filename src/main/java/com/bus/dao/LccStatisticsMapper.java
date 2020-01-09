package com.bus.dao;

import com.bus.javabean.LccCashBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LccStatisticsMapper
{
	//public List<LccCashBean> findRouteCashes();

	public List<LccCashBean>findRouteCashesByDate(@Param("start") String start, @Param("end") String end);
}
