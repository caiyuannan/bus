package com.bus.controller;
import com.bus.javabean.TableBean;
import com.bus.javabean.XhaCityConfigurationBean;
import com.bus.javabean.XhaPageBean;
import com.bus.javabean.XhaProvinceBean;
import com.bus.service.XhaCityConfigurationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 城市配置控制类
 * by 谢海安
 */
@Controller
@RequestMapping("city")
public class XhaCityConfigurationController {
	@Resource
	private XhaCityConfigurationService cityConfigurationService;

	/**
	 * 界面路径
	 * @param path
	 * @return
	 */
	@RequestMapping("{url}")
	public String view(@PathVariable(value = "url") String path){
		return "backjsp/"+path;
	}

	/**
	 * 城市配置表
	 * @param page：页码
	 * @param limit：分页限制
	 * @return
	 */
	@RequestMapping("queryCityConfiguration")
	@ResponseBody
	public TableBean queryCityConfiguration(String page,String limit,String provinceName,String cityName){
		System.out.println("provinceName:"+provinceName+",cityName:"+cityName);
		XhaPageBean pb = new XhaPageBean(provinceName,cityName,0,0);
		//表格中的总条数
		List<XhaCityConfigurationBean> count = cityConfigurationService.queryCityConfiguration(pb);
		//带分页效果
		XhaPageBean pbLimit = new XhaPageBean(provinceName,cityName,Integer.parseInt(page),Integer.parseInt(limit));
		List<XhaCityConfigurationBean> beans = cityConfigurationService.queryCityConfigurationOnLimit(pbLimit);
		TableBean tb = new TableBean("",count.size(),0,beans);
		return tb;
	}

	/**
	 * 删除城市配置表
	 * @param cityConfigurationId：城市配置表id
	 * @return
	 */
	@RequestMapping("deleteCityConfiguration")
	@ResponseBody
	public String deleteCityConfiguration(String cityConfigurationId){
		System.out.println("获取到的是"+cityConfigurationId);
		String result = cityConfigurationService.deleteCityConfiguration(Integer.parseInt(cityConfigurationId));
		System.out.println(result);
		return result;
	}

	/**
	 * 添加城市配置里面的城市
	 */
	@RequestMapping("addCityConfiguration")
	@ResponseBody
	public String addCityConfiguration(String provinceName,String cityName){
		System.out.println("添加进来了！");
		String s = cityConfigurationService.addProvinceCity(provinceName, cityName);
		System.out.println(s);
		return s;
	}

	/**添加城市界面，同时把省份数据传输到前台*/
	@RequestMapping("addCityView")
	@ResponseBody
	public ModelAndView addCityView(){
		ModelAndView mv = new ModelAndView("backjsp/addCity");
		List<XhaProvinceBean> provinceBeans = cityConfigurationService.queryProvince();
		mv.addObject("province",provinceBeans);
		return mv;
	}
}
