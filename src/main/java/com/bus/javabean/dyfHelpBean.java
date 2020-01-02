package com.bus.javabean;

public class dyfHelpBean
{
	private String liscense;
	private String stopStation;
	private String jionTime;
	private String beginTime;
	private String newTime;
	private String newStation;

	public dyfHelpBean(String liscense, String stopStation, String jionTime, String beginTime, String newTime, String newStation)
	{
		this.liscense = liscense;
		this.stopStation = stopStation;
		this.jionTime = jionTime;
		this.beginTime = beginTime;
		this.newTime = newTime;
		this.newStation = newStation;
	}

	public dyfHelpBean()
	{
	}

	@Override
	public String toString()
	{
		return "dyfHelpBean{" + "liscense='" + liscense + '\'' + ", stopStation='" + stopStation + '\'' + ", jionTime='" + jionTime + '\'' + ", beginTime='" + beginTime + '\'' + ", newTime='" + newTime + '\'' + ", newStation='" + newStation + '\'' + '}';
	}

	public String getLiscense()
	{
		return liscense;
	}

	public void setLiscense(String liscense)
	{
		this.liscense = liscense;
	}

	public String getStopStation()
	{
		return stopStation;
	}

	public void setStopStation(String stopStation)
	{
		this.stopStation = stopStation;
	}

	public String getJionTime()
	{
		return jionTime;
	}

	public void setJionTime(String jionTime)
	{
		this.jionTime = jionTime;
	}

	public String getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	public String getNewTime()
	{
		return newTime;
	}

	public void setNewTime(String newTime)
	{
		this.newTime = newTime;
	}

	public String getNewStation()
	{
		return newStation;
	}

	public void setNewStation(String newStation)
	{
		this.newStation = newStation;
	}
}
