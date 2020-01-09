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
	//查询所有的起始和终点站点
	public List<DyfRouteOrder> selectAllStation();
	//添加公交车信息
	public int insertBus(DyfBusBean dyfBusBean);

	//查询所有状态表并给其赋值
	public List<stateBean> selectAllState(@Param("state") String state);

	//	车辆管理的删除操作，将车辆状态更改为已删除
	public Integer deleteBusState(@Param("stateNumber") Integer stateNumber, @Param("busId") BigDecimal busId);

	//	车辆管理的修改信息操作
	public Integer updateBusInforMation(DyfBusBean dyfBusBean);

	// 车辆维修记录数据
	public List<DyfBusRepairBean> selectAllRepair(@Param("startIndex") BigDecimal startIndex, @Param("pageSize") Integer pageSize, @Param("province") String province, @Param("license") String license);
	//车辆分页信息总条数
	public BigDecimal countSelectRepair(@Param("province") String province, @Param("license") String license);
//	当用户点击维修状态后插入维修表表数据
	public Integer insertAddRepairBus(DyfBusRepairBean dyfBusRepairBean);
//	查询分页所有的车辆排班信息
	public List<DyfBusShfitBean> shfitSlectAllLine(@Param("shfitDate") String shfitDate, @Param("cityName") String cityName, @Param("shfitBusLine") String shfitBusLine, @Param("busEndStation") String busEndStation, @Param("shfitBusStartStation") String shfitBusStartStation);
//	查询排班分页总条数
	public Integer shfitCount(@Param("shfitDate") String shfitDate, @Param("cityName") String cityName);
//	查询所有的线路
	public List<DyfRouteBean> routeAllSelect(@Param("cityName") String cityName);
//	查询所有可排班车联的，区分起始站和终点站
	public List<DyfRouteOrder> selectSomeStation(@Param("routeOrder") String routeOrder, @Param("routeName") String routeName);
//	查询所有的车辆排班
	public List<DyfBusShfitBean> selectAllCanRefait(@Param("shfitDate") String shfitDate, @Param("cityName") String cityName, @Param("shfitBusLine") String shfitBusLine, @Param("busEndStation") String busEndStation, @Param("shfitBusStartStation") String shfitBusStartStation);
//去除掉所有不可选择的排班
	public List<DyfBusBean> selectAllNoShfitBus(@Param("list") List<Integer> list, @Param("busEndStation") String busEndStation, @Param("busTime") String busTime, @Param("twoBusStation") String twoBusStation);
//添加排班
	public int addShfit(@Param("shfitDate") String shfitDate, @Param("shfitStartTime") String shfitStartTime, @Param("shfitBusId") String shfitBusId, @Param("shfitBusLine") String shfitBusLine, @Param("shfitStartId") String shfitStartId, @Param("startStation") String startStation);
//	替换排班
	public int updateShfitBus(@Param("shfitBusId") String shfitBusId, @Param("shfitBusLine") String shfitBusLine, @Param("shfitBusStation") String shfitBusStation, @Param("startId") String startId, @Param("shfitDate") String shfitDate, @Param("oldShfitId") String oldShfitId);
//	根据车牌查询车辆id
	public DyfBusBean selectBusLinsece(String busLicense);
//	查询所有站路下对应的站点数和站点经纬度
	public List<dyfStationBean> testGetAllData(@Param("route") String route, @Param("cityName") String cityName);
//	查询所有的站点名称
	public List<dyfAllRouteBean>selectAllRoute(String cityName);
//	时间轴信息查看
	public List<DyfBusShfitBean> timerShfit(@Param("busId") String busId, @Param("busDate") String busDate);
//	查询某段时间下所有排班车辆信息
	public List<DyfBusBean> selectStationId(@Param("shfitBusId") String shfitBusId, @Param("shfitDate") String shfitDate, @Param("shfitStation") String shfitStation, @Param("shfitBusId1") Integer shfitBusId1, @Param("shfitBusId2") Integer shfitBusId2, @Param("shfitBusLine") String shfitBusLine);
//	查询时间对应的时间id
	public List<dyfDateBean>selectDateId(String dateTime);
//	查询当断时间内停站车辆信息
	public List<DyfBusShfitBean>selectHelpBus(@Param("dateNow") String dateNow, @Param("dateId") String dateId);
//	查询该车最新发车信息
	public List<DyfBusShfitBean>timeNameLisence(@Param("shfitThisStation") String shfitThisStation, @Param("shfitDate") String shfitDate, @Param("shfitBusId") String shfitBusId, @Param("shfitDateID") String shfitDateID);
}
