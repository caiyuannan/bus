package com.bus.dao;

import com.bus.aoplog.CynSystemLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CynAopAddLogMapper
{
	/**
	 * 添加日志
	 */
	public int addLog(CynSystemLog cynSystemLog);
}
