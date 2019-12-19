package com.bus.service;

import com.bus.javabean.LccCrewSchedulingBean;
import com.bus.javabean.LccDriverBean;
import com.bus.dao.LccDriverManageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public boolean updateDriverWork(String workType,String workId){
		LccCrewSchedulingBean lcsb = new LccCrewSchedulingBean();

		int wid =Integer.valueOf(workId);
		lcsb.setWorkId(wid);
		lcsb.setWorkType(workType);
		System.out.println("service输出"+lcsb.getWorkType()+","+lcsb.getWorkId());
		return lmp.updateDriverWork(lcsb);
	}
}
