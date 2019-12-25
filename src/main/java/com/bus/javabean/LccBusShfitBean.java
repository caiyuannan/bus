package com.bus.javabean;

/**
 * 汽车发车类
 * by连晨诚
 */
public class LccBusShfitBean
{
	private int shfitId;
	private String shfitDate;
	private String shfitStartTime;
	private String shfitState;

	public LccBusShfitBean()
	{
	}

	public LccBusShfitBean(int shfitId, String shfitDate, String shfitStartTime, String shfitState)
	{
		this.shfitId = shfitId;
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

	public int getShfitId()
	{
		return shfitId;
	}

	public void setShfitId(int shfitId)
	{
		this.shfitId = shfitId;
	}

	@Override
	public String toString()
	{
		return "LccBusShfitBean{" + "shfitId=" + shfitId + ", shfitDate='" + shfitDate + '\'' + ", shfitStartTime='" + shfitStartTime + '\'' + ", shfitState='" + shfitState + '\'' + '}';
	}
}
