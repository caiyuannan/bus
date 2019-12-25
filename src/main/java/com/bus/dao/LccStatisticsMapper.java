package com.bus.dao;

import com.bus.javabean.LccCashBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LccStatisticsMapper
{
	//public List<LccCashBean> findRouteCashes();

	public List<LccCashBean>findRouteCashesByDate(Map<String,Object> map);
}
