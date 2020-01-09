package com.bus.service;

import com.bus.dao.XdjRoleMapper;
import com.bus.javabean.CynRoleBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色配置
 */
@Service
public class XdjRoleService
{
	@Resource
	private XdjRoleMapper xdjRoleMapper;
	CynRoleBean cynRoleBean=new CynRoleBean();

	@Transactional
	public List<CynRoleBean> findXdjRoleAll(String roleName,int page, int limit){
		cynRoleBean.setRoleName(roleName);
		cynRoleBean.setLimit(page*limit);
		cynRoleBean.setPage((page-1)*limit);
		List<CynRoleBean> tableList=xdjRoleMapper.findXdjRoleAll(cynRoleBean);
		if (tableList != null && tableList.size() > 0)
		{
			return tableList;
		}
		return tableList;
	}

	@Transactional

	public int getTableCount()
	{
		int count = xdjRoleMapper.findRoleCount(cynRoleBean);
		return count;
	}

	//增加
	@Transactional

	public int getinsertXdjRole(CynRoleBean cynRoleBean){
		return xdjRoleMapper.insertXdjRole(cynRoleBean);
	}

	//修改
	@Transactional

	public int getupdateXdjRole(CynRoleBean cynRoleBean){
		return xdjRoleMapper.updateXdjRole(cynRoleBean);
	}

	//删除
	@Transactional

	public int getdeleteXdjRole(CynRoleBean cynRoleBean){
		return xdjRoleMapper.updateXdjRole(cynRoleBean);
	}
}
