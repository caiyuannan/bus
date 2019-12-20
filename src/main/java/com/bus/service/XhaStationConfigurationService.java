package com.bus.service;

import com.bus.dao.XhaCityConfigurationMapper;
import com.bus.dao.XhaStationConfigurationMapper;
import com.bus.javabean.*;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 城市站点配置服务器
 * by 谢海安
 */
@Service
public class XhaStationConfigurationService {
	@Resource
	private XhaStationConfigurationMapper stationMapper;
	@Resource
	private XhaCityConfigurationMapper cityMapper;

	/**查询所有省和市*/
	public List<XhaProvinceBean> queryProvinceCity(){
		List<XhaProvinceBean> provinceBeans = cityMapper.queryProvince();
		for (XhaProvinceBean provinceBean : provinceBeans) {
			List<XhaCityBean> xhaCityBeans = stationMapper.queryCityByProvinceId(provinceBean.getProvinceId());
			provinceBean.setCitys(xhaCityBeans);
		}
		return provinceBeans;
	}

	/**根据城市查询对应的站点*/
	public TableBean queryStationByProvinceName(XhaPageBean pageBean){
		List<XhaStationBean> count = stationMapper.queryStationByProvinceName(pageBean);
		List<XhaStationBean> beans = stationMapper.queryStationOnLimit(pageBean);
		return new TableBean("",count.size(),0,beans);
	}

	/**删除站点*/
	public String deleteStation(int stationId){
		int i = stationMapper.deleteStation(stationId);
		if(i > 0){
			return "操作成功";
		}
		return "操作失败";
	}

	/**
	 * 添加站点:先判断站点是否已经存在，如果存在则提示已存在，如果被逻辑删除则情况站点其他记录更改状态为1
	 */
	public String addStataion(String stationName){
		XhaStationBean isExisted = stationMapper.seleteStationIsExisted(stationName, 1);
		if (null != isExisted){
			System.out.println("站点已存在");
			return "站点已存在!";
		}else{
			XhaStationBean isDeleted = stationMapper.seleteStationIsExisted(stationName, 2);
			if (null != isDeleted){
				System.out.println("站点已被逻辑删除");

			}
		}
		return null;
	}
}
