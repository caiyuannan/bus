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
	 */
	private Integer busId;
	private String busLicense;
	private String busDutyDriver;
	private String busType;
	private String busMin;
	private String busAge;

	public DyfBusBean()
	{
	}

	public DyfBusBean(Integer busId, String busLicense, String busDutyDriver, String busType, String busMin, String busAge)
	{
		this.busId = busId;
		this.busLicense = busLicense;
		this.busDutyDriver = busDutyDriver;
		this.busType = busType;
		this.busMin = busMin;
		this.busAge = busAge;
	}

	public DyfBusBean(String busLicense, String busDutyDriver, String busType, String busMin, String busAge)
	{
		this.busLicense = busLicense;
		this.busDutyDriver = busDutyDriver;
		this.busType = busType;
		this.busMin = busMin;
		this.busAge = busAge;
	}

	@Override
	public String toString()
	{
		return "DyfBusBean{" + "busId=" + busId + ", busLicense='" + busLicense + '\'' + ", busDutyDriver='" + busDutyDriver + '\'' + ", busType='" + busType + '\'' + ", busMin='" + busMin + '\'' + ", busAge='" + busAge + '\'' + '}';
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
}
