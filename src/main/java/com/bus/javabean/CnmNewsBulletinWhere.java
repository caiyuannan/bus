package com.bus.javabean;


import org.springframework.stereotype.Component;

@Component
public class CnmNewsBulletinWhere
{

	private String newsBulletinType;
	private String newsBulletinTime;
	private String newsBulletinTitle;
	private int limit;
	private int page;

	public CnmNewsBulletinWhere(String newsBulletinType, String newsBulletinTime, String newsBulletinTitle, int limit, int page)
	{
		this.newsBulletinType = newsBulletinType;
		this.newsBulletinTime = newsBulletinTime;
		this.newsBulletinTitle = newsBulletinTitle;
		this.limit = limit;
		this.page = page;
	}

	public CnmNewsBulletinWhere()
	{
	}

	public String getNewsBulletinType()
	{
		return newsBulletinType;
	}

	public void setNewsBulletinType(String newsBulletinType)
	{
		this.newsBulletinType = newsBulletinType;
	}

	public String getNewsBulletinTime()
	{
		return newsBulletinTime;
	}

	public void setNewsBulletinTime(String newsBulletinTime)
	{
		this.newsBulletinTime = newsBulletinTime;
	}

	public String getNewsBulletinTitle()
	{
		return newsBulletinTitle;
	}

	public void setNewsBulletinTitle(String newsBulletinTitle)
	{
		this.newsBulletinTitle = newsBulletinTitle;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}
}
