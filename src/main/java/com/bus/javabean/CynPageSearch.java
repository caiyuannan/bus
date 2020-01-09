package com.bus.javabean;

import java.util.List;

/**
 * 页面搜索
 */
public class CynPageSearch
{
	private String msg;
	private int count;
	private int code;
	private List<Object> data;

	public CynPageSearch()
	{
	}

	public CynPageSearch(String msg, int count, int code, List<Object> data)
	{
		this.msg = msg;
		this.count = count;
		this.code = code;
		this.data = data;
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

	public List<Object> getData()
	{
		return data;
	}

	public void setData(List<Object> data)
	{
		this.data = data;
	}
}
