package com.bus.javabean;

import org.apache.ibatis.annotations.Mapper;

/**
 * 日志搜索框数据
 */
@Mapper
public class CynLogSelectBean
{
	String actionName;
	int count;
	int start;

	public CynLogSelectBean()
	{
	}

	public CynLogSelectBean(String actionName, int count, int start)
	{
		this.actionName = actionName;
		this.count = count;
		this.start = start;
	}

	public String getActionName()
	{
		return actionName;
	}

	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}
}
