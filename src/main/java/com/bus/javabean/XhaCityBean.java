package com.bus.javabean;

import org.springframework.stereotype.Component;

/**
 * 城市类
 * by谢海安
 */
@Component
public class XhaCityBean {
	/**城市id*/
	private int cityId;
	/**城市名字*/
	private String cityName;

	public XhaCityBean() {
	}

	public XhaCityBean(int cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "XhaCityBean{" + "cityId=" + cityId + ", cityName='" + cityName + '\'' + '}';
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
