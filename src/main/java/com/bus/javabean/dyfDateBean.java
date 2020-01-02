package com.bus.javabean;

public class dyfDateBean
{
	private Integer dateId;
	private String dateTime;

	public dyfDateBean(Integer dateId, String dateTime)
	{
		this.dateId = dateId;
		this.dateTime = dateTime;
	}

	public dyfDateBean()
	{
	}

	public Integer getDateId()
	{
		return dateId;
	}

	public void setDateId(Integer dateId)
	{
		this.dateId = dateId;
	}
}
