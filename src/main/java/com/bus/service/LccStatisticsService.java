package com.bus.service;

import com.bus.dao.LccDriverManageMapper;
import com.bus.dao.LccStatisticsMapper;
import com.bus.javabean.LccCashBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LccStatisticsService
{
	@Resource
	private LccStatisticsMapper lsp;

	/**
	 * 查询路线收银统计
	 * @return
	 */
//	@Transactional
//	public List<LccCashBean> findRouteCashes(){
//		System.out.println(lsp.findRouteCashes().toString());
//		return lsp.findRouteCashes();
//	}

	/**
	 * 查询路线收银统计带查询日期条件
	 * @return
	 */
	@Transactional
	public List<LccCashBean>findRouteCashesByDate(Map<String,Object> map){
		System.out.println(lsp.findRouteCashesByDate(map).toString()+"========");
		return lsp.findRouteCashesByDate(map);
	}
}
