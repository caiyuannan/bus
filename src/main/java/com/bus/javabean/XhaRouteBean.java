package com.bus.javabean;

import java.util.List;

/**
 * 路线表
 * by 谢海安
 */
public class XhaRouteBean {
	private int routeId;
	private String routeName;
	private int routeFee;
	private int routeTime;
	private int stateId;
	private int cityId;

	public XhaRouteBean() {
	}

	public XhaRouteBean(int routeId, String routeName, int routeFee, int routeTime, int stateId, int cityId) {
		this.routeId = routeId;
		this.routeName = routeName;
		this.routeFee = routeFee;
		this.routeTime = routeTime;
		this.stateId = stateId;
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "XhaRouteBean{" + "routeId=" + routeId + ", routeName='" + routeName + '\'' + ", routeFee=" + routeFee + ", routeTime=" + routeTime + ", stateId=" + stateId + ", cityId=" + cityId + '}';
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public int getRouteFee() {
		return routeFee;
	}

	public void setRouteFee(int routeFee) {
		this.routeFee = routeFee;
	}

	public int getRouteTime() {
		return routeTime;
	}

	public void setRouteTime(int routeTime) {
		this.routeTime = routeTime;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}
