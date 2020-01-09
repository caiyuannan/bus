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
	private int limit;
	private int page;

	public CynRoleBean()
	{
	}

	public CynRoleBean(int roleId, String roleName)
	{
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public CynRoleBean(int roleId, String roleName,int limit,int page)
	{
		this.roleId = roleId;
		this.roleName = roleName;
		this.limit=limit;
		this.page=page;
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

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}
}
