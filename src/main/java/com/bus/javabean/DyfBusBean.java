package com.bus.javabean;

/**
 * 杜云峰 公交模块  公交车bean
 */
public class DyfBusBean
{
	/**
	 * busId 公交id主键
	 * busLicense 公交车牌号
	 * busDutyDriver 公交责任人名称
	 * busType 公交车类型
	 * busMin 公交车使用时间
	 * busAge 公交车使用年限
	 * busState 公交状态
	 */
	private Integer busId;
	private String busLicense;
	private String busDutyDriver;
	private String busType;
	private String busMin;
	private String busAge;
	private Integer busState;
	private String stateName;

	public DyfBusBean()
	{
	}

	public DyfBusBean(String busLicense, String busDutyDriver, String busType, String busMin, String busAge, Integer busState, String stateName)
	{
		this.busLicense = busLicense;
		this.busDutyDriver = busDutyDriver;
		this.busType = busType;
		this.busMin = busMin;
		this.busAge = busAge;
		this.busState = busState;
		this.stateName = stateName;
	}

	public DyfBusBean(Integer busId, String busLicense, String busDutyDriver, String busType, String busMin, String busAge, Integer busState, String stateName)
	{
		this.busId = busId;
		this.busLicense = busLicense;
		this.busDutyDriver = busDutyDriver;
		this.busType = busType;
		this.busMin = busMin;
		this.busAge = busAge;
		this.busState = busState;
		this.stateName = stateName;
	}

	@Override
	public String toString()
	{
		return "DyfBusBean{" + "busId=" + busId + ", busLicense='" + busLicense + '\'' + ", busDutyDriver='" + busDutyDriver + '\'' + ", busType='" + busType + '\'' + ", busMin='" + busMin + '\'' + ", busAge='" + busAge + '\'' + ", busState=" + busState + ", stateName='" + stateName + '\'' + '}';
	}

	public Integer getBusId()
	{
		return busId;
	}

	public void setBusId(Integer busId)
	{
		this.busId = busId;
	}

	public String getBusLicense()
	{
		return busLicense;
	}

	public void setBusLicense(String busLicense)
	{
		this.busLicense = busLicense;
	}

	public String getBusDutyDriver()
	{
		return busDutyDriver;
	}

	public void setBusDutyDriver(String busDutyDriver)
	{
		this.busDutyDriver = busDutyDriver;
	}

	public String getBusType()
	{
		return busType;
	}

	public void setBusType(String busType)
	{
		this.busType = busType;
	}

	public String getBusMin()
	{
		return busMin;
	}

	public void setBusMin(String busMin)
	{
		this.busMin = busMin;
	}

	public String getBusAge()
	{
		return busAge;
	}

	public void setBusAge(String busAge)
	{
		this.busAge = busAge;
	}

	public Integer getBusState()
	{
		return busState;
	}

	public void setBusState(Integer busState)
	{
		this.busState = busState;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
}
