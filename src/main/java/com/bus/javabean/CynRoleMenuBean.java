package com.bus.javabean;

/**
 * 角色权限关系Bean
 * roleMenuId：角色权限关系ID
 * roleId：角色ID
 * menuId：菜单ID
 */

public class CynRoleMenuBean
{
	private int roleMenuId;
	private int roleId;
	private int menuId;

	public CynRoleMenuBean()
	{
	}

	public CynRoleMenuBean(int roleMenuId, int roleId, int menuId)
	{
		this.roleMenuId = roleMenuId;
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public int getRoleMenuId()
	{
		return roleMenuId;
	}

	public void setRoleMenuId(int roleMenuId)
	{
		this.roleMenuId = roleMenuId;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}
}
