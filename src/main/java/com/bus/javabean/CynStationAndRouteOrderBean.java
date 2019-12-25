package com.bus.javabean;

/**
 * 站点线路bean
 * routeOrderId:自增长ID
 * routeOrderName:线路名
 * stationName：站点名
 * order:顺序
 * isStartEnd：是否是始末点   1起点  0终点
 * cityName：城市名
 * stateName：状态
 *
 */
public class CynStationAndRouteOrderBean
{
	private int routeOrderId;
	private String routeOrderName;
	private String stationName;
	private int order;
	private int isStartEnd;
	private String cityName;
	private String stateName;

	public CynStationAndRouteOrderBean()
	{
	}

	public CynStationAndRouteOrderBean(int routeOrderId, String routeOrderName, String stationName, int order, int isStartEnd, String cityName, String stateName)
	{
		this.routeOrderId = routeOrderId;
		this.routeOrderName = routeOrderName;
		this.stationName = stationName;
		this.order = order;
		this.isStartEnd = isStartEnd;
		this.cityName = cityName;
		this.stateName = stateName;
	}

	public int getRouteOrderId()
	{
		return routeOrderId;
	}

	public void setRouteOrderId(int routeOrderId)
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

	public String getStationName()
	{
		return stationName;
	}

	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}

	public int getOrder()
	{
		return order;
	}

	public void setOrder(int order)
	{
		this.order = order;
	}

	public int getIsStartEnd()
	{
		return isStartEnd;
	}

	public void setIsStartEnd(int isStartEnd)
	{
		this.isStartEnd = isStartEnd;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
}
