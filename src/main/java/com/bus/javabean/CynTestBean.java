package com.bus.javabean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Double meter;
	private String startStation;
	private String endStation;
	private int count;
	private List<Object> ways=new ArrayList();

	public CynTestBean()
	{
	}

	public CynTestBean(int id, String routeName, String stationName, int order, String cityName, String lon, String lat, Double meter, String startStation, String endStation, int count)
	{
		this.id = id;
		this.routeName = routeName;
		this.stationName = stationName;
		this.order = order;
		this.cityName = cityName;
		this.lon = lon;
		this.lat = lat;
		this.meter = meter;
		this.startStation = startStation;
		this.endStation = endStation;
		this.count = count;
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

	public Double getMeter()
	{
		return meter;
	}

	public void setMeter(Double meter)
	{
		this.meter = meter;
	}

	public String getStartStation()
	{
		return startStation;
	}

	public void setStartStation(String startStation)
	{
		this.startStation = startStation;
	}

	public String getEndStation()
	{
		return endStation;
	}

	public void setEndStation(String endStation)
	{
		this.endStation = endStation;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<Object> getWays()
	{
		return ways;
	}

	public void setWays(List<Object> ways)
	{
		this.ways = ways;
	}
}
