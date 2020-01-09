package com.bus.service;

import com.bus.dao.CynMangeCardMapper;
import com.bus.javabean.CynCardBean;
import com.bus.javabean.CynCardSelectBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理卡片接口实现类
 * by 蔡远南
 */
@Service
public class CynMangeCardService
{
	@Resource
	private CynMangeCardMapper cynMangeCardMapper;
	/**
	 * 获取卡片bean
	 */
	@Transactional
	public List<CynCardBean> getAllCard(CynCardSelectBean cynCardBean){
		return cynMangeCardMapper.getAllCard(cynCardBean);
	}
	/**
	 * 获取卡片数量
	 */
	@Transactional
	public int getAllCardCount(CynCardSelectBean cynCardBean){
		return cynMangeCardMapper.getAllCardCount(cynCardBean);
	}

	/**
	 * 删除卡片
	 */
	@Transactional
	public int delCardById(int id){
		return cynMangeCardMapper.delCardById(id);
	}

	/**
	 * 获取状态ID
	 */
	@Transactional
	public int getStateId(String stateName){
		return cynMangeCardMapper.getStateId(stateName);
	}

	/**
	 * 修改状态
	 */
	public int updateCardById(int id2,int id){
		return cynMangeCardMapper.updateCardById(id2,id);
	}
	/**
	 * 批量插入数据库
	 */
	public int batchInsertCard(List<CynCardBean> cynCardBeans){
		return cynMangeCardMapper.batchInsertCard(cynCardBeans);
	}
}
