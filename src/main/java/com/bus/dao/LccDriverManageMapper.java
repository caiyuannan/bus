package com.bus.dao;

import com.bus.javabean.LccCrewSchedulingBean;
import com.bus.javabean.LccDriverBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
}
