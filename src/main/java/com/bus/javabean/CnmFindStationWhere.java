package com.bus.javabean;

import java.util.List;

public class CnmFindStationWhere
{
	private String startStop;
	private String endStop;
	private String routeName;
	private int endOrder;
	private int startOrder;
	private String msg;
	private List<XhaRouteOrderBean> list;
	private List<XhaStationBean> listStation;
	private int sum;
	private String startStopX;
	private String startStopY;

	public CnmFindStationWhere(String startStop, String endStop, String routeName, int endOrder, int startOrder, String msg, List<XhaRouteOrderBean> list, List<XhaStationBean> listStation, int sum, String startStopX, String startStopY)
	{
		this.startStop = startStop;
		this.endStop = endStop;
		this.routeName = routeName;
		this.endOrder = endOrder;
		this.startOrder = startOrder;
		this.msg = msg;
		this.list = list;
		this.listStation = listStation;
		this.sum = sum;
		this.startStopX = startStopX;
		this.startStopY = startStopY;
	}

	public CnmFindStationWhere()
	{
	}

	public String getStartStop()
	{
		return startStop;
	}

	public void setStartStop(String startStop)
	{
		this.startStop = startStop;
	}

	public String getEndStop()
	{
		return endStop;
	}

	public void setEndStop(String endStop)
	{
		this.endStop = endStop;
	}

	public String getRouteName()
	{
		return routeName;
	}

	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}

	public int getEndOrder()
	{
		return endOrder;
	}

	public void setEndOrder(int endOrder)
	{
		this.endOrder = endOrder;
	}

	public int getStartOrder()
	{
		return startOrder;
	}

	public void setStartOrder(int startOrder)
	{
		this.startOrder = startOrder;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public List<XhaRouteOrderBean> getList()
	{
		return list;
	}

	public void setList(List<XhaRouteOrderBean> list)
	{
		this.list = list;
	}

	public List<XhaStationBean> getListStation()
	{
		return listStation;
	}

	public void setListStation(List<XhaStationBean> listStation)
	{
		this.listStation = listStation;
	}

	public int getSum()
	{
		return sum;
	}

	public void setSum(int sum)
	{
		this.sum = sum;
	}

	public String getStartStopX()
	{
		return startStopX;
	}

	public void setStartStopX(String startStopX)
	{
		this.startStopX = startStopX;
	}

	public String getStartStopY()
	{
		return startStopY;
	}

	public void setStartStopY(String startStopY)
	{
		this.startStopY = startStopY;
	}
}
