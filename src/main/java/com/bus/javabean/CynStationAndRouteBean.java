package com.bus.javabean;

/**
 * 城市和线路合起来的bean
 * 1.站点ID
 * 2.站点名字
 * 3.站点X
 * 4.站点Y
 * 5.状态
 * 6.线路名
 * 7.城市名
 * 8.路费
 * 9.总时长
 * 10.是否是起点站
 */
public class CynStationAndRouteBean
{
	private int stationId;
	private String stationName;
	private String stationLon;
	private String stationLat;
	private String stateName;
	private String routeName;
	private String cityName;
	private int routeFee;
	private int routeTime;
	private int isStart;

	public CynStationAndRouteBean()
	{
	}

	public CynStationAndRouteBean(int stationId, String stationName, String stationLon, String stationLat, String stateName, String routeName, String cityName, int routeFee, int routeTime, int isStart)
	{
		this.stationId = stationId;
		this.stationName = stationName;
		this.stationLon = stationLon;
		this.stationLat = stationLat;
		this.stateName = stateName;
		this.routeName = routeName;
		this.cityName = cityName;
		this.routeFee = routeFee;
		this.routeTime = routeTime;
		this.isStart = isStart;
	}

	public int getStationId()
	{
		return stationId;
	}

	public void setStationId(int stationId)
	{
		this.stationId = stationId;
	}

	public String getStationName()
	{
		return stationName;
	}

	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}

	public String getStationLon()
	{
		return stationLon;
	}

	public void setStationLon(String stationLon)
	{
		this.stationLon = stationLon;
	}

	public String getStationLat()
	{
		return stationLat;
	}

	public void setStationLat(String stationLat)
	{
		this.stationLat = stationLat;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
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

	public int getIsStart()
	{
		return isStart;
	}

	public void setIsStart(int isStart)
	{
		this.isStart = isStart;
	}
}
