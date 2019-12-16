package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 司机考勤类
 * by连晨诚
 */
@Component
public class LccGateCardBean
{
	/**打卡出勤id*/
	private int attendanceId;
	/**司机id*/
	private int driverId;
	/**上班时间+日期*/
	private String office_hours;
	/**下班时间+日期 */
	private String closing_time;

	public LccGateCardBean()
	{
	}

	public LccGateCardBean(int attendanceId, int driverId, String office_hours, String closing_time)
	{
		this.attendanceId = attendanceId;
		this.driverId = driverId;
		this.office_hours = office_hours;
		this.closing_time = closing_time;
	}

	public int getAttendanceId()
	{
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId)
	{
		this.attendanceId = attendanceId;
	}

	public int getDriverId()
	{
		return driverId;
	}

	public void setDriverId(int driverId)
	{
		this.driverId = driverId;
	}

	public String getOffice_hours()
	{
		return office_hours;
	}

	public void setOffice_hours(String office_hours)
	{
		this.office_hours = office_hours;
	}

	public String getClosing_time()
	{
		return closing_time;
	}

	public void setClosing_time(String closing_time)
	{
		this.closing_time = closing_time;
	}

	@Override
	public String toString()
	{
		return "LccGateCardBean{" + "attendanceId=" + attendanceId + ", driverId=" + driverId + ", office_hours='" + office_hours + '\'' + ", closing_time='" + closing_time + '\'' + '}';
	}
}
