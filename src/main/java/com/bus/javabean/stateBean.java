package com.bus.javabean;

import java.math.BigDecimal;


/**
 * @author 40651
 * 状态bean
 */
public class stateBean
{
	private BigDecimal stateId;
	private String stateName;
	private Integer stateUserId;

	public stateBean(BigDecimal stateId, String stateName, Integer stateUserId)
	{
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateUserId = stateUserId;
	}

	public stateBean()
	{
	}

	public BigDecimal getStateId()
	{
		return stateId;
	}

	public void setStateId(BigDecimal stateId)
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

	public Integer getStateUserId()
	{
		return stateUserId;
	}

	public void setStateUserId(Integer stateUserId)
	{
		this.stateUserId = stateUserId;
	}

	@Override
	public String toString()
	{
		return "stateBean{" + "stateId=" + stateId + ", stateName='" + stateName + '\'' + ", stateUserId=" + stateUserId + '}';
	}
}
