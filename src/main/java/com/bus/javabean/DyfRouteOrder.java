package com.bus.javabean;

public class DyfRouteOrder
{
	private int routeOrderId;
	private String routeName;
	private String routeStationName;
	private String routeOrder;
	private String routeEndOrStart;

	public DyfRouteOrder()
	{
	}

	public DyfRouteOrder(int routeOrderId, String routeName, String routeStationName, String routeOrder, String routeEndOrStart)
	{
		this.routeOrderId = routeOrderId;
		this.routeName = routeName;
		this.routeStationName = routeStationName;
		this.routeOrder = routeOrder;
		this.routeEndOrStart = routeEndOrStart;
	}

	@Override
	public String toString()
	{
		return "DyfRouteOrder{" + "routeOrderId=" + routeOrderId + ", routeName='" + routeName + '\'' + ", routeStationName='" + routeStationName + '\'' + ", routeOrder='" + routeOrder + '\'' + ", routeEndOrStart='" + routeEndOrStart + '\'' + '}';
	}

	public int getRouteOrderId()
	{
		return routeOrderId;
	}

	public void setRouteOrderId(int routeOrderId)
	{
		this.routeOrderId = routeOrderId;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public String getRouteStationName()
	{
		return routeStationName;
	}

	public void setRouteStationName(String routeStationName)
	{
		this.routeStationName = routeStationName;
	}

	public String getRouteOrder()
	{
		return routeOrder;
	}

	public void setRouteOrder(String routeOrder)
	{
		this.routeOrder = routeOrder;
	}

	public String getRouteEndOrStart()
	{
		return routeEndOrStart;
	}

	public void setRouteEndOrStart(String routeEndOrStart)
	{
		this.routeEndOrStart = routeEndOrStart;
	}
}
