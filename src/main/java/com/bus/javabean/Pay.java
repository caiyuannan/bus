package com.bus.javabean;

public class Pay
{
	private Integer out_trade_no;
	private long trade_no;
	private String total_amount;

	public Pay()
	{
	}

	public Pay(Integer out_trade_no, long trade_no, String total_amount)
	{
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
		this.total_amount = total_amount;
	}

	public String getTotal_amount()
	{
		return total_amount;
	}

	public void setTotal_amount(String total_amount)
	{
		this.total_amount = total_amount;
	}

	public Integer getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(Integer out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public long getTrade_no()
	{
		return trade_no;
	}

	public void setTrade_no(long trade_no)
	{
		this.trade_no = trade_no;
	}
}
