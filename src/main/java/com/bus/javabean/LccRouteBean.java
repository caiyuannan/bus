package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 线路类
 * by连晨诚
 * routeId线路id
 * routeName线路名
 * routeFee线路费用
 * routeTime线路总分钟
 * stateId状态1启用
 * cityName城市
 */
@Component
public class LccRouteBean
{
	private int routeId;
	private String routeName;
	private int routeFee;
	private int routeTime;
	private int stateId;
	private String cityName;

	public LccRouteBean()
	{
	}

	public LccRouteBean(int routeId, String routeName, int routeFee, int routeTime, int stateId, String cityName)
	{
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeFee = routeFee;
		this.routeTime = routeTime;
		this.stateId = stateId;
		this.cityName = cityName;
	}

	public int getRouteId()
	{
		return routeId;
	}

	public void setRouteId(int routeId)
	{
		this.routeId = routeId;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public int getRouteFee()
	{
		return routeFee;
	}

	public void setRouteFee(int routeFee)
	{
		this.routeFee = routeFee;
	}

	public int getRouteTime()
	{
		return routeTime;
	}

	public void setRouteTime(int routeTime)
	{
		this.routeTime = routeTime;
	}

	public int getStateId()
	{
		return stateId;
	}

	public void setStateId(int stateId)
	{
		this.stateId = stateId;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	@Override
	public String toString()
	{
		return "LccRouteBean{" + "routeId=" + routeId + ", routeName='" + routeName + '\'' + ", routeFee=" + routeFee + ", routeTime=" + routeTime + ", stateId=" + stateId + ", cityName='" + cityName + '\'' + '}';
	}
}
