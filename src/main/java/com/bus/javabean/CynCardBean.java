package com.bus.javabean;

/**
 * 卡片基础类
 * cardId:自增ID
 * cardNum:卡号
 * stateName:状态
 * userName:使用人
 */

public class CynCardBean
{
	private int cardId;
	private String cardNum;
	private String stateName;
	private String userName;

	public CynCardBean()
	{
	}

	public CynCardBean(int cardId, String cardNum, String stateName, String userName)
	{
		this.cardId = cardId;
		this.cardNum = cardNum;
		this.stateName = stateName;
		this.userName = userName;
	}

	public int getCardId()
	{
		return cardId;
	}

	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}

	public String getCardNum()
	{
		return cardNum;
	}

	public void setCardNum(String cardNum)
	{
		this.cardNum = cardNum;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
