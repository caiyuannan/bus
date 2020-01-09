package com.bus.javabean;

import java.util.Date;

public class CnmSimulationBean
{
	private int simulationId;
	private int stationId;
	private String stationName;
	private int simulationSum;;
	private String simulationTime;

	public CnmSimulationBean()
	{
	}

	public int getSimulationId()
	{
		return simulationId;
	}

	public void setSimulationId(int simulationId)
	{
		this.simulationId = simulationId;
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

	public int getSimulationSum()
	{
		return simulationSum;
	}

	public void setSimulationSum(int simulationSum)
	{
		this.simulationSum = simulationSum;
	}

	public String getSimulationTime()
	{
		return simulationTime;
	}

	public void setSimulationTime(String simulationTime)
	{
		this.simulationTime = simulationTime;
	}

	public CnmSimulationBean(int simulationId, int stationId, String stationName, int simulationSum, String simulationTime)
	{
		this.simulationId = simulationId;
		this.stationId = stationId;
		this.stationName = stationName;
		this.simulationSum = simulationSum;
		this.simulationTime = simulationTime;
	}
}
