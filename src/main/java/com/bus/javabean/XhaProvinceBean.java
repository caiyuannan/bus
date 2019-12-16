package com.bus.javabean;

/**
 * 省份表
 * by 谢海安
 */
public class XhaProvinceBean {
	/**省份id*/
	private int provinceId;
	/**省份名字*/
	private String provinceName;
	/**城市表*/
	private XhaCityBean cityBean;

	public XhaProvinceBean() {
	}

	public XhaProvinceBean(int provinceId, String provinceName, XhaCityBean cityBean) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.cityBean = cityBean;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public XhaCityBean getCityBean() {
		return cityBean;
	}

	public void setCityBean(XhaCityBean cityBean) {
		this.cityBean = cityBean;
	}

	@Override
	public String toString() {
		return "XhaProvinceBean{" + "provinceId=" + provinceId + ", provinceName='" + provinceName + '\'' + ", cityBean=" + cityBean + '}';
	}
}
