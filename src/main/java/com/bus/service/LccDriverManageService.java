package com.bus.service;

import com.bus.javabean.LccDriverBean;
import com.bus.mapper.LccDriverManageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
