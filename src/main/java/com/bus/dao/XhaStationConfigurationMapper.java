package com.bus.dao;
import com.bus.javabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市站点配置mapper
 * by 谢海安
 */
@Mapper
public interface XhaStationConfigurationMapper {

	/**通过省份id查询所有的城市*/
	public List<XhaCityBean> queryCityByProvinceId(int provinceId);

	/**通过城市名字查找站点*/
	public List<XhaStationBean> queryStationByProvinceName(XhaPageBean pageBean);

	/**查找站点带分页*/
	public List<XhaStationBean> queryStationOnLimit(XhaPageBean pageBean);

	/**删除站点*/
	public int deleteStation(int stationId);

	/**查询站点是否已经存在*/
	public XhaStationBean seleteStationIsExisted(@Param("stationName") String stationName,@Param("stateId") int stateId);

	/**把被逻辑删除的站点重新添加回来*/
	public int updateStation(XhaStationBean stationBean);

	/**添加新站点*/
	public int addStation(XhaStationBean stationBean);

	/**根据站点的变动及时更新城市配置表添加数据*/
	public int addStationCount(@Param("cityName") String cityName);

	/**根据站点的变动及时更新城市配置表减少数据*/
	public int reduceStationCount(@Param("cityName") String cityName);

	/**根据站点id查询站点信息*/
	public XhaStationBean queryStationById(int stationId);
}
