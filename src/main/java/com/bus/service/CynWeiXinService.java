package com.bus.service;

import com.bus.dao.CynWeiXinMapper;
import com.bus.javabean.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 微信小程序：接口实现类
 */
@Service
public class CynWeiXinService
{
	@Resource
	private CynWeiXinMapper cynWeiXinMapper;

	/**
	 * 获取到数据库的所有城市
	 */
	@Transactional
	public List<CynCityBean> findAllCitys(){
		return cynWeiXinMapper.findAllCitys();
	}
	/**
	 * 获取到数据库的所有站点和线路
	 */
	@Transactional
	public List<CynStationAndRouteOrderBean> findAllStationAndRoute(String location){
		return cynWeiXinMapper.findAllStationAndRoute(location);
	}
	/**
	 * 获取所有的站点
	 */
	@Transactional
	public List<CynStationAndRouteBean> findAllStation(String stationName){
		return cynWeiXinMapper.findAllStation(stationName);
	}

	/**
	 * 根据站点获取所有的线路
	 */
	@Transactional
	public List<CynRouteSelectBean> getAllRoute(String stationName){
		return cynWeiXinMapper.getAllRoute(stationName);
	}
	@Transactional
	public List<CynTestBean> testGetAllData(String route){
		return cynWeiXinMapper.testGetAllData(route);
	}

	/**
	 * 根据站点名，获取X轴和Y轴
	 */
	@Transactional
	public CynTestBean getXYByStationName(String stationName){
		return cynWeiXinMapper.getXYByStationName(stationName);
	}

}

