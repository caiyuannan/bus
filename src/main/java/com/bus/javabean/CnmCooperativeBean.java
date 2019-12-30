package com.bus.javabean;

import org.springframework.stereotype.Component;

/*
* 合作商表
* 陈南明
*
* */
@Component
public class CnmCooperativeBean
{


	private int cooperativeId ;
	private String cooperativeName;
	private String cooperativeType;
	private int cooperativeX;
	private int cooperativeY;
	private String cooperativeCoordinate;
	private String cooperativeArea;
	private int stateId;
	private String stateName;


	public CnmCooperativeBean(int cooperativeId, String cooperativeName, String cooperativeType, int cooperativeX, int cooperativeY, String cooperativeCoordinate, String cooperativeArea, int stateId, String stateName)
	{
		this.cooperativeId = cooperativeId;
		this.cooperativeName = cooperativeName;
		this.cooperativeType = cooperativeType;
		this.cooperativeX = cooperativeX;
		this.cooperativeY = cooperativeY;
		this.cooperativeCoordinate = cooperativeCoordinate;
		this.cooperativeArea = cooperativeArea;
		this.stateId = stateId;
		this.stateName = stateName;
	}

	public CnmCooperativeBean()
	{
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public int getCooperativeId()
	{
		return cooperativeId;
	}

	public void setCooperativeId(int cooperativeId)
	{
		this.cooperativeId = cooperativeId;
	}

	public String getCooperativeName()
	{
		return cooperativeName;
	}

	public void setCooperativeName(String cooperativeName)
	{
		this.cooperativeName = cooperativeName;
	}

	public String getCooperativeType()
	{
		return cooperativeType;
	}

	public void setCooperativeType(String cooperativeType)
	{
		this.cooperativeType = cooperativeType;
	}

	public int getCooperativeX()
	{
		return cooperativeX;
	}

	public void setCooperativeX(int cooperativeX)
	{
		this.cooperativeX = cooperativeX;
	}

	public int getCooperativeY()
	{
		return cooperativeY;
	}

	public void setCooperativeY(int cooperativeY)
	{
		this.cooperativeY = cooperativeY;
	}

	public String getCooperativeCoordinate()
	{
		return cooperativeCoordinate;
	}

	public void setCooperativeCoordinate(String cooperativeCoordinate)
	{
		this.cooperativeCoordinate = cooperativeCoordinate;
	}

	public String getCooperativeArea()
	{
		return cooperativeArea;
	}

	public void setCooperativeArea(String cooperativeArea)
	{
		this.cooperativeArea = cooperativeArea;
	}

	public int getStateId()
	{
		return stateId;
	}

	public void setStateId(int stateId)
	{
		this.stateId = stateId;
	}

	@Override
	public String toString()
	{
		return "CnmCooperativeBean{" + "cooperativeId=" + cooperativeId + ", cooperativeName='" + cooperativeName + '\'' + ", cooperativeType='" + cooperativeType + '\'' + ", cooperativeX=" + cooperativeX + ", cooperativeY=" + cooperativeY + ", cooperativeCoordinate='" + cooperativeCoordinate + '\'' + ", cooperativeArea='" + cooperativeArea + '\'' + ", stateId=" + stateId + '}';
	}
}
