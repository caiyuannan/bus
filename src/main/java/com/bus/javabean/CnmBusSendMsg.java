package com.bus.javabean;

/**
 * 暂无用
 */
public class CnmBusSendMsg
{
	private int sum;
	private String msg;

	public CnmBusSendMsg()
	{
	}

	public CnmBusSendMsg(int sum, String msg)
	{
		this.sum = sum;
		this.msg = msg;
	}

	public int getSum()
	{
		return sum;
	}

	public void setSum(int sum)
	{
		this.sum = sum;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
}
