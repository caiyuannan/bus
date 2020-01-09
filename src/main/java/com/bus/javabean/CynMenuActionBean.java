package com.bus.javabean;

import java.util.List;

/**
 * 权限分配树
 * id：节点唯一索引值
 * title： 节点标题
 * field： 节点字段名
 * checked：是否被选中
 * spread：节点是否初始展开，默认 false
 * roleId：角色ID
 *
 */
public class CynMenuActionBean
{
	private int id;
	private String title;
	private String field;
	private boolean checked;
	private boolean spread;
	private List<CynMenuActionBean> children;
	private int roleId;

	public CynMenuActionBean()
	{
	}

	public CynMenuActionBean(int id, String title, String field, boolean checked, boolean spread, List<CynMenuActionBean> children, int roleId)
	{
		this.id = id;
		this.title = title;
		this.field = field;
		this.checked = checked;
		this.spread = spread;
		this.children = children;
		this.roleId = roleId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public boolean isSpread()
	{
		return spread;
	}

	public void setSpread(boolean spread)
	{
		this.spread = spread;
	}

	public List<CynMenuActionBean> getChildren()
	{
		return children;
	}

	public void setChildren(List<CynMenuActionBean> children)
	{
		this.children = children;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
}
