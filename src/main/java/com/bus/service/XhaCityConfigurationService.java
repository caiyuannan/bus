package com.bus.service;

import com.bus.javabean.XhaProvinceBean;
import com.bus.javabean.XhaRouteBean;
import com.bus.javabean.XhaStationBean;
import com.bus.dao.XhaCityConfigurationMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 城市配置接口实现类
 * by 谢海安
 */
@Service
public class XhaCityConfigurationService{
	@Resource
	private XhaCityConfigurationMapper cityConfigurationMapper;

	/**
	 * 查找城市
	 * @return
	 */
	public List<XhaProvinceBean> findCity(String provinceName, String cityName,int startPage,int limitPage) {
		return cityConfigurationMapper.findCity(provinceName,cityName,startPage,limitPage);
	}

	/**
	 * 查找站点
	 */
	public List<XhaStationBean> findStation(int cityId){
		return cityConfigurationMapper.findStation(cityId);
	}

	/**
	 * 查找线路
	 */
	public List<XhaRouteBean> findRoute(int cityId){
		return cityConfigurationMapper.findRoute(cityId);
	}
}
