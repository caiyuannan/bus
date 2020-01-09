package com.bus.javabean;

/**
 * 系统帐号管理类
 */
public class XdjSystem
{
	private int mangeuserId;
	private String mangeuserName;
	private String password;
	private String mangeuserPhonenumber;
	private String roleName;
	private String mangeuserCity;

	public XdjSystem()
	{
	}

	public XdjSystem(int mangeuserId, String mangeuserName, String password, String mangeuserPhonenumber, String roleName, String mangeuserCity)
	{
		this.mangeuserId = mangeuserId;
		this.mangeuserName = mangeuserName;
		this.password = password;
		this.mangeuserPhonenumber = mangeuserPhonenumber;
		this.roleName = roleName;
		this.mangeuserCity = mangeuserCity;
	}

	public int getMangeuserId()
	{
		return mangeuserId;
	}

	public void setMangeuserId(int mangeuserId)
	{
		this.mangeuserId = mangeuserId;
	}

	public String getMangeuserName()
	{
		return mangeuserName;
	}

	public void setMangeuserName(String mangeuserName)
	{
		this.mangeuserName = mangeuserName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMangeuserPhonenumber()
	{
		return mangeuserPhonenumber;
	}

	public void setMangeuserPhonenumber(String mangeuserPhonenumber)
	{
		this.mangeuserPhonenumber = mangeuserPhonenumber;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getMangeuserCity()
	{
		return mangeuserCity;
	}

	public void setMangeuserCity(String mangeuserCity)
	{
		this.mangeuserCity = mangeuserCity;
	}

	@Override
	public String toString(){
		return "XdjSystem"+"{" +
				",mangeuserId='"+mangeuserId+'\''+
				",mangeuserName='"+mangeuserName+'\''+
				",password='" +password+ '\'' +
				",mangeuserPhonenumber='"+mangeuserPhonenumber+'\''+
				",roleName='"+roleName+'\''+
				",mangeuserCity='"+mangeuserCity+'\''+
				'}';
	}
}
