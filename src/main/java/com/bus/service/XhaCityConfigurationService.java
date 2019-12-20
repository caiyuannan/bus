package com.bus.service;

import com.bus.javabean.*;
import com.bus.dao.XhaCityConfigurationMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 城市配置接口实现类
 * by 谢海安
 */
@Service
public class XhaCityConfigurationService{
	@Resource
	private XhaCityConfigurationMapper cityConfigurationMapper;

	/**
	 * 城市配置表
	 * @param pageBean：分页类
	 * @return
	 */
	public List<XhaCityConfigurationBean> queryCityConfiguration(XhaPageBean pageBean){
		return cityConfigurationMapper.queryCityConfiguration(pageBean);
	}

	/**查询城市配置表,带分页*/
	public List<XhaCityConfigurationBean> queryCityConfigurationOnLimit(XhaPageBean pageBean){
		return cityConfigurationMapper.queryCityConfigurationOnLimit(pageBean);
	}

	/**
	 *  删除城市配置表
	 * @param cityConfigurationId:城市配置表的id参数
	 * @return
	 */
	public String deleteCityConfiguration(int cityConfigurationId){
		int i = cityConfigurationMapper.deleteCityConfiguration(cityConfigurationId);
		if(i > 0){
			return "删除成功!";
		}else{
			return "删除失败!";
		}
	}

	/**查询省份*/
	public List<XhaProvinceBean> queryProvince(){
		return cityConfigurationMapper.queryProvince();
	}

	/**
	 * 添加省份城市：首先先查找有没有存在，如果存在就说明状态为2就把状态改成1，不存在就添加
	 * @param provinceName：省名
	 * @param cityName：城市名
	 * @return
	 */
	public String addProvinceCity(String provinceName,String cityName){
		XhaProvinceBean flag1 = cityConfigurationMapper.queryProvinceCityIsExited(provinceName, cityName);
		if (null != flag1){
			System.out.println("该城市已存在");
			return "该城市已存在!";
		}else{
			XhaProvinceBean flag2 = cityConfigurationMapper.queryProvinceCityIsDeleted(provinceName, cityName);
			//存在，更改状态
			if(null != flag2){
				int i = cityConfigurationMapper.updateProvinceCityState(provinceName, cityName);
				if (i > 0){
					return "添加成功!";
				}
			}else{
				int i = cityConfigurationMapper.addProvinceCity(provinceName, cityName);
				if (i > 0){
					return "添加成功!";
				}
			}
		}
		return "添加失败!";
	}

}
