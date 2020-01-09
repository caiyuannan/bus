package com.bus.service;

import com.bus.dao.CynWeiXinMapper;
import com.bus.javabean.*;
import org.apache.ibatis.annotations.Param;
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
	 * 微信端用户注册
	 */
	@Transactional
	public int addUser(CynUserBean cynUserBean){
		return cynWeiXinMapper.addUser(cynUserBean);
	}
	/**
	 * 微信端用户注册增加图片
	 */
	@Transactional
	public int addUserFaceToken(CynUserBean cynUserBean){
		return cynWeiXinMapper.addUserFaceToken(cynUserBean);
	}

	/**
	 * 微信端用户登录
	 */
	@Transactional
	public CynUserBean findUserByAccount(String account){
		return cynWeiXinMapper.findUserByAccount(account);
	}

	/**
	 * 微信端用户人脸识别登录
	 */
	@Transactional
	public CynUserBean findUserByFaceToken(String faceToken){
		return cynWeiXinMapper.findUserByFaceToken(faceToken);
	}

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

	/**
	 * 获取所需站点
	 */
	@Transactional
	public List<CynTestBean> getNearStation(String route){
		return cynWeiXinMapper.getNearStation(route);
	}

	/**
	 * 添加到用户反馈表
	 */
	@Transactional
	public int addAdvice(CynAdviceBean advice){
		return cynWeiXinMapper.addAdvice(advice);
	}
	/**
	 * 添加到用户反馈表关联的图片表
	 */
	@Transactional
	public int addAdviceImage(CynAdviceBean advice){
		return cynWeiXinMapper.addAdviceImage(advice);
	}

	/**
	 * 通过城市名，获取到所有站点的经纬度和站点名
	 */
	@Transactional
	public List<CynStationAndRouteBean> getAllStationByCityName(@Param("cityName") String cityName){
		return cynWeiXinMapper.getAllStationByCityName(cityName);
	}
}

