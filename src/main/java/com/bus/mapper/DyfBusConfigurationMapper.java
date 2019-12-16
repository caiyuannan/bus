package com.bus.mapper;

import com.bus.javabean.DyfBusBean;
import com.bus.javabean.DyfProvince;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DyfBusConfigurationMapper
{
	//查询所有省份以及省份所包含的城市
	public List<DyfProvince> selectProvinceCity();

	/**
	 *查询所有的车辆信息
	 * @param busLicense 前台菜单跳转时分辨出查看城市
	 * @param busDutyDriver 配置车辆管理维护司机
	 * @param busPlate      查看车牌
	 * @param busType       公交车类型
	 * @param busMin        公交车使用时间
	 * @param busState      公交车状态
	 * @return
	 */
	public List<DyfBusBean> selectBusManger
	(@Param("busLicense") String busLicense,
	 @Param("busDutyDriver") String busDutyDriver,
	 @Param("busPlate") String busPlate,
	 @Param("busType") String busType,
	 @Param("bubusMinsType") String busMin,
	 @Param("busState") String busState,
	 @Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

	//查询车辆总条数count
	public BigDecimal selectCountBus(@Param("busLicense") String busLicense,
	                                 @Param("busDutyDriver") String busDutyDriver,
	                                 @Param("busPlate") String busPlate,
	                                 @Param("busType") String busType,
	                                 @Param("bubusMinsType") String busMin,
	                                 @Param("busState") String busState);
}
