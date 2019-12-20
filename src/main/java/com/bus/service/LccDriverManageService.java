package com.bus.service;

import com.bus.javabean.*;
import com.bus.dao.LccDriverManageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LccDriverManageService
{
	@Resource
	private LccDriverManageMapper lmp;
	/**
	 * 查找司机列表
	 * @return
	 */
//	public List<LccDriverBean> findDriver() {
//		return lmp.queryDriver();
//	}
//
//	public boolean checkDriverWork(int driverId,String date){
//
//		int num = lmp.checkDriverWork(driverId,date);
//
//		if(num>0){
//			return true;
//		}
//
//		return false;
//	}

	/**
	 * 对应日期加载排班表
	 * @param lcsb
	 * @return
	 */
	@Transactional
	public HashMap<String, ArrayList<LccCrewSchedulingBean>> queryWeekWork(LccCrewSchedulingBean lcsb){

		List<LccCrewSchedulingBean> lis = lmp.queryWeekWork(lcsb);
		System.out.println("service:list打印"+lis);
		HashMap<String, ArrayList<LccCrewSchedulingBean>> map = new HashMap<>();
		for (int i = 0; i <lis.size() ; i++)
		{
			LccCrewSchedulingBean wk = lis.get(i);
			if (map.containsKey(wk.getDriverName())){

				ArrayList<LccCrewSchedulingBean> list = map.get(wk.getDriverName());
				list.add( wk);

			}else{

				ArrayList<LccCrewSchedulingBean> list = new ArrayList<>();
				list.add(wk);
				map.put(wk.getDriverName(),list);
			}
		}

		return  map;
	}

	/**
	 * 添加排班
	 * @param workType
	 * @param driverId
	 * @param workTime
	 * @return
	 */
	public boolean addDriverWork(String workType,int driverId,String workTime){
		LccCrewSchedulingBean lcsb = new LccCrewSchedulingBean();
		lcsb.setWorkType(workType);
		lcsb.setDriverId(driverId);
		lcsb.setWorkTime(workTime);
		return lmp.addDriverWork(lcsb);
	}


	/**
	 * 修改排班
	 * @param workType
	 * @param workId
	 * @return
	 */
	public boolean updateDriverWork(String workType,String workId){
		LccCrewSchedulingBean lcsb = new LccCrewSchedulingBean();

		int wid =Integer.valueOf(workId);
		lcsb.setWorkId(wid);
		lcsb.setWorkType(workType);
		System.out.println("service输出"+lcsb.getWorkType()+","+lcsb.getWorkId());
		return lmp.updateDriverWork(lcsb);
	}

	/**
	 * 查询所有车牌
	 * @return
	 */
	@Transactional
	public List<DyfBusBean> findAllBuses(){

		System.out.println(lmp.findAllBuses().toString());
		return lmp.findAllBuses();
	}

	/**
	 * 查询所有线路
	 * @return
	 */
	@Transactional
	public List<LccRouteBean> findAllRoutes(){
		System.out.println(lmp.findAllRoutes().toString());
		return lmp.findAllRoutes();
	}

	/**
	 * 查询所有司机
	 * @return
	 */
	@Transactional
	public List<LccDriverBean> findAllDriver(){
		System.out.println(lmp.findAllDriver().toString());
		return lmp.findAllDriver();
	}

	@Transactional
	public List<Integer> findDriverId(){
		System.out.println(lmp.findDriverId().toString());
		return lmp.findDriverId();
	}

	public int insertBlankWork(LccCrewSchedulingBean lcdb){
		System.out.println(lmp.insertBlankWork(lcdb)+"增加条数");
		return lmp.insertBlankWork(lcdb);
	}

	@Transactional
	public int queryDriverIdByDriverName(String driverName){

		return lmp.queryDriverIdByDriverName(driverName);
	}

	/**
	 * 查询发车时间--出站确认功能
	 * @return
	 */
	@Transactional
	public LccBusShfitBean findBusShfit(){

		return lmp.findBusShfit();
	}

	@Transactional
	public int insertDriverWorkload(Map<String,Object> map){

		return lmp.insertDriverWorkload(map);
	}


}
