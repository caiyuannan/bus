package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 司机排班类
 * by连晨诚
 */
@Component
public class LccCrewSchedulingBean
{
	/**司机排班id*/
	private int workId;
	/**司机id*/
	private int driverId;
	/**公交车排班id*/
	private int busWorkId;
	/**替班状态*/
	private String relayState;
	/**排班日期*/
	private String workTime;
	public LccCrewSchedulingBean()
	{
	}

	public LccCrewSchedulingBean(int workId, int driverId, int busWorkId, String relayState, String workTime)
	{
		this.workId = workId;
		this.driverId = driverId;
		this.busWorkId = busWorkId;
		this.relayState = relayState;
		this.workTime = workTime;
	}

	public int getWorkId()
	{
		return workId;
	}

	public void setWorkId(int workId)
	{
		this.workId = workId;
	}

	public int getDriverId()
	{
		return driverId;
	}

	public void setDriverId(int driverId)
	{
		this.driverId = driverId;
	}

	public int getBusWorkId()
	{
		return busWorkId;
	}

	public void setBusWorkId(int busWorkId)
	{
		this.busWorkId = busWorkId;
	}

	public String getRelayState()
	{
		return relayState;
	}

	public void setRelayState(String relayState)
	{
		this.relayState = relayState;
	}

	public String getWorkTime()
	{
		return workTime;
	}

	public void setWorkTime(String workTime)
	{
		this.workTime = workTime;
	}

	@Override
	public String toString()
	{
		return "LccCrewSchedulingBean{" + "workId=" + workId + ", driverId=" + driverId + ", busWorkId=" + busWorkId + ", relayState='" + relayState + '\'' + ", workTime='" + workTime + '\'' + '}';
	}
}
