package com.bus.javabean;

/**
 * 路线顺序表
 * by 谢海安
 */
public class XhaRouteOrderBean {
	private int routeOrderId;
	private String routeName;
	private String stationName;
	/**路线顺序*/
	private int routeOrderNumber;
	/**路线站点是否是起点终点*/
	private int isStartEnd;

	public XhaRouteOrderBean() {
	}

	public XhaRouteOrderBean(int routeOrderId, String routeName, String stationName, int routeOrderNumber, int isStartEnd) {
		this.routeOrderId = routeOrderId;
		this.routeName = routeName;
		this.stationName = stationName;
		this.routeOrderNumber = routeOrderNumber;
		this.isStartEnd = isStartEnd;
	}

	@Override
	public String toString() {
		return "XhaRouteOrderBean{" + "routeOrderId=" + routeOrderId + ", routeName='" + routeName + '\'' + ", stationName='" + stationName + '\'' + ", routeOrderNumber=" + routeOrderNumber + ", isStartEnd=" + isStartEnd + '}';
	}

	public int getRouteOrderId() {
		return routeOrderId;
	}

	public void setRouteOrderId(int routeOrderId) {
		this.routeOrderId = routeOrderId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getRouteOrderNumber() {
		return routeOrderNumber;
	}

	public void setRouteOrderNumber(int routeOrderNumber) {
		this.routeOrderNumber = routeOrderNumber;
	}

	public int getIsStartEnd() {
		return isStartEnd;
	}

	public void setIsStartEnd(int isStartEnd) {
		this.isStartEnd = isStartEnd;
	}
}
