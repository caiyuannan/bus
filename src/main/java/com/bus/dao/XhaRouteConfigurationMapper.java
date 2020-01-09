package com.bus.dao;
import com.bus.javabean.XhaRouteOrderBean;
import com.bus.javabean.XhaStationBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市路线配置mapper
 * by 谢海安
 */
@Mapper
public interface XhaRouteConfigurationMapper {

	/**查询所有的路线名*/
	public List<XhaRouteOrderBean> queryAllRoute();

	/**通过路线名查询路线站点*/
	public List<XhaRouteOrderBean> queryStationByRouteName(@Param("routeName") String routeName);

	/**通过路线名查询路线站点具体信息*/
	public List<XhaStationBean> queryStationInfo(@Param("routeName") String routeName);

	/**从路线中删除站点*/
	public int deleteStation(XhaRouteOrderBean routeOrderBean);

	/**删除后对路线顺序进行从新排列*/
	public int realignRoute(XhaRouteOrderBean routeOrderBean);

	/**查询所有站点*/
	public List<XhaStationBean> queryAllStation();

	//	cnm通过站点名查询站点信息
	public XhaStationBean queryStation(@Param("stationName") String stationName);
}
