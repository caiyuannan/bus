package com.bus.javabean;

/**
 * 站点表
 * by 谢海安
 */
public class XhaStationBean {
	private int stationId;
	private int cityId;
	private String stationName;
	private String stationLon;
	private String stationLat;
	private int stateId;

	public XhaStationBean() {
	}

	public XhaStationBean(int stationId, int cityId, String stationName, String stationLon, String stationLat, int stateId) {
		this.stationId = stationId;
		this.cityId = cityId;
		this.stationName = stationName;
		this.stationLon = stationLon;
		this.stationLat = stationLat;
		this.stateId = stateId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
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

	@Override
	public String toString() {
		return "XhaStationBean{" + "stationId=" + stationId + ", cityId=" + cityId + ", stationName='" + stationName + '\'' + ", stationLon='" + stationLon + '\'' + ", stationLat='" + stationLat + '\'' + ", stateId=" + stateId + '}';
	}
}
