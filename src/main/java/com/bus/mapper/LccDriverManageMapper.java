package com.bus.mapper;

import com.bus.javabean.LccDriverBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 司机管理接口类
 * by连晨诚
 */
@Mapper
public interface LccDriverManageMapper

{
	/**
	 *预加载查询所有启用状态司机
	 * @return
	 */
	public List<LccDriverBean> queryDriver();

	/**
	 * 检查司机是否当天排过班
	 * @param driverId
	 * @param date
	 * @return
	 */
	public int checkDriverWork(int driverId, String date);

	/**
	 * 给司机排班
	 * @param driverId
	 * @param date
	 * @return
	 */
	public boolean addWork(int driverId, String date);
}
