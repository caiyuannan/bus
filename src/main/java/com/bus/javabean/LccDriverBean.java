package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 司机类
 * by连晨诚
 */
@Component
public class LccDriverBean
{
	/**司机id*/
	private int driverId;
	/**司机名*/
	private String driverName;
	/**司机手机号*/
	private String driverCellphone;
	/**司机身份证*/
	private String driverIDNumber;
	/**司机状态*/
	private String driverState;
	/**司机工资*/
	private int driverWage;
	private int cityId;
	/**司机所在城市名称*/
	private String cityName;
	/**司机上班站点*/
	private int stationId;
	private String stationName;

	public LccDriverBean()
	{
	}

	public LccDriverBean(int driverId, String driverName, String driverCellphone, String driverIDNumber, String driverState, int driverWage, int cityId, String cityName, int stationId, String stationName)
	{
		this.driverId = driverId;
		this.driverName = driverName;
		this.driverCellphone = driverCellphone;
		this.driverIDNumber = driverIDNumber;
		this.driverState = driverState;
		this.driverWage = driverWage;
		this.cityId = cityId;
		this.cityName = cityName;
		this.stationId = stationId;
		this.stationName = stationName;
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

	public int getDriverId()
	{
		return driverId;
	}

	public void setDriverId(int driverId)
	{
		this.driverId = driverId;
	}

	public String getDriverName()
	{
		return driverName;
	}

	public void setDriverName(String driverName)
	{
		this.driverName = driverName;
	}

	public String getDriverCellphone()
	{
		return driverCellphone;
	}

	public void setDriverCellphone(String driverCellphone)
	{
		this.driverCellphone = driverCellphone;
	}

	public String getDriverIDNumber()
	{
		return driverIDNumber;
	}

	public void setDriverIDNumber(String driverIDNumber)
	{
		this.driverIDNumber = driverIDNumber;
	}

	public String getDriverState()
	{
		return driverState;
	}

	public void setDriverState(String driverState)
	{
		this.driverState = driverState;
	}

	public int getDriverWage()
	{
		return driverWage;
	}

	public void setDriverWage(int driverWage)
	{
		this.driverWage = driverWage;
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

	@Override
	public String toString()
	{
		return "LccDriverBean{" + "driverId=" + driverId + ", driverName='" + driverName + '\'' + ", driverCellphone='" + driverCellphone + '\'' + ", driverIDNumber='" + driverIDNumber + '\'' + ", driverState='" + driverState + '\'' + ", driverWage=" + driverWage + ", cityId=" + cityId + ", cityName='" + cityName + '\'' + ", stationId=" + stationId + ", stationName='" + stationName + '\'' + '}';
	}
}
