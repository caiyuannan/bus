package com.bus.dao;

import com.bus.javabean.CynCardBean;
import com.bus.javabean.CynCardSelectBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CynMangeCardMapper
{
	/**
	 * 获取所有的卡片数据
	 */
	public List<CynCardBean> getAllCard(CynCardSelectBean cynCardSelectBean);

	/**
	 * 获取所有卡片的数量
	 */
	public int getAllCardCount(CynCardSelectBean cynCardBean);

	/**
	 * 删除卡片通过ID
	 */
	public int delCardById(@Param("id") int id);

	/**
	 * 获取卡片状态
	 */
	public int getStateId(@Param("stateName") String stateName);

	/**
	 * 更新卡片状态
	 */
	public int updateCardById(@Param("id2") int id2, @Param("id") int id);

	/**
	 * 批量插入
	 */
	public int batchInsertCard(List<CynCardBean> cynCardBeans);
}
