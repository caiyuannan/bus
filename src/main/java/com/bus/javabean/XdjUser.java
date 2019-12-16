package com.bus.javabean;
/**
 * 许达军  乘客用户表
 */
public class XdjUser
{
	//乘客用户表的id主键
	private int userId;
	//乘客用户的名字
	private String userName;
	//乘客用户的密码
	private String userPassword;
	//乘客用户的手机号码
	private String userPhonenumber;
	//乘客用户的头像
	private String userHeadportrait;
	//乘客用户的余额
	private int userBalance;
	//乘客用户的家经度
	private String userHomeLongitude;
	//乘客用户的家纬度
	private String userHomeLatitude;
	//乘客用户的公司经度
	private String userCompanyLongitude;
	//乘客用户的公司纬度
	private String userCompanyLatitude;
	//乘客用户的状态
	private String userStatus;

	public XdjUser(int userId, String userName, String userPassword, String userPhonenumber, String userHeadportrait, int userBalance, String userHomeLongitude, String userHomeLatitude, String userCompanyLongitude, String userCompanyLatitude, String userStatus)
	{
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhonenumber = userPhonenumber;
		this.userHeadportrait = userHeadportrait;
		this.userBalance = userBalance;
		this.userHomeLongitude = userHomeLongitude;
		this.userHomeLatitude = userHomeLatitude;
		this.userCompanyLongitude = userCompanyLongitude;
		this.userCompanyLatitude = userCompanyLatitude;
		this.userStatus = userStatus;
	}

	public XdjUser()
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

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getUserPhonenumber()
	{
		return userPhonenumber;
	}

	public void setUserPhonenumber(String userPhonenumber)
	{
		this.userPhonenumber = userPhonenumber;
	}

	public String getUserHeadportrait()
	{
		return userHeadportrait;
	}

	public void setUserHeadportrait(String userHeadportrait)
	{
		this.userHeadportrait = userHeadportrait;
	}

	public int getUserBalance()
	{
		return userBalance;
	}

	public void setUserBalance(int userBalance)
	{
		this.userBalance = userBalance;
	}

	public String getUserHomeLongitude()
	{
		return userHomeLongitude;
	}

	public void setUserHomeLongitude(String userHomeLongitude)
	{
		this.userHomeLongitude = userHomeLongitude;
	}

	public String getUserHomeLatitude()
	{
		return userHomeLatitude;
	}

	public void setUserHomeLatitude(String userHomeLatitude)
	{
		this.userHomeLatitude = userHomeLatitude;
	}

	public String getUserCompanyLongitude()
	{
		return userCompanyLongitude;
	}

	public void setUserCompanyLongitude(String userCompanyLongitude)
	{
		this.userCompanyLongitude = userCompanyLongitude;
	}

	public String getUserCompanyLatitude()
	{
		return userCompanyLatitude;
	}

	public void setUserCompanyLatitude(String userCompanyLatitude)
	{
		this.userCompanyLatitude = userCompanyLatitude;
	}

	public String getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}

	@Override
	public String toString(){

		return "XdjUser"+"{" +
					", userName='" + userName + '\'' +
					", userPassword=‘" +userPassword+'\''+
					'}';
	}
}
