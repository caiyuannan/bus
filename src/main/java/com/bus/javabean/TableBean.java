package com.bus.javabean;

import java.util.List;

/**
 * layui数据表格类
 */
public class TableBean
{
	private String msg;
	private int count;
	private int code;
	private List<Object>date;


	public TableBean()
	{
	}

	public TableBean(String msg, int count, int code, List<Object> date)
	{
		this.msg = msg;
		this.count = count;
		this.code = code;
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "TableBean{" + "msg='" + msg + '\'' + ", count=" + count + ", code=" + code + ", date=" + date + '}';
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public List<Object> getDate()
	{
		return date;
	}

	public void setDate(List<Object> date)
	{
		this.date = date;
	}
}
