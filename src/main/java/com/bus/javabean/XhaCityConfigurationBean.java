package com.bus.javabean;

import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 城市配置类
 * by谢海安
 */
@Component
public class XhaCityConfigurationBean {
	private int num;
	private String provinceName;
	private String cityName;
	private int stationCounts;
	private int routeCounts;

	public XhaCityConfigurationBean() {
	}

	public XhaCityConfigurationBean(int num, String provinceName, String cityName, int stationCounts, int routeCounts) {
		this.num = num;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.stationCounts = stationCounts;
		this.routeCounts = routeCounts;
	}

	@Override
	public String toString() {
		return "XhaCityConfigurationBean{" + "num=" + num + ", provinceName='" + provinceName + '\'' + ", cityName='" + cityName + '\'' + ", stationCounts=" + stationCounts + ", routeCounts=" + routeCounts + '}';
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	public int getStationCounts() {
		return stationCounts;
	}

	public void setStationCounts(int stationCounts) {
		this.stationCounts = stationCounts;
	}

	public int getRouteCounts() {
		return routeCounts;
	}

	public void setRouteCounts(int routeCounts) {
		this.routeCounts = routeCounts;
	}
}
