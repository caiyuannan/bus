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
	public int queryAttendanceId();
	public boolean updateBusShfitState(Map<String,Object> map);

	public List<LccDriverBean> queryAllDrivers(Map<String,Object> map);
	public int getTotalPage(Map<String,Object> map);

	public boolean updateDrivers(LccDriverBean ldb);

	public List<LccDriverWorkloadBean> findDriverWorkload(Map<String,Object> map);
	public int getTotalPage1(Map<String,Object> map);

	public List<LccDriverWageBean> findDriverWage(Map<String,Object> map);
	public int getTotalPage2(Map<String,Object> map);
}
