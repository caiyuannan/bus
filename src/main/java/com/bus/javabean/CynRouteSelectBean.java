package com.bus.javabean;

/**
 * 用于根据站点  获取其所有线路以及方向
 */
public class CynRouteSelectBean
{
	private String routeName;
	private String endStationName;

	public CynRouteSelectBean()
	{
	}

	public CynRouteSelectBean(String routeName, String endStationName)
	{
		this.routeName = routeName;
		this.endStationName = endStationName;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public String getEndStationName()
	{
		return endStationName;
	}

	public void setEndStationName(String endStationName)
	{
		this.endStationName = endStationName;
	}
}
