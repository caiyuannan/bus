package com.bus.service;

import com.bus.javabean.XhaCityBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市配置业务接口
 * by 谢海安
 */
@Service
public interface XhaCityConfigurationService {
	/**
	 * 查询城市
	 * @return
	 */
	public List<XhaCityBean> findCity();
}
