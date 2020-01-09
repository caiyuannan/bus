package com.bus.service;

import com.bus.dao.XdjUserMapper;
import com.bus.javabean.XdjUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * 乘客用户的Service类
 */
@Service
public class XdjUserService
{
	@Resource
	private XdjUserMapper xdjUserMapper;

	@Transactional
	public List<XdjUser> findUserInfAll(XdjUser xdjUser){
		return xdjUserMapper.findXdjUserAll(xdjUser);
	}

     @Transactional
	public int insertXdjUser(XdjUser xdjUser){
		return xdjUserMapper.insertXdjUser(xdjUser);
     }

     @Transactional
	public int updateXdjUser(XdjUser xdjUser){
	     return xdjUserMapper.updateXdjUser(xdjUser);
     }
	//删除delete
     @Transactional
	public int deleteXdjUser(XdjUser xdjUser){
	     return xdjUserMapper.deleteXdjUser(xdjUser.getUserId());
     }

	//@Transactional
	//public int findUserCount(XdjUser xdjUser)
	//{
	//	return xdjUserMapper.findUserCount(xdjUser);
	//}
}
