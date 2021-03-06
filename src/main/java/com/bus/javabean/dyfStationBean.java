package com.bus.javabean;

import java.util.Comparator;

public class dyfStationBean
{
	private String id;
	private String routeName;
	private String stationName;
	private String order;
	private String cityName;
	private String lon;
	private String lat;

	public dyfStationBean(String id, String routeName, String stationName, String order, String cityName, String lon, String lat)
	{
		this.id = id;
		this.routeName = routeName;
		this.stationName = stationName;
		this.order = order;
		this.cityName = cityName;
		this.lon = lon;
		this.lat = lat;
	}

	public dyfStationBean()
	{
	}
	public static class Comparators {
		public static Comparator<dyfStationBean> AGE = new Comparator<dyfStationBean>() {
			@Override
			public int compare(dyfStationBean o1, dyfStationBean o2)
			{
				return Integer.valueOf(o1.getOrder()) - Integer.valueOf(o2.getOrder());
			}



		};

	}
	@Override
	public String toString()
	{
		return "dyfStationBean{" + "id='" + id + '\'' + ", routeName='" + routeName + '\'' + ", stationName='" + stationName + '\'' + ", order='" + order + '\'' + ", cityName='" + cityName + '\'' + ", lon='" + lon + '\'' + ", lat='" + lat + '\'' + '}';
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
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

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
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
