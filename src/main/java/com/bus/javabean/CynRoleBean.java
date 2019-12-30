package com.bus.javabean;

/**
 * 角色Bean
 * roleId:自增ID
 * roleName:角色名字
 */
public class CynRoleBean
{
	private int roleId;
	private String roleName;

	public CynRoleBean()
	{
	}

	public CynRoleBean(int roleId, String roleName)
	{
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
}
