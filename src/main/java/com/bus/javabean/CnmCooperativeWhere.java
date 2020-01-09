package com.bus.javabean;


import org.springframework.stereotype.Component;

@Component
public class CnmCooperativeWhere
{

	private String cooperativeName;
	private String cooperativeArea;
	private String cooperativeType;
	private int limit;
	private int page;


	public String getCooperativeName()
	{
		return cooperativeName;
	}

	public void setCooperativeName(String cooperativeName)
	{
		this.cooperativeName = cooperativeName;
	}

	public String getCooperativeArea()
	{
		return cooperativeArea;
	}

	public void setCooperativeArea(String cooperativeArea)
	{
		this.cooperativeArea = cooperativeArea;
	}

	public String getCooperativeType()
	{
		return cooperativeType;
	}

	public void setCooperativeType(String cooperativeType)
	{
		this.cooperativeType = cooperativeType;
	}

	public CnmCooperativeWhere()
	{
	}


	public CnmCooperativeWhere(String cooperativeName, String cooperativeArea, String cooperativeType, int limit, int page)
	{
		this.cooperativeName = cooperativeName;
		this.cooperativeArea = cooperativeArea;
		this.cooperativeType = cooperativeType;
		this.limit = limit;
		this.page = page;
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
