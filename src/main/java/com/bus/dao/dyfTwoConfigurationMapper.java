package com.bus.dao;

import com.bus.javabean.DyfHtmlUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface dyfTwoConfigurationMapper
{
	//用户账号密码登入
	public DyfHtmlUserBean userLogin(@Param("userName") String userName,@Param("userPassWord") String userPassWord);

	//查看该用户是否已注册过
	public DyfHtmlUserBean userSelectrepeatRegister(String userPhoneNum);

	//用户注册
	public int userRegisterInto(@Param("userName") String userName,@Param("userPass") String userPass,@Param("userPhoneNum") String userPhoneNum);
}
