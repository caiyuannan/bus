package com.bus.aoplog;

/**
 * 日志实体
 * loginfid:自增ID
 * actionname:操作人
 * actiontime:操作时间
 * actioninf:操作事件
 * money:充值金额
 * @author yn
 *
 */
public class CynSystemLog
{
	private int logInfId;
	private String actionName;
	private String actionTime;
	private String actionInf;
	private double money;

	public CynSystemLog()
	{
	}

	public CynSystemLog(int logInfId, String actionName, String actionTime, String actionInf, double money)
	{
		this.logInfId = logInfId;
		this.actionName = actionName;
		this.actionTime = actionTime;
		this.actionInf = actionInf;
		this.money = money;
	}

	public int getLogInfId()
	{
		return logInfId;
	}

	public void setLogInfId(int logInfId)
	{
		this.logInfId = logInfId;
	}

	public String getActionName()
	{
		return actionName;
	}

	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	public String getActionTime()
	{
		return actionTime;
	}

	public void setActionTime(String actionTime)
	{
		this.actionTime = actionTime;
	}

	public String getActionInf()
	{
		return actionInf;
	}

	public void setActionInf(String actionInf)
	{
		this.actionInf = actionInf;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}
}
