package com.bus.javabean;

import org.apache.ibatis.annotations.Mapper;

/**
 * 卡片搜索框数据
 */
@Mapper
public class CynCardSelectBean
{
	String stateName;
	int count;
	int start;

	public CynCardSelectBean()
	{
	}

	public CynCardSelectBean(String stateName, int count, int start)
	{
		this.stateName = stateName;
		this.count = count;
		this.start = start;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
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
