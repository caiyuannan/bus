package com.bus.javabean;

/**
 * 路线表的bean
 */
public class dyfAllRouteBean
{
	private String routeId;
	private String routeName;
	private String routeFee;
	private String routeTime;
	private String stateId;
	private String cityName;

	public dyfAllRouteBean()
	{
	}

	public dyfAllRouteBean(String routeId, String routeName, String routeFee, String routeTime, String stateId, String cityName)
	{
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeFee = routeFee;
		this.routeTime = routeTime;
		this.stateId = stateId;
		this.cityName = cityName;
	}

	@Override
	public String toString()
	{
		return "dyfAllRouteBean{" + "routeId='" + routeId + '\'' + ", routeName='" + routeName + '\'' + ", routeFee='" + routeFee + '\'' + ", routeTime='" + routeTime + '\'' + ", stateId='" + stateId + '\'' + ", cityName='" + cityName + '\'' + '}';
	}

	public String getRouteId()
	{
		return routeId;
	}

	public void setRouteId(String routeId)
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

	public String getRouteFee()
	{
		return routeFee;
	}

	public void setRouteFee(String routeFee)
	{
		this.routeFee = routeFee;
	}

	public String getRouteTime()
	{
		return routeTime;
	}

	public void setRouteTime(String routeTime)
	{
		this.routeTime = routeTime;
	}

	public String getStateId()
	{
		return stateId;
	}

	public void setStateId(String stateId)
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
}
