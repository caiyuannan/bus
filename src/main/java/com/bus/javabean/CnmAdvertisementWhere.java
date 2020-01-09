package com.bus.javabean;


import org.springframework.stereotype.Component;

@Component
public class CnmAdvertisementWhere
{

	private String cooperativeName;
	private String advertisingType;
	private String stateName;
	private int limit;
	private int page;

	public CnmAdvertisementWhere()
	{
	}

	public CnmAdvertisementWhere(String cooperativeName, String advertisingType, String stateName, int limit, int page)
	{
		this.cooperativeName = cooperativeName;
		this.advertisingType = advertisingType;
		this.stateName = stateName;
		this.limit = limit;
		this.page = page;
	}

	public String getCooperativeName()
	{
		return cooperativeName;
	}

	public void setCooperativeName(String cooperativeName)
	{
		this.cooperativeName = cooperativeName;
	}

	public String getAdvertisingType()
	{
		return advertisingType;
	}

	public void setAdvertisingType(String advertisingType)
	{
		this.advertisingType = advertisingType;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
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
