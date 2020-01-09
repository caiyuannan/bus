package com.bus.dao;

import com.bus.javabean.CynMenuActionBean;
import com.bus.javabean.CynRoleBean;
import com.bus.javabean.CynRoleMenuBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员管理权限接口
 */
@Mapper
public interface CynMangeMenuMapper
{
	//获取所有角色
	public List<CynRoleBean> findRoleAll();

	//获取二级树菜单
	public List<CynMenuActionBean> MenuOne(CynRoleBean role);

	//根据roleid获取所有menuid
	public List<CynMenuActionBean> getMenuIdByRoleID(CynRoleBean role);

	//根据roleid获取该用户已有的menuid
	public List<CynMenuActionBean> getOne(CynRoleBean role);

	//删除所有数据
	public int deleteRoleMenuByRoleid(CynRoleBean role);
	//增加所有数据
	public int addAllRoleMenuRelation(@Param("roleId") String roleId, @Param("list") List<Integer> list);

	public List<CynRoleMenuBean> judeMenuRoleIsNull(CynRoleBean role);

}
