package com.bus.javabean;

import java.util.List;

/**
 * 分页封装类
 * by 谢海安
 */
public class XhaPageBean {
	private String provinceName;
	private String cityName;
	private String stationName;
	private int page;
	private int start;
	private int end;
	private int count;
	private int limit;
	private List list;
	private String routeName;

	public XhaPageBean() {
	}

	/** 城市配置表*/
	public XhaPageBean(String provinceName, String cityName, int page, int limit) {
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.page = page;
		this.limit = limit;
	}

	/**站点配置表*/
	public XhaPageBean(int page, int limit,String cityName,String stationName){
		this.page = page;
		this.limit = limit;
		this.cityName = cityName;
		this.stationName = stationName;
	}

	public XhaPageBean(String cityName, String routeName, String stationName, int page, int limit) {
		this.cityName = cityName;
		this.routeName = routeName;
		this.stationName = stationName;
		this.page = page;
		this.limit = limit;
	}

	public XhaPageBean(String provinceName, String cityName, int page, int start, int end, int count, int limit, List list) {
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.page = page;
		this.start = start;
		this.end = end;
		this.count = count;
		this.limit = limit;
		this.list = list;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		start = (page-1)*limit;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		end = page*limit;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@Override
	public String toString() {
		return "XhaPageBean{" + "provinceName='" + provinceName + '\'' + ", cityName='" + cityName + '\'' + ", page=" + page + ", start=" + start + ", end=" + end + ", count=" + count + ", limit=" + limit + ", list=" + list + '}';
	}
}
