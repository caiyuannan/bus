package com.bus.javabean;

/**
 * @author 40651
 * 公交车排班表
 * private Integer shfitId;  排班表id
 * 	private String shfitDate; 排班日期
 * 	private String shfitStartTime; 排班开始时间
 * 	private String shfitEndTime; 排班结束时间
 * 	private String shfitBusId; 排班车辆id
 * 	private String shfitBusLine; 线路表id
 * 	private String shfitState; 发车状态
 * 	private String busLicense; 车牌号码
 * 	private String routeName; 发车线路名称
 */
public class DyfBusShfitBean
{
	private Integer shfitId;
	private String shfitDate;
	private String shfitStartTime;
	private String shfitEndTime;
	private String shfitBusId;
	private String shfitBusLine;
	private String shfitState;
	private String busLicense;
	private String routeName;
	private String dateBusTime;
	private String dateBusId;
	public DyfBusShfitBean()
	{
	}

	public DyfBusShfitBean(Integer shfitId, String shfitDate, String shfitStartTime, String shfitEndTime, String shfitBusId, String shfitBusLine, String shfitState, String busLicense, String routeName, String dateBusTime, String dateBusId)
	{
		this.shfitId = shfitId;
		this.shfitDate = shfitDate;
		this.shfitStartTime = shfitStartTime;
		this.shfitEndTime = shfitEndTime;
		this.shfitBusId = shfitBusId;
		this.shfitBusLine = shfitBusLine;
		this.shfitState = shfitState;
		this.busLicense = busLicense;
		this.routeName = routeName;
		this.dateBusTime = dateBusTime;
		this.dateBusId = dateBusId;
	}

	@Override
	public String toString()
	{
		return "DyfBusShfitBean{" + "shfitId=" + shfitId + ", shfitDate='" + shfitDate + '\'' + ", shfitStartTime='" + shfitStartTime + '\'' + ", shfitEndTime='" + shfitEndTime + '\'' + ", shfitBusId='" + shfitBusId + '\'' + ", shfitBusLine='" + shfitBusLine + '\'' + ", shfitState='" + shfitState + '\'' + ", busLicense='" + busLicense + '\'' + ", routeName='" + routeName + '\'' + ", dateBusTime='" + dateBusTime + '\'' + ", dateBusId='" + dateBusId + '\'' + '}';
	}

	public String getDateBusId()
	{
		return dateBusId;
	}

	public void setDateBusId(String dateBusId)
	{
		this.dateBusId = dateBusId;
	}

	public String getDateBusTime()
	{
		return dateBusTime;
	}

	public void setDateBusTime(String dateBusTime)
	{
		this.dateBusTime = dateBusTime;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public Integer getShfitId()
	{
		return shfitId;
	}

	public void setShfitId(Integer shfitId)
	{
		this.shfitId = shfitId;
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

	public String getShfitBusId()
	{
		return shfitBusId;
	}

	public void setShfitBusId(String shfitBusId)
	{
		this.shfitBusId = shfitBusId;
	}

	public String getShfitBusLine()
	{
		return shfitBusLine;
	}

	public void setShfitBusLine(String shfitBusLine)
	{
		this.shfitBusLine = shfitBusLine;
	}

	public String getShfitState()
	{
		return shfitState;
	}

	public void setShfitState(String shfitState)
	{
		this.shfitState = shfitState;
	}

	public String getBusLicense()
	{
		return busLicense;
	}

	public void setBusLicense(String busLicense)
	{
		this.busLicense = busLicense;
	}
}
