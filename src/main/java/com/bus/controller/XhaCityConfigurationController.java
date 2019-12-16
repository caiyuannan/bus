//package com.bus.controller;
//
//import com.bus.javabean.XhaCityBean;
//import com.bus.service.XhaCityConfigurationService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 城市配置控制器
// * by谢海安
// */
//@Controller
//@RequestMapping("/city")
//public class XhaCityConfigurationController {
//	@Resource
//	private XhaCityConfigurationService ccs;
//
//	/**
//	 * 返回界面层
//	 * @param path
//	 * @return
//	 */
//	@RequestMapping("{url}")
//	public String view(@PathVariable(value = "url") String path){
//		return "backjsp/"+path;
//	}
//	/**查询城市*/
//	@RequestMapping("/findCity")
//	public void findCity(){
//		System.out.println("方法进来了！");
//		List<XhaCityBean> city = ccs.findCity();
//		System.out.println(city.toString());
//	}
//}
