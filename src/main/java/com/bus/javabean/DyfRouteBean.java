package com.bus.javabean;

/**
 * @author 40651
 * 公交车线路表
 * private Integer routeId; 线路表id
 * 	private String routeName; 线路名称
 * 	private Integer routeFee;   线路费
 * 	private Integer routeTime; 单程时间
 * 	private Integer stateId; 状态id
 * 	private String cityName; 城市名称
 */
public class DyfRouteBean
{
	private Integer routeId;
	private String routeName;
	private Integer routeFee;
	private Integer routeTime;
	private Integer stateId;
	private String cityName;
	public DyfRouteBean()
	{
	}

	public DyfRouteBean(Integer routeId, String routeName, Integer routeFee, Integer routeTime, Integer stateId, String cityName)
	{
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeFee = routeFee;
		this.routeTime = routeTime;
		this.stateId = stateId;
		this.cityName = cityName;
	}

	@Override
	public String toString()
	{
		return "DyfRouteBean{" + "routeId=" + routeId + ", routeName='" + routeName + '\'' + ", routeFee=" + routeFee + ", routeTime=" + routeTime + ", stateId=" + stateId + ", cityName='" + cityName + '\'' + '}';
	}

	public Integer getRouteId()
	{
		return routeId;
	}

	public void setRouteId(Integer routeId)
	{
		this.routeId = routeId;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public Integer getRouteFee()
	{
		return routeFee;
	}

	public void setRouteFee(Integer routeFee)
	{
		this.routeFee = routeFee;
	}

	public Integer getRouteTime()
	{
		return routeTime;
	}

	public void setRouteTime(Integer routeTime)
	{
		this.routeTime = routeTime;
	}

	public Integer getStateId()
	{
		return stateId;
	}

	public void setStateId(Integer stateId)
	{
		this.stateId = stateId;
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
