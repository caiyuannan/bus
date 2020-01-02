package com.bus.javabean;

public class dyfStationOrderBean
{
	private Integer routeOrderId;
	private String routeOrderName;
	private String routeOrderStationName;
	private String routeOrderOrder;
	private String routeStartEnd;
	private String cityName;
	private String stateId;

	public dyfStationOrderBean()
	{
	}

	public dyfStationOrderBean(Integer routeOrderId, String routeOrderName, String routeOrderStationName, String routeOrderOrder, String routeStartEnd, String cityName, String stateId)
	{
		this.routeOrderId = routeOrderId;
		this.routeOrderName = routeOrderName;
		this.routeOrderStationName = routeOrderStationName;
		this.routeOrderOrder = routeOrderOrder;
		this.routeStartEnd = routeStartEnd;
		this.cityName = cityName;
		this.stateId = stateId;
	}

	@Override
	public String toString()
	{
		return "dyfStationOrderBean{" + "routeOrderId=" + routeOrderId + ", routeOrderName='" + routeOrderName + '\'' + ", routeOrderStationName='" + routeOrderStationName + '\'' + ", routeOrderOrder='" + routeOrderOrder + '\'' + ", routeStartEnd='" + routeStartEnd + '\'' + ", cityName='" + cityName + '\'' + ", stateId='" + stateId + '\'' + '}';
	}

	public Integer getRouteOrderId()
	{
		return routeOrderId;
	}

	public void setRouteOrderId(Integer routeOrderId)
	{
		this.routeOrderId = routeOrderId;
	}

	public String getRouteOrderName()
	{
		return routeOrderName;
	}

	public void setRouteOrderName(String routeOrderName)
	{
		this.routeOrderName = routeOrderName;
	}

	public String getRouteOrderStationName()
	{
		return routeOrderStationName;
	}

	public void setRouteOrderStationName(String routeOrderStationName)
	{
		this.routeOrderStationName = routeOrderStationName;
	}

	public String getRouteOrderOrder()
	{
		return routeOrderOrder;
	}

	public void setRouteOrderOrder(String routeOrderOrder)
	{
		this.routeOrderOrder = routeOrderOrder;
	}

	public String getRouteStartEnd()
	{
		return routeStartEnd;
	}

	public void setRouteStartEnd(String routeStartEnd)
	{
		this.routeStartEnd = routeStartEnd;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getStateId()
	{
		return stateId;
	}

	public void setStateId(String stateId)
	{
		this.stateId = stateId;
	}
}
