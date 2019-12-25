package com.bus.javabean;

/**
 * 站点表
 * by 谢海安
 */
public class XhaStationBean {
	private int stationId;
	private String cityName;
	private String stationName;
	private String stationLon;//x
	private String stationLat;//y
	private int stateId;
	private String routes;

	public XhaStationBean() {
	}

	public XhaStationBean(String cityName, String stationName, String stationLon, String stationLat) {
		this.cityName = cityName;
		this.stationName = stationName;
		this.stationLon = stationLon;
		this.stationLat = stationLat;
	}

	public XhaStationBean(int stationId, String cityName, String stationName, String stationLon, String stationLat, int stateId, String routes) {
		this.stationId = stationId;
		this.cityName = cityName;
		this.stationName = stationName;
		this.stationLon = stationLon;
		this.stationLat = stationLat;
		this.stateId = stateId;
		this.routes = routes;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationLon() {
		return stationLon;
	}

	public void setStationLon(String stationLon) {
		this.stationLon = stationLon;
	}

	public String getStationLat() {
		return stationLat;
	}

	public void setStationLat(String stationLat) {
		this.stationLat = stationLat;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "XhaStationBean{" + "stationId=" + stationId + ", cityName='" + cityName + '\'' + ", stationName='" + stationName + '\'' + ", stationLon='" + stationLon + '\'' + ", stationLat='" + stationLat + '\'' + ", stateId=" + stateId + ", routes='" + routes + '\'' + '}';
	}
}
