package com.bus.javabean;

/**
 * 菜单基础类
 * menuId:菜单ID
 * parentMenuName:父亲类
 * childrenName:孩子类
 * menuUrl:url路径
 * by:蔡远南
 */
public class CynMenuBean
{
    private int menuId;
    private String parentMenuName;
    private String childrenName;
    private String menuUrl;

	public CynMenuBean()
	{
	}

	public CynMenuBean(int menuId, String parentMenuName, String childrenName, String menuUrl)
	{
		this.menuId = menuId;
		this.parentMenuName = parentMenuName;
		this.childrenName = childrenName;
		this.menuUrl = menuUrl;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

	public String getParentMenuName()
	{
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName)
	{
		this.parentMenuName = parentMenuName;
	}

	public String getChildrenName()
	{
		return childrenName;
	}

	public void setChildrenName(String childrenName)
	{
		this.childrenName = childrenName;
	}

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}
}
