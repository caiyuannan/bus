package com.bus.dao;

import com.bus.javabean.XdjSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统帐号管理的Mapper类
 */

@Mapper
public interface XdjSystemMapper
{

	//获取所有的XdjSystem

	public List<XdjSystem> findXdjSystemAll(XdjSystem xdjSystem);

	//获取表格总条数

//	public int findSystemCount(XdjSystem xdjSystem);

	//增加

	public int insertXdjSystem(XdjSystem xdjSystem);

	//修改

	public int updateXdjSystem(XdjSystem xdjSystem);

	//删除

	public int deleteXdjSystem(int mangeuserId);

}
