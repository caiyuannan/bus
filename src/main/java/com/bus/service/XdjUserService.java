package com.bus.service;

import com.bus.dao.XdjUserMapper;
import com.bus.javabean.XdjUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XdjUserService
{
	@Resource
	private XdjUserMapper xdjUserMapper;

	@Transactional
	public List<XdjUser> findUser(String userName, String userPassword)
	{
		return xdjUserMapper.findUser(userName, userPassword);
	}
}
