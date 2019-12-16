package com.bus.service;

import com.bus.javabean.XhaCityBean;
import com.bus.mapper.XhaCityConfigurationMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 城市配置接口实现类
 * by 谢海安
 */
@Service
public class XhaCityConfigurationServiceImpl implements XhaCityConfigurationService{
	@Resource
	private XhaCityConfigurationMapper ccm;

	@Override
	public List<XhaCityBean> findCity() {
		return ccm.findCity();
	}
}
