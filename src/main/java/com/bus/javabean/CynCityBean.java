package com.bus.javabean;

/**
 * 城市Bean
 * cityId:城市Id　　
 * cityName:城市名字
 */
public class CynCityBean
{
	private int cityId;
	private String cityName;

	public CynCityBean()
	{
	}

	public CynCityBean(int cityId, String cityName)
	{
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public int getCityId()
	{
		return cityId;
	}

	public void setCityId(int cityId)
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
}
