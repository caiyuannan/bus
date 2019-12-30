package com.bus.javabean;

/**
 * 用户表
 * userId:用户ID
 * userName：账号
 * password：密码
 * userHead: 用户头像
 * phone：手机号
 * money：余额
 * stateName：状态
 * sex：性别
 * home：家
 * company：公司
 * By:蔡远南
 */
public class CynUserBean
{
	private int userId;
	private String userName;
	private String password;
	private String userHead;
	private String phone;
	private int money;
	private String stateName;
	private String sex;
	private String home;
	private String company;
	private String faceToken;

	public CynUserBean(int userId, String userName, String password, String userHead, String phone, int money, String stateName, String sex, String home, String company, String faceToken)
	{
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userHead = userHead;
		this.phone = phone;
		this.money = money;
		this.stateName = stateName;
		this.sex = sex;
		this.home = home;
		this.company = company;
		this.faceToken = faceToken;
	}

	public CynUserBean()
	{
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserHead()
	{
		return userHead;
	}

	public void setUserHead(String userHead)
	{
		this.userHead = userHead;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getHome()
	{
		return home;
	}

	public void setHome(String home)
	{
		this.home = home;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getFaceToken()
	{
		return faceToken;
	}

	public void setFaceToken(String faceToken)
	{
		this.faceToken = faceToken;
	}
}
