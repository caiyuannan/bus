package com.bus.javabean;
/**路线配置表*/
public class XhaRouteConfigurationBean {
	private String routeName;
	private int stationCount;//经过站点数
	private int shiftCount;//每日运行班次
	private int busCount;//线路在用车辆

	public XhaRouteConfigurationBean() {
	}

	public XhaRouteConfigurationBean(String routeName, int stationCount, int shiftCount, int busCount) {
		this.routeName = routeName;
		this.stationCount = stationCount;
		this.shiftCount = shiftCount;
		this.busCount = busCount;
	}

	@Override
	public String toString() {
		return "XhaRouteConfigurationBean{" + "routeName='" + routeName + '\'' + ", stationCount=" + stationCount + ", shiftCount=" + shiftCount + ", busCount=" + busCount + '}';
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public int getStationCount() {
		return stationCount;
	}

	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}

	public int getShiftCount() {
		return shiftCount;
	}

	public void setShiftCount(int shiftCount) {
		this.shiftCount = shiftCount;
	}

	public int getBusCount() {
		return busCount;
	}

	public void setBusCount(int busCount) {
		this.busCount = busCount;
	}
}
