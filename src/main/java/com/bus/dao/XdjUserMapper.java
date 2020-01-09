package com.bus.dao;

import com.bus.javabean.XdjUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 乘客用户的Mapper的类
 */
@Mapper
public interface XdjUserMapper
{

	// 获取所有的XdjUser
	public List<XdjUser> findXdjUserAll(XdjUser xdjUser);

	//获取到XdjUser的所有数量

	public int findUserCount(XdjUser xdjUser);

	//增加用户
	public int insertXdjUser(XdjUser xdjUser);

	//修改用户名
	public int updateXdjUser(XdjUser xdjUser);

	//删除用户
	public int deleteXdjUser(int userId);
}
