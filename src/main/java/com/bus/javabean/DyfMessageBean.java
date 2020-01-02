package com.bus.javabean;

import javax.print.attribute.standard.PrinterURI;

public class DyfMessageBean
{
	private String status;
	private String send_id;
	private String fee;
	private String sms_credits;
	private String transactional_sms_credits;
	private int num;
	public DyfMessageBean()
	{
	}

	public DyfMessageBean(String status, String send_id, String fee, String sms_credits, String transactional_sms_credits, int num)
	{
		this.status = status;
		this.send_id = send_id;
		this.fee = fee;
		this.sms_credits = sms_credits;
		this.transactional_sms_credits = transactional_sms_credits;
		this.num = num;
	}

	@Override
	public String toString()
	{
		return "DyfMessageBean{" + "status='" + status + '\'' + ", send_id='" + send_id + '\'' + ", fee='" + fee + '\'' + ", sms_credits='" + sms_credits + '\'' + ", transactional_sms_credits='" + transactional_sms_credits + '\'' + ", num=" + num + '}';
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getSend_id()
	{
		return send_id;
	}

	public void setSend_id(String send_id)
	{
		this.send_id = send_id;
	}

	public String getFee()
	{
		return fee;
	}

	public void setFee(String fee)
	{
		this.fee = fee;
	}

	public String getSms_credits()
	{
		return sms_credits;
	}

	public void setSms_credits(String sms_credits)
	{
		this.sms_credits = sms_credits;
	}

	public String getTransactional_sms_credits()
	{
		return transactional_sms_credits;
	}

	public void setTransactional_sms_credits(String transactional_sms_credits)
	{
		this.transactional_sms_credits = transactional_sms_credits;
	}
}
