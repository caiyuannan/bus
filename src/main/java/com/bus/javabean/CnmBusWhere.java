package com.bus.javabean;

public class CnmBusWhere
{
	private String shfitDate;//日期
	private String shfitStartTime;//时间
	private String shfitEndTime;//时间
	private String shfitBusLine;//线路
	private int shfitThisStation;//1上0下行

	public CnmBusWhere()
	{
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

	public String getShfitEndTime()
	{
		return shfitEndTime;
	}

	public void setShfitEndTime(String shfitEndTime)
	{
		this.shfitEndTime = shfitEndTime;
	}

	public String getShfitBusLine()
	{
		return shfitBusLine;
	}

	public void setShfitBusLine(String shfitBusLine)
	{
		this.shfitBusLine = shfitBusLine;
	}

	public int getShfitThisStation()
	{
		return shfitThisStation;
	}

	public void setShfitThisStation(int shfitThisStation)
	{
		this.shfitThisStation = shfitThisStation;
	}

	public CnmBusWhere(String shfitDate, String shfitStartTime, String shfitEndTime, String shfitBusLine, int shfitThisStation)
	{
		this.shfitDate = shfitDate;
		this.shfitStartTime = shfitStartTime;
		this.shfitEndTime = shfitEndTime;
		this.shfitBusLine = shfitBusLine;
		this.shfitThisStation = shfitThisStation;
	}

}
