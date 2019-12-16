package com.bus.javabean;

public class DyfCityBean
{
	private Integer cityId;
	private String cityName;

	public DyfCityBean()
	{
	}

	public DyfCityBean(Integer cityId, String cityName)
	{
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public Integer getCityId()
	{
		return cityId;
	}

	public void setCityId(Integer cityId)
	{
		this.cityId = cityId;
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
		return "DyfCityBean{" + "cityId=" + cityId + ", cityName='" + cityName + '\'' + '}';
	}
}
