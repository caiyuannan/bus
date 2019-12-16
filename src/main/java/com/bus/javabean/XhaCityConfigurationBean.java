package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 城市配置类
 * by谢海安
 */
@Component
public class XhaCityConfigurationBean {
	/**序号*/
	private int rowNumber;
	/**省份名字*/
	private String provinceName;
	/**城市名字*/
	private String cityName;
	/**站点数*/
	private int stationCount;
	/**线路数*/
	private int routeCount;

	public XhaCityConfigurationBean() {
	}

	public XhaCityConfigurationBean(int rowNumber, String provinceName, String cityName, int stationCount, int routeCount) {
		this.rowNumber = rowNumber;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.stationCount = stationCount;
		this.routeCount = routeCount;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getStationCount() {
		return stationCount;
	}

	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}

	public int getRouteCount() {
		return routeCount;
	}

	public void setRouteCount(int routeCount) {
		this.routeCount = routeCount;
	}

	@Override
	public String toString() {
		return "XhaCityConfigurationBean" +
				"{" + "rowNumber=" + rowNumber + ", provinceName='" + provinceName + '\'' + ", cityName='" + cityName + '\'' + ", stationCount=" + stationCount + ", routeCount=" + routeCount + '}';
	}
}
