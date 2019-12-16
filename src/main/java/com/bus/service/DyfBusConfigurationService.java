package com.bus.service;

import com.bus.javabean.DyfBusBean;
import com.bus.javabean.DyfProvince;
import com.bus.javabean.LccDriverBean;
import com.bus.mapper.DyfBusConfigurationMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DyfBusConfigurationService
{
	@Resource
	private DyfBusConfigurationMapper busConfigurationMapper;
	//省份城市树结构查询
	public List<DyfProvince> selectProvinceCity(){
		return busConfigurationMapper.selectProvinceCity();
	}

	/**
	 *
	 * @param busLicense 前台菜单跳转时分辨出查看城市
	 * @param busDutyDriver 配置车辆管理维护司机
	 * @param busPlate      查看车牌
	 * @param busAge       公交车类型
	 * @param busMin        公交车使用时间
	 * @param busState      公交车状态
	 * @return
	 * 查询所有的车辆信息
	 */
	//分页查询所有公交车
	public List<DyfBusBean> selectBusManger(String busLicense,String busDutyDriver,String busPlate,String busAge, String busMin,String busState,Integer startPage,Integer page){
		return busConfigurationMapper.selectBusManger(busLicense,busDutyDriver,busPlate,busAge,busMin,busState,startPage,page);
	}
	//查询公交车总页数
	public BigDecimal selectCountBus(String busLicense,String busDutyDriver,String busPlate,String busAge, String busMin,String busState){
		return busConfigurationMapper.selectCountBus(busLicense, busDutyDriver, busPlate, busAge, busMin, busState);

	}
	//查询所有司机
	public List<LccDriverBean> selectDriver(){
		return busConfigurationMapper.selectDriver();
	}
}
