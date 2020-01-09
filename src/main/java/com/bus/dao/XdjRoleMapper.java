package com.bus.dao;

import com.bus.javabean.CynRoleBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色配置
 */
@Mapper
public interface XdjRoleMapper
{
	public List<CynRoleBean> findXdjRoleAll(CynRoleBean cynRoleBean);

	//获取表格总条数

	public int findRoleCount(CynRoleBean cynRoleBean);

	//增加

	public int insertXdjRole(CynRoleBean cynRoleBean);

	//修改

	public int updateXdjRole(CynRoleBean cynRoleBean);

	//删除

	public int deleteXdjRole(int roleId);
}
