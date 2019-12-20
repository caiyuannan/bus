package com.bus.javabean;

import java.math.BigDecimal;

/**
 * @author 40651   维修表Javabean
 * private BigDecimal repairId; 维修表主键id
 * 	private BigDecimal repairBusId; 维修表维修车联id
 * 	private String repairStart; 维修表开始日期     2019-12-18
 * 	private String repairStartTime; 维修表开始日期当天时间    AM.18：07
 * 	private String repairFinish;    维修表维修结束日期
 * 	private String repairFinishTime;    维修表维修结束日期当天时间
 * 	private BigDecimal repairDriverId;  车辆维护司机id
 * 	private String repairState;     车辆维修状态
 */
public class DyfBusRepairBean
{
	private BigDecimal repairId;
	private BigDecimal repairBusId;
	private String repairStart;
	private String repairStartTime;
	private String repairFinish;
	private String repairFinishTime;
	private BigDecimal repairDriverId;
	private String repairState;
	//下面的是数据操作多添的数据 不属于该表字段
	//	busDutyDriver 司机名称
	private String busDutyDriver;
	//车牌号
	private String busLicense;
	//状态名称
	private String stateName;

	public DyfBusRepairBean(BigDecimal repairId, BigDecimal repairBusId, String repairStart, String repairStartTime, String repairFinish, String repairFinishTime, BigDecimal repairDriverId, String repairState, String busDutyDriver, String busLicense, String stateName)
	{
		this.repairId = repairId;
		this.repairBusId = repairBusId;
		this.repairStart = repairStart;
		this.repairStartTime = repairStartTime;
		this.repairFinish = repairFinish;
		this.repairFinishTime = repairFinishTime;
		this.repairDriverId = repairDriverId;
		this.repairState = repairState;
		this.busDutyDriver = busDutyDriver;
		this.busLicense = busLicense;
		this.stateName = stateName;
	}

	public DyfBusRepairBean( BigDecimal repairBusId, String repairStart, String repairStartTime, String repairFinish, String repairFinishTime, String repairState)
	{

		this.repairBusId = repairBusId;
		this.repairStart = repairStart;
		this.repairStartTime = repairStartTime;
		this.repairFinish = repairFinish;
		this.repairFinishTime = repairFinishTime;
		this.repairState = repairState;
	}

	public DyfBusRepairBean()
	{
	}

	@Override
	public String toString()
	{
		return "DyfBusRepairBean{" + "repairId=" + repairId + ", repairBusId=" + repairBusId + ", repairStart='" + repairStart + '\'' + ", repairStartTime='" + repairStartTime + '\'' + ", repairFinish='" + repairFinish + '\'' + ", repairFinishTime='" + repairFinishTime + '\'' + ", repairDriverId=" + repairDriverId + ", repairState='" + repairState + '\'' + ", busDutyDriver='" + busDutyDriver + '\'' + ", busLicense='" + busLicense + '\'' + ", stateName='" + stateName + '\'' + '}';
	}

	public String getBusLicense()
	{
		return busLicense;
	}

	public void setBusLicense(String busLicense)
	{
		this.busLicense = busLicense;
	}

	public BigDecimal getRepairId()
	{
		return repairId;
	}

	public void setRepairId(BigDecimal repairId)
	{
		this.repairId = repairId;
	}

	public BigDecimal getRepairBusId()
	{
		return repairBusId;
	}

	public void setRepairBusId(BigDecimal repairBusId)
	{
		this.repairBusId = repairBusId;
	}

	public String getRepairStart()
	{
		return repairStart;
	}

	public void setRepairStart(String repairStart)
	{
		this.repairStart = repairStart;
	}

	public String getRepairStartTime()
	{
		return repairStartTime;
	}

	public void setRepairStartTime(String repairStartTime)
	{
		this.repairStartTime = repairStartTime;
	}

	public String getRepairFinish()
	{
		return repairFinish;
	}

	public void setRepairFinish(String repairFinish)
	{
		this.repairFinish = repairFinish;
	}

	public String getRepairFinishTime()
	{
		return repairFinishTime;
	}

	public void setRepairFinishTime(String repairFinishTime)
	{
		this.repairFinishTime = repairFinishTime;
	}

	public BigDecimal getRepairDriverId()
	{
		return repairDriverId;
	}

	public void setRepairDriverId(BigDecimal repairDriverId)
	{
		this.repairDriverId = repairDriverId;
	}

	public String getRepairState()
	{
		return repairState;
	}

	public void setRepairState(String repairState)
	{
		this.repairState = repairState;
	}

	public String getBusDutyDriver()
	{
		return busDutyDriver;
	}

	public void setBusDutyDriver(String busDutyDriver)
	{
		this.busDutyDriver = busDutyDriver;
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
