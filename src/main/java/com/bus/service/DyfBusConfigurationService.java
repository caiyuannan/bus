package com.bus.service;

import com.bus.javabean.*;
import com.bus.dao.DyfBusConfigurationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DyfBusConfigurationService
{
	@Resource
	private DyfBusConfigurationMapper busConfigurationMapper;

	//省份城市树结构查询
	@Transactional
	public List<DyfProvince> selectProvinceCity()
	{
		return busConfigurationMapper.selectProvinceCity();
	}

	/**
	 * @param busLicense    前台菜单跳转时分辨出查看城市
	 * @param busDutyDriver 配置车辆管理维护司机
	 * @param busPlate      查看车牌
	 * @param busAge        公交车类型
	 * @param busMin        公交车使用时间
	 * @param busState      公交车状态
	 * @return 查询所有的车辆信息
	 */
	//分页查询所有公交车
	@Transactional
	public List<DyfBusBean> selectBusManger(String busLicense, String busDutyDriver, String busPlate, String busAge, String busMin, String busState, Integer startPage, Integer page)
	{
		return busConfigurationMapper.selectBusManger(busLicense, busDutyDriver, busPlate, busAge, busMin, busState, startPage, page);
	}

	//查询公交车总页数
	@Transactional
	public BigDecimal selectCountBus(String busLicense, String busDutyDriver, String busPlate, String busAge, String busMin, String busState)
	{
		return busConfigurationMapper.selectCountBus(busLicense, busDutyDriver, busPlate, busAge, busMin, busState);

	}

	//查询所有司机
	@Transactional
	public List<LccDriverBean> selectDriver(String province)
	{
		return busConfigurationMapper.selectDriver(province);
	}

	//	添加bus操作
	@Transactional
	public int insertBus(DyfBusBean dyfBusBean)
	{
		return busConfigurationMapper.insertBus(dyfBusBean);
	}

	//查询状态操作
	@Transactional
	public List<stateBean> selectAllState(String state)
	{
		return busConfigurationMapper.selectAllState(state);
	}

	//	修改车辆状态，当为删除时则为删除
	@Transactional
	public Integer deleteBusState(Integer stateNumber, BigDecimal busId)
	{
		return busConfigurationMapper.deleteBusState(stateNumber, busId);
	}

	//	车辆管理的修改信息操作
	@Transactional
	public Integer updateBusInforMation(DyfBusBean dyfBusBean)
	{
		return busConfigurationMapper.updateBusInforMation(dyfBusBean);
	}

	//	车辆维修表查询
	public List<DyfBusRepairBean> selectAllRepair(BigDecimal startIndex, Integer pageSize, String province, String license)
	{

		return busConfigurationMapper.selectAllRepair(startIndex, pageSize, province, license);
	}

	//车辆总条数
	public BigDecimal countSelectRepair(String province, String license)
	{
		return busConfigurationMapper.countSelectRepair(province, license);
	}

	//	当用户点击维修状态后插入维修表表数据
	public Integer insertAddRepairBus(DyfBusRepairBean dyfBusRepairBean)
	{
		return busConfigurationMapper.insertAddRepairBus(dyfBusRepairBean);
	}

	//	排班查看
	public List<DyfBusShfitBean> shfitSlectAllLine(BigDecimal startIndex, Integer pageSize, String shfitDate, String cityName)
	{
		return busConfigurationMapper.shfitSlectAllLine(startIndex, pageSize, shfitDate, cityName);
	}

	//	排班总条数
	public Integer shfitCount(@Param("shfitDate") String shfitDate, @Param("cityName") String cityName)
	{
		return busConfigurationMapper.shfitCount(shfitDate, cityName);
	}

	//	查询所有的线路名称及id
	public List<DyfRouteBean> routeAllSelect(String cityName){
		return busConfigurationMapper.routeAllSelect(cityName);
	}
}
