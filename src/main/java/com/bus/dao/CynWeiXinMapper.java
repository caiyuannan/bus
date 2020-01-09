package com.bus.dao;

import com.bus.javabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * cyn:连接数据库的接口
 */
@Mapper
public interface CynWeiXinMapper
{
	public int addUser(CynUserBean cynUserBean);

	public int addUserFaceToken(CynUserBean cynUserBean);

	public CynUserBean findUserByAccount(@Param("account") String account);

	public CynUserBean findUserByFaceToken(@Param("faceToken") String faceToken);

	public List<CynCityBean> findAllCitys();

	public List<CynStationAndRouteOrderBean> findAllStationAndRoute(String location);

	public List<CynStationAndRouteBean> findAllStation(@Param("stationName") String stationName);

	public List<CynRouteSelectBean> getAllRoute(@Param("stationName") String stationName);

	public List<CynTestBean> testGetAllData(@Param("route") String route);

	public CynTestBean getXYByStationName(@Param("stationName") String stationName);

	public List<CynTestBean> getNearStation(@Param("route") String route);

	public int addAdvice(CynAdviceBean advice);

	public int addAdviceImage(CynAdviceBean advice);

	public List<CynStationAndRouteBean> getAllStationByCityName(@Param("cityName") String cityName);
}
