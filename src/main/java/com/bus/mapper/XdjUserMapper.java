package com.bus.mapper;

import com.bus.javabean.XdjUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XdjUserMapper
{
	public List<XdjUser> findUser(@Param("userName") String userName, @Param("userPassword") String userPassword);
}
