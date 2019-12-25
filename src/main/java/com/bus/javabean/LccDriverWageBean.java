package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 司机工资类
 * by连晨诚
 */
@Component
public class LccDriverWageBean
{
	private String cityName;
	private String driverName;
	private String driverWage;
	private String driverCellphone;

	public LccDriverWageBean()
	{
	}

	public LccDriverWageBean(String cityName, String driverName, String driverWage, String driverCellphone)
	{
		this.cityName = cityName;
		this.driverName = driverName;
		this.driverWage = driverWage;
		this.driverCellphone = driverCellphone;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getDriverName()
	{
		return driverName;
	}

	public void setDriverName(String driverName)
	{
		this.driverName = driverName;
	}

	public String getDriverWage()
	{
		return driverWage;
	}

	public void setDriverWage(String driverWage)
	{
		this.driverWage = driverWage;
	}

	public String getDriverCellphone()
	{
		return driverCellphone;
	}

	public void setDriverCellphone(String driverCellphone)
	{
		this.driverCellphone = driverCellphone;
	}

	@Override
	public String toString()
	{
		return "LccDriverWage{" + "cityName='" + cityName + '\'' + ", driverName='" + driverName + '\'' + ", driverWage='" + driverWage + '\'' + ", driverCellphone='" + driverCellphone + '\'' + '}';
	}
}
