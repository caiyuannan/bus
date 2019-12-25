package com.bus.javabean;

/**
 * Test类
 */
public class CynTestBean
{
	private int id;
	private String routeName;
	private String stationName;
	private int order;
	private String cityName;
	private String lon;
	private String lat;

	public CynTestBean()
	{
	}

	public CynTestBean(int id, String routeName, String stationName, int order, String cityName, String lon, String lat)
	{
		this.id = id;
		this.routeName = routeName;
		this.stationName = stationName;
		this.order = order;
		this.cityName = cityName;
		this.lon = lon;
		this.lat = lat;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
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

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getLon()
	{
		return lon;
	}

	public void setLon(String lon)
	{
		this.lon = lon;
	}

	public String getLat()
	{
		return lat;
	}

	public void setLat(String lat)
	{
		this.lat = lat;
	}
}
