package com.bus.javabean;

public class CynLogInf
{
	private int logInfId;
	private String actionName;
	private String actionTime;
	private String actionInf;
	private double money;

	public CynLogInf()
	{
	}

	public CynLogInf(int logInfId, String actionName, String actionTime, String actionInf, double money)
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
