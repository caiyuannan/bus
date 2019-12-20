package com.bus.dao;

import com.bus.javabean.*;
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
	 * 查询所有的车辆信息
	 *
	 * @param busLicense    前台菜单跳转时分辨出查看城市
	 * @param busDutyDriver 配置车辆管理维护司机
	 * @param busPlate      查看车牌
	 * @param busAge        公交车类型
	 * @param busMin        公交车使用时间
	 * @param busState      公交车状态
	 * @return
	 */
	public List<DyfBusBean> selectBusManger(@Param("busLicense") String busLicense, @Param("busDutyDriver") String busDutyDriver, @Param("busPlate") String busPlate, @Param("busAge") String busAge, @Param("busMin") String busMin, @Param("busState") String busState, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

	//查询车辆总条数count
	public BigDecimal selectCountBus(@Param("busLicense") String busLicense, @Param("busDutyDriver") String busDutyDriver, @Param("busPlate") String busPlate, @Param("busAge") String busAge, @Param("busMin") String busMin, @Param("busState") String busState);

	//查询所有的司机 用来昨天加下拉框选择
	public List<LccDriverBean> selectDriver(String province);

	//添加公交车信息
	public int insertBus(DyfBusBean dyfBusBean);

	//查询所有状态表并给其赋值
	public List<stateBean> selectAllState(@Param("state") String state);

	//	车辆管理的删除操作，将车辆状态更改为已删除
	public Integer deleteBusState(@Param("stateNumber") Integer stateNumber, @Param("busId") BigDecimal busId);

	//	车辆管理的修改信息操作
	public Integer updateBusInforMation(DyfBusBean dyfBusBean);

	// 车辆维修记录数据
	public List<DyfBusRepairBean> selectAllRepair(@Param("startIndex") BigDecimal startIndex, @Param("pageSize") Integer pageSize,@Param("province") String province,@Param("license") String license );
	//车辆分页信息总条数
	public BigDecimal countSelectRepair(@Param("province") String province,@Param("license") String license);
//	当用户点击维修状态后插入维修表表数据
	public Integer insertAddRepairBus(DyfBusRepairBean dyfBusRepairBean);
//	查询分页所有的车辆排班信息
	public List<DyfBusShfitBean> shfitSlectAllLine(@Param("startIndex") BigDecimal startIndex, @Param("pageSize") Integer pageSize,@Param("shfitDate") String shfitDate,@Param("cityName") String cityName);
//	查询排班分页总条数
	public Integer shfitCount(@Param("shfitDate") String shfitDate,@Param("cityName") String cityName);
//	查询所有的线路
	public List<DyfRouteBean> routeAllSelect(@Param("cityName") String cityName);
}
