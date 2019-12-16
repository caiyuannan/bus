package com.bus.javabean;
/**
 * 管理员基础信息
 * mangeuserid  //管理员id
 * mangeusername //管理员账户
 * password //管理员密码
 * by蔡远南
 */
public class CynMangeUserBean
{
	private int mangeUserId;
	private String mangeUserName;
	private String password;

	public CynMangeUserBean()
	{
	}

	public CynMangeUserBean(int mangeUserId, String mangeUserName, String password)
	{
		this.mangeUserId = mangeUserId;
		this.mangeUserName = mangeUserName;
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "CynMangeUserBean{" + "mangeUserId=" + mangeUserId + ", mangeUserName='" + mangeUserName + '\'' + ", password='" + password + '\'' + '}';
	}

	public int getMangeUserId()
	{
		return mangeUserId;
	}

	public void setMangeUserId(int mangeUserId)
	{
		this.mangeUserId = mangeUserId;
	}

	public String getMangeUserName()
	{
		return mangeUserName;
	}

	public void setMangeUserName(String mangeUserName)
	{
		this.mangeUserName = mangeUserName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
