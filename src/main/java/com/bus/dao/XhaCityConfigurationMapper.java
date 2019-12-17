
package com.bus.dao;
import com.bus.javabean.XhaCityBean;
import com.bus.javabean.XhaProvinceBean;
import com.bus.javabean.XhaRouteBean;
import com.bus.javabean.XhaStationBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市配置mapper
 * by 谢海安
 */
@Mapper
public interface XhaCityConfigurationMapper {
	/**查询城市*/
	public List<XhaProvinceBean> findCity(@Param("provinceName") String provinceName, @Param("cityName") String cityName,@Param("startPage") int startPage,@Param("limitPage") int limitPage);

	/**查询站点*/
	public List<XhaStationBean> findStation(int cityId);

	/**查询路线*/
	public List<XhaRouteBean> findRoute(int cityId);
}
