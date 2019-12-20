package com.bus.dao;

import com.bus.javabean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 司机管理接口类
 * by连晨诚
 */
@Mapper
public interface LccDriverManageMapper

{
//	/**
//	 *预加载查询所有启用状态司机
//	 * @return
//	 */
//	public List<LccDriverBean> queryDriver();
//
//	/**
//	 * 检查司机是否当天排过班
//	 * @param driverId
//	 * @param date
//	 * @return
//	 */
//	public int checkDriverWork(int driverId, String date);

	/**
	 * 预加载周排班查询
	 * @return
	 */

	public List<LccCrewSchedulingBean> queryWeekWork(LccCrewSchedulingBean lcsb);


	public int findBusWorkIdByBusIicense(String busIicense);
	/**
	 * 添加(修改)排班
	 * @param lcsb
	 * @return
	 */
	public boolean updateDriverWork(LccCrewSchedulingBean lcsb);
	public boolean addDriverWork(LccCrewSchedulingBean lcsb);
	public List<DyfBusBean> findAllBuses();
	public List<LccRouteBean> findAllRoutes();
	public List<LccDriverBean> findAllDriver();

	public List<Integer> findDriverId();
	public int insertBlankWork(LccCrewSchedulingBean lcdb);

	public int queryDriverIdByDriverName(String driverName);

	public LccBusShfitBean findBusShfit();

	public int insertDriverWorkload(Map<String,Object> map);
	public int addGateCard(Map<String,Object> map);
	public boolean updateBusShfitState(int shfitId);
}
