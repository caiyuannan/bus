package com.bus.javabean;

public class CnmSimulationWhere
{


	private int stationId;
	private String stationName;
	private String simulationTime;
	private String simulationSumTime;
	private int limit;
	private int page;

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

	public String getSimulationTime()
	{
		return simulationTime;
	}

	public void setSimulationTime(String simulationTime)
	{
		this.simulationTime = simulationTime;
	}

	public String getSimulationSumTime()
	{
		return simulationSumTime;
	}

	public void setSimulationSumTime(String simulationSumTime)
	{
		this.simulationSumTime = simulationSumTime;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public CnmSimulationWhere(int stationId, String stationName, String simulationTime, String simulationSumTime, int limit, int page)
	{
		this.stationId = stationId;
		this.stationName = stationName;
		this.simulationTime = simulationTime;
		this.simulationSumTime = simulationSumTime;
		this.limit = limit;
		this.page = page;
	}

	public CnmSimulationWhere()
	{
	}

}
