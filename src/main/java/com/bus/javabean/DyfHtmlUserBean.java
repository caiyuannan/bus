package com.bus.javabean;


import java.io.Serializable;

/**
 * @author 40651
 * private Integer userId;  id
 * 	private String userName; 用户名
 * 	private String userPassWord; 密码
 * 	private String userPhoneNumber; 电话
 * 	private String userHeadPortrait; 头像
 * 	private String userBalance;   余额
 * 	private String userHomeLongitude; 家经度
 * 	private String userCompanyLongitude; 公司经度
 * 	private String userStatus;   状态名
 * 	private String  userHomeLatitude; 家纬度
 * 	private String userCompanyLatitude; 公司纬度
 * 	private String userSex; 性别
 * 	private String userHome; 家名称
 * 	private String userCompany; gs名称
 * 	private String userFaceToken;  ？？？？
 */
public class DyfHtmlUserBean implements Serializable
{
	private Integer userId;
	private String userName;
	private String userPassWord;
	private String userPhoneNumber;
	private String userHeadPortrait;
	private String userBalance;
	private String userHomeLongitude;
	private String userCompanyLongitude;
	private String userStatus;
	private String  userHomeLatitude;
	private String userCompanyLatitude;
	private String userSex;
	private String userHome;
	private String userCompany;
	private String userFaceToken;

	public DyfHtmlUserBean()
	{
	}

	public DyfHtmlUserBean(Integer userId, String userName, String userPassWord, String userPhoneNumber, String userHeadPortrait, String userBalance, String userHomeLongitude, String userCompanyLongitude, String userStatus, String userHomeLatitude, String userCompanyLatitude, String userSex, String userHome, String userCompany, String userFaceToken)
	{
		this.userId = userId;
		this.userName = userName;
		this.userPassWord = userPassWord;
		this.userPhoneNumber = userPhoneNumber;
		this.userHeadPortrait = userHeadPortrait;
		this.userBalance = userBalance;
		this.userHomeLongitude = userHomeLongitude;
		this.userCompanyLongitude = userCompanyLongitude;
		this.userStatus = userStatus;
		this.userHomeLatitude = userHomeLatitude;
		this.userCompanyLatitude = userCompanyLatitude;
		this.userSex = userSex;
		this.userHome = userHome;
		this.userCompany = userCompany;
		this.userFaceToken = userFaceToken;
	}

	@Override
	public String toString()
	{
		return "DyfHtmlUserBean{" + "userId=" + userId + ", userName='" + userName + '\'' + ", userPassWord='" + userPassWord + '\'' + ", userPhoneNumber='" + userPhoneNumber + '\'' + ", userHeadPortrait='" + userHeadPortrait + '\'' + ", userBalance='" + userBalance + '\'' + ", userHomeLongitude='" + userHomeLongitude + '\'' + ", userCompanyLongitude='" + userCompanyLongitude + '\'' + ", userStatus='" + userStatus + '\'' + ", userHomeLatitude='" + userHomeLatitude + '\'' + ", userCompanyLatitude='" + userCompanyLatitude + '\'' + ", userSex='" + userSex + '\'' + ", userHome='" + userHome + '\'' + ", userCompany='" + userCompany + '\'' + ", userFaceToken='" + userFaceToken + '\'' + '}';
	}



	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
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

	public String getUserPassWord()
	{
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord)
	{
		this.userPassWord = userPassWord;
	}

	public String getUserPhoneNumber()
	{
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber)
	{
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserHeadPortrait()
	{
		return userHeadPortrait;
	}

	public void setUserHeadPortrait(String userHeadPortrait)
	{
		this.userHeadPortrait = userHeadPortrait;
	}

	public String getUserBalance()
	{
		return userBalance;
	}

	public void setUserBalance(String userBalance)
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

	public String getUserCompanyLongitude()
	{
		return userCompanyLongitude;
	}

	public void setUserCompanyLongitude(String userCompanyLongitude)
	{
		this.userCompanyLongitude = userCompanyLongitude;
	}

	public String getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}

	public String getUserHomeLatitude()
	{
		return userHomeLatitude;
	}

	public void setUserHomeLatitude(String userHomeLatitude)
	{
		this.userHomeLatitude = userHomeLatitude;
	}

	public String getUserCompanyLatitude()
	{
		return userCompanyLatitude;
	}

	public void setUserCompanyLatitude(String userCompanyLatitude)
	{
		this.userCompanyLatitude = userCompanyLatitude;
	}

	public String getUserSex()
	{
		return userSex;
	}

	public void setUserSex(String userSex)
	{
		this.userSex = userSex;
	}

	public String getUserHome()
	{
		return userHome;
	}

	public void setUserHome(String userHome)
	{
		this.userHome = userHome;
	}

	public String getUserCompany()
	{
		return userCompany;
	}

	public void setUserCompany(String userCompany)
	{
		this.userCompany = userCompany;
	}
	public String getUserFaceToken()
	{
		return userFaceToken;
	}
	public void setUserFaceToken(String userFaceToken)
	{
		this.userFaceToken = userFaceToken;
	}


}
