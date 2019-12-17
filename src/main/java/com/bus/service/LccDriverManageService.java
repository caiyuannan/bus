package com.bus.service;

import com.bus.javabean.LccCrewSchedulingBean;
import com.bus.javabean.LccDriverBean;
import com.bus.dao.LccDriverManageMapper;
import org.springframework.stereotype.Service;

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
	public List<LccDriverBean> findDriver() {
		return lmp.queryDriver();
	}

	public boolean checkDriverWork(int driverId,String date){

		int num = lmp.checkDriverWork(driverId,date);

		if(num>0){
			return true;
		}

		return false;
	}

	public HashMap<String, ArrayList<LccCrewSchedulingBean>> queryWeekWork(){

		List<LccCrewSchedulingBean> lis = lmp.queryWeekWork();

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


}
