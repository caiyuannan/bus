package com.bus.javabean;

import java.util.List;

/**
 * 省份表
 * by 谢海安
 */
public class XhaProvinceBean {
	/**省份id*/
	private int provinceId;
	/**省份名字*/
	private String provinceName;
	/**城市集合*/
	private List citys;

	public XhaProvinceBean() {
	}

	public XhaProvinceBean(int provinceId, String provinceName, List citys) {
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.citys = citys;
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

	public List getCitys() {
		return citys;
	}

	public void setCitys(List citys) {
		this.citys = citys;
	}

	@Override
	public String toString() {
		return "XhaProvinceBean{" + "provinceId=" + provinceId + ", provinceName='" + provinceName + '\'' + ", citys=" + citys + '}';
	}
}
