package com.bus.javabean;

import java.util.List;

/**
 * 省份城市表
 */
public class DyfProvince
{
	//省份表id
	private Integer provinceId;
	//省份名称
	private String provinceName;
	//省份所拥有的城市
	private List<DyfCityBean> city;

	public DyfProvince()
	{
	}

	public DyfProvince(Integer provinceId, String provinceName, List<DyfCityBean> city)
	{
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.city = city;
	}

	@Override
	public String toString()
	{
		return "DyfProvince{" + "provinceId=" + provinceId + ", provinceName='" + provinceName + '\'' + ", city=" + city + '}';
	}

	public Integer getProvinceId()
	{
		return provinceId;
	}

	public void setProvinceId(Integer provinceId)
	{
		this.provinceId = provinceId;
	}

	public String getProvinceName()
	{
		return provinceName;
	}

	public void setProvinceName(String provinceName)
	{
		this.provinceName = provinceName;
	}

	public List<DyfCityBean> getCity()
	{
		return city;
	}

	public void setCity(List<DyfCityBean> city)
	{
		this.city = city;
	}
}
