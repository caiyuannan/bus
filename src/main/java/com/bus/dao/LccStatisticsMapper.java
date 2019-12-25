package com.bus.dao;

import com.bus.javabean.LccCashBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LccStatisticsMapper
{
	//public List<LccCashBean> findRouteCashes();

	public List<LccCashBean>findRouteCashesByDate(@Param("start") String start,@Param("end") String end);
}
