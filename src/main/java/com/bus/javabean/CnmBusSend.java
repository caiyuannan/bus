package com.bus.javabean;

import java.util.List;

public class CnmBusSend
{
	private List listSum;
	private List listmsg;
	private int startPost;
	private List<XhaRouteOrderBean> listRoute;
	private List<DyfBusShfitBean> listBusShfit;
	private String msg;

	public List getListSum()
	{
		return listSum;
	}

	public void setListSum(List listSum)
	{
		this.listSum = listSum;
	}

	public List getListmsg()
	{
		return listmsg;
	}

	public void setListmsg(List listmsg)
	{
		this.listmsg = listmsg;
	}

	public int getStartPost()
	{
		return startPost;
	}

	public void setStartPost(int startPost)
	{
		this.startPost = startPost;
	}

	public List<XhaRouteOrderBean> getListRoute()
	{
		return listRoute;
	}

	public void setListRoute(List<XhaRouteOrderBean> listRoute)
	{
		this.listRoute = listRoute;
	}

	public List<DyfBusShfitBean> getListBusShfit()
	{
		return listBusShfit;
	}

	public void setListBusShfit(List<DyfBusShfitBean> listBusShfit)
	{
		this.listBusShfit = listBusShfit;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public CnmBusSend()
	{
	}

	public CnmBusSend(List listSum, List listmsg, int startPost, List<XhaRouteOrderBean> listRoute, List<DyfBusShfitBean> listBusShfit, String msg)
	{
		this.listSum = listSum;
		this.listmsg = listmsg;
		this.startPost = startPost;
		this.listRoute = listRoute;
		this.listBusShfit = listBusShfit;
		this.msg = msg;
	}

}
