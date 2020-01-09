package com.bus.javabean;

/**
 * 新闻公告表
 */

public class CnmNewsBulletinBean
{
	private int newsBulletinId;
	private String newsBulletinTime;
	private String newsBulletinContent;
	private String newsBulletinType;
	private String newsBulletinTitle;
	private int stateId;
	private String stateName;
	public CnmNewsBulletinBean(int newsBulletinId, String newsBulletinTime, String newsBulletinContent, String newsBulletinType, String newsBulletinTitle, int stateId, String stateName)
	{
		this.newsBulletinId = newsBulletinId;
		this.newsBulletinTime = newsBulletinTime;
		this.newsBulletinContent = newsBulletinContent;
		this.newsBulletinType = newsBulletinType;
		this.newsBulletinTitle = newsBulletinTitle;
		this.stateId = stateId;
		this.stateName = stateName;
	}


	public CnmNewsBulletinBean()
	{
	}

	public int getNewsBulletinId()
	{
		return newsBulletinId;
	}

	public void setNewsBulletinId(int newsBulletinId)
	{
		this.newsBulletinId = newsBulletinId;
	}

	public String getNewsBulletinTime()
	{
		return newsBulletinTime;
	}

	public void setNewsBulletinTime(String newsBulletinTime)
	{
		this.newsBulletinTime = newsBulletinTime;
	}

	public String getNewsBulletinContent()
	{
		return newsBulletinContent;
	}

	public void setNewsBulletinContent(String newsBulletinContent)
	{
		this.newsBulletinContent = newsBulletinContent;
	}

	public String getNewsBulletinType()
	{
		return newsBulletinType;
	}

	public void setNewsBulletinType(String newsBulletinType)
	{
		this.newsBulletinType = newsBulletinType;
	}

	public String getNewsBulletinTitle()
	{
		return newsBulletinTitle;
	}

	public void setNewsBulletinTitle(String newsBulletinTitle)
	{
		this.newsBulletinTitle = newsBulletinTitle;
	}

	public int getStateId()
	{
		return stateId;
	}

	public void setStateId(int stateId)
	{
		this.stateId = stateId;
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
