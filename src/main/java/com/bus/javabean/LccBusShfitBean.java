package com.bus.javabean;

/**
 * 汽车发车类
 * by连晨诚
 */
public class LccBusShfitBean
{
	private String shfitDate;
	private String shfitStartTime;
	private String shfitState;

	public LccBusShfitBean()
	{
	}

	public LccBusShfitBean(String shfitDate, String shfitStartTime, String shfitState)
	{
		this.shfitDate = shfitDate;
		this.shfitStartTime = shfitStartTime;
		this.shfitState = shfitState;
	}

	public String getShfitDate()
	{
		return shfitDate;
	}

	public void setShfitDate(String shfitDate)
	{
		this.shfitDate = shfitDate;
	}

	public String getShfitStartTime()
	{
		return shfitStartTime;
	}

	public void setShfitStartTime(String shfitStartTime)
	{
		this.shfitStartTime = shfitStartTime;
	}

	public String getShfitState()
	{
		return shfitState;
	}

	public void setShfitState(String shfitState)
	{
		this.shfitState = shfitState;
	}

	@Override
	public String toString()
	{
		return "LccBusShfitBean{" + "shfitDate='" + shfitDate + '\'' + ", shfitStartTime='" + shfitStartTime + '\'' + ", shfitState='" + shfitState + '\'' + '}';
	}
}
