package com.bus.javabean;
/**
 * 收银类
 * by连晨诚
 * cashId收银ID
 * driverId关联获取司机名字
 * userId关联获取乘客信息
 * cashTime收款时间
 * cashDate收款日期
 * income收款金额
 * busStopId关联上车站点名字
 * routeId关联线路名字
 */
public class LccCashBean
{
	private int cashId;
	private int driverId;
	private int userId;
	private String cashDate;
	private String cashTime;
	private int income;
	private int busStopId;
	private int routeId;

	private int incomes;
	private String routeName;

	public LccCashBean()
	{
	}

	public LccCashBean(int cashId, int driverId, int userId, String cashDate, String cashTime, int income, int busStopId, int routeId, int incomes, String routeName)
	{
		this.cashId = cashId;
		this.driverId = driverId;
		this.userId = userId;
		this.cashDate = cashDate;
		this.cashTime = cashTime;
		this.income = income;
		this.busStopId = busStopId;
		this.routeId = routeId;
		this.incomes = incomes;
		this.routeName = routeName;
	}

	public int getCashId()
	{
		return cashId;
	}

	public void setCashId(int cashId)
	{
		this.cashId = cashId;
	}

	public int getDriverId()
	{
		return driverId;
	}

	public void setDriverId(int driverId)
	{
		this.driverId = driverId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getCashTime()
	{
		return cashTime;
	}

	public void setCashTime(String cashTime)
	{
		this.cashTime = cashTime;
	}

	public int getIncome()
	{
		return income;
	}

	public void setIncome(int income)
	{
		this.income = income;
	}

	public int getBusStopId()
	{
		return busStopId;
	}

	public void setBusStopId(int busStopId)
	{
		this.busStopId = busStopId;
	}

	public String getCashDate()
	{
		return cashDate;
	}

	public void setCashDate(String cashDate)
	{
		this.cashDate = cashDate;
	}

	public int getRouteId()
	{
		return routeId;
	}

	public void setRouteId(int routeId)
	{
		this.routeId = routeId;
	}

	public int getIncomes()
	{
		return incomes;
	}

	public void setIncomes(int incomes)
	{
		this.incomes = incomes;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	@Override
	public String toString()
	{
		return "LccCashBean{" + "cashId=" + cashId + ", driverId=" + driverId + ", userId=" + userId + ", cashDate='" + cashDate + '\'' + ", cashTime='" + cashTime + '\'' + ", income=" + income + ", busStopId=" + busStopId + ", routeId=" + routeId + ", incomes=" + incomes + ", routeName=" + routeName + '}';
	}
}
