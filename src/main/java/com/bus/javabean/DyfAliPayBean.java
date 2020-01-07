package com.bus.javabean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 40651
 * 用户订单充值表
 */
public class DyfAliPayBean
{
	private Integer orderId;
	private Integer orderMoney;
	private Integer orderType;
	private String orderPhoneNum;
	private String nowDate;
	public DyfAliPayBean()
	{
	}

	public DyfAliPayBean(Integer orderMoney, Integer orderType, String orderPhoneNum, String nowDate)
	{
		this.orderMoney = orderMoney;
		this.orderType = orderType;
		this.orderPhoneNum = orderPhoneNum;
		this.nowDate = nowDate;
	}

	public DyfAliPayBean(Integer orderMoney, Integer orderType, String orderPhoneNum)
	{
		this.orderMoney = orderMoney;
		this.orderType = orderType;
		this.orderPhoneNum = orderPhoneNum;
	}

	public DyfAliPayBean(Integer orderId, Integer orderMoney, Integer orderType, String orderPhoneNum)
	{
		this.orderId = orderId;
		this.orderMoney = orderMoney;
		this.orderType = orderType;
		this.orderPhoneNum = orderPhoneNum;
	}

	public String getNowDate()
	{
		return nowDate;
	}

	public void setNowDate(String nowDate)
	{
		this.nowDate = nowDate;
	}

	public String getOrderPhoneNum()
	{
		return orderPhoneNum;
	}

	public void setOrderPhoneNum(String orderPhoneNum)
	{
		this.orderPhoneNum = orderPhoneNum;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public Integer getOrderMoney()
	{
		return orderMoney;
	}

	public void setOrderMoney(Integer orderMoney)
	{
		this.orderMoney = orderMoney;
	}

	public Integer getOrderType()
	{
		return orderType;
	}

	public void setOrderType(Integer orderType)
	{
		this.orderType = orderType;
	}
}
