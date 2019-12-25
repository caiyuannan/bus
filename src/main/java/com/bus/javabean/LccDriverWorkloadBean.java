package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 司机工作量类
 * by连晨诚
 */
@Component
public class LccDriverWorkloadBean
{
	private int workloadId;
	private int busId;
	private int routeId;
	private int driverId;
	private int stationId;
	private int attendanceId;
	private String startTime;
	private String endTime;
	private String busLicense;
	private String routeName;
	private String officeHours;
	private String workDate;
	public LccDriverWorkloadBean()
	{
	}

	public LccDriverWorkloadBean(int workloadId, int busId, int routeId, int driverId, int stationId, int attendanceId, String startTime, String endTime, String busLicense, String routeName, String officeHours, String workDate)
	{
		this.workloadId = workloadId;
		this.busId = busId;
		this.routeId = routeId;
		this.driverId = driverId;
		this.stationId = stationId;
		this.attendanceId = attendanceId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.busLicense = busLicense;
		this.routeName = routeName;
		this.officeHours = officeHours;
		this.workDate = workDate;
	}

	public int getWorkloadId()
	{
		return workloadId;
	}

	public void setWorkloadId(int workloadId)
	{
		this.workloadId = workloadId;
	}

	public int getBusId()
	{
		return busId;
	}

	public void setBusId(int busId)
	{
		this.busId = busId;
	}

	public int getRouteId()
	{
		return routeId;
	}

	public void setRouteId(int routeId)
	{
		this.routeId = routeId;
	}

	public int getDriverId()
	{
		return driverId;
	}

	public void setDriverId(int driverId)
	{
		this.driverId = driverId;
	}

	public int getStationId()
	{
		return stationId;
	}

	public void setStationId(int stationId)
	{
		this.stationId = stationId;
	}

	public int getAttendanceId()
	{
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId)
	{
		this.attendanceId = attendanceId;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getBusLicense()
	{
		return busLicense;
	}

	public void setBusLicense(String busLicense)
	{
		this.busLicense = busLicense;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public String getOfficeHours()
	{
		return officeHours;
	}

	public void setOfficeHours(String officeHours)
	{
		this.officeHours = officeHours;
	}

	public String getWorkDate()
	{
		return workDate;
	}

	public void setWorkDate(String workDate)
	{
		this.workDate = workDate;
	}

	@Override
	public String toString()
	{
		return "LccDriverWorkloadBean{" + "workloadId=" + workloadId + ", busId=" + busId + ", routeId=" + routeId + ", driverId=" + driverId + ", stationId=" + stationId + ", attendanceId=" + attendanceId + ", startTime='" + startTime + '\'' + ", endTime='" + endTime + '\'' + ", busLicense='" + busLicense + '\'' + ", routeName='" + routeName + '\'' + ", officeHours='" + officeHours + '\'' + ", workDate='" + workDate + '\'' + '}';
	}
}
