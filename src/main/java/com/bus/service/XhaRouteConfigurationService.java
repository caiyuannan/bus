package com.bus.service;
import com.bus.dao.XhaRouteConfigurationMapper;
import com.bus.javabean.TableBean;
import com.bus.javabean.XhaRouteConfigurationBean;
import com.bus.javabean.XhaRouteOrderBean;
import com.bus.javabean.XhaStationBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 城市路线配置服务器
 * by 谢海安
 */
@Service
public class XhaRouteConfigurationService {
	@Resource
	private XhaRouteConfigurationMapper routeMapper;

	/**路线查询*/
	public TableBean queryRoute(){
		List<XhaRouteConfigurationBean> list = new ArrayList<>();
		List<XhaRouteOrderBean> routeList = routeMapper.queryAllRoute();
		for (XhaRouteOrderBean routeOrder : routeList) {
			XhaRouteConfigurationBean routeConfiguration = new XhaRouteConfigurationBean();
			//路线名称
			routeConfiguration.setRouteName(routeOrder.getRouteName());
			List<XhaRouteOrderBean> routeOrderBeanList = routeMapper.queryStationByRouteName(routeOrder.getRouteName());
			//经过站点数
			routeConfiguration.setStationCount(routeOrderBeanList.size());
			list.add(routeConfiguration);
		}
		return new TableBean("",list.size(),0,list);
	}

	/**根据线路名查询站点*/
	public List<XhaRouteOrderBean> queryStationByRouteName(String routeName,String isReverse){
		List<XhaRouteOrderBean> list = routeMapper.queryStationByRouteName(routeName);
		if(null != isReverse){
			if(Integer.parseInt(isReverse) != 1){
				Collections.reverse(list);
			}
		}
		return list;
	}

	/**从路线中删除站点*/
	public String deleteStation(XhaRouteOrderBean routeOrderBean){
		int i = routeMapper.deleteStation(routeOrderBean);
		if(i > 0){
			int i1 = routeMapper.realignRoute(routeOrderBean);
			if (i1 > 0){
				return "删除成功!";
			}
		}
		return "删除失败!";
	}

	/**根据线路名查询站点具体信息*/
	public List<XhaStationBean> queryStationInfo(String routeName){
		return routeMapper.queryStationInfo(routeName);
	}

	/**查询所有站点*/
	public List<XhaStationBean> queryAllStation(){
		List<XhaStationBean> list = routeMapper.queryAllStation();
		return list;
	}
}
