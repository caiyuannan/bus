package com.bus.service;

import com.bus.dao.XhaCityConfigurationMapper;
import com.bus.dao.XhaStationConfigurationMapper;
import com.bus.javabean.*;
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
	public String deleteStation(int stationId,String cityName){
		int i = stationMapper.deleteStation(stationId);
		if(i > 0){
			int i1 = stationMapper.reduceStationCount(cityName);
			if(i1 >0){
				return "操作成功";
			}
		}
		return "操作失败";
	}

	/**
	 * 添加站点:先判断站点是否已经存在，如果存在则提示已存在，
	 * 如果被逻辑删除则情况站点其他记录更改状态为1
	 * 添加完站点同时更新城市站点表的总站点数
	 */
	public String addStataion(XhaStationBean stationBean){
		XhaStationBean isExisted = stationMapper.seleteStationIsExisted(stationBean.getStationName(), 1);
		if (null != isExisted){
			return "站点已存在!";
		}else{
			XhaStationBean isDeleted = stationMapper.seleteStationIsExisted(stationBean.getStationName(), 2);
			int result = 0;
			if (null != isDeleted){
				result = stationMapper.updateStation(stationBean);
			}else{
				result = stationMapper.addStation(stationBean);
			}
			if(result > 0){
				int i = stationMapper.addStationCount(stationBean.getCityName());
				if (i >0){
					return "添加成功!";
				}
			}
		}
		return "添加失败!";
	}

	/**根据站点id查询站点信息*/
	public XhaStationBean queryStationById(int stationId){
		return stationMapper.queryStationById(stationId);
	}
}
