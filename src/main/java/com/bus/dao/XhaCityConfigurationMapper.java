package com.bus.dao;
import com.bus.javabean.XhaCityConfigurationBean;
import com.bus.javabean.XhaPageBean;
import com.bus.javabean.XhaProvinceBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 城市配置mapper
 * by 谢海安
 */
@Mapper
public interface XhaCityConfigurationMapper {

	/**查询城市配置表,没有分页*/
	public List<XhaCityConfigurationBean> queryCityConfiguration(XhaPageBean pageBean);

	/**查询城市配置表,带分页*/
	public List<XhaCityConfigurationBean> queryCityConfigurationOnLimit(XhaPageBean pageBean);

	/**删除城市配置表*/
	public int deleteCityConfiguration(int cityConfigurationId);

	/**查询省份*/
	public List<XhaProvinceBean> queryProvince();

	/**查询城市配置表是否已经存在相应的省份、城市*/
	public XhaProvinceBean queryProvinceCityIsExited(@Param("provinceName") String provinceName,@Param("cityName") String cityName);

	/**查询城市配置表是否已经存在相应的省份、城市,并被逻辑删除*/
	public XhaProvinceBean queryProvinceCityIsDeleted(@Param("provinceName") String provinceName,@Param("cityName") String cityName);

	/**把省份城市逻辑删除的状态2改成1*/
	public int updateProvinceCityState(@Param("provinceName") String provinceName,@Param("cityName") String cityName);

	/**添加省份城市*/
	public int addProvinceCity(@Param("provinceName") String provinceName,@Param("cityName") String cityName);
}
