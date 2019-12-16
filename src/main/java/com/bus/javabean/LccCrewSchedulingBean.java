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
	/**替班司机id*/
	private int relayDriverId;

	public LccCrewSchedulingBean()
	{
	}

	public LccCrewSchedulingBean(int workId, int driverId, int busWorkId, int relayDriverId)
	{
		this.workId = workId;
		this.driverId = driverId;
		this.busWorkId = busWorkId;
		this.relayDriverId = relayDriverId;
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

	public int getRelayDriverId()
	{
		return relayDriverId;
	}

	public void setRelayDriverId(int relayDriverId)
	{
		this.relayDriverId = relayDriverId;
	}

	@Override
	public String toString()
	{
		return "LccCrewScheduling{" + "workId=" + workId + ", driverId=" + driverId + ", busWorkId=" + busWorkId + ", relayDriverId=" + relayDriverId + '}';
	}
}
