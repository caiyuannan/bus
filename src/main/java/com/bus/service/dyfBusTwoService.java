package com.bus.service;

import com.bus.dao.DyfBusConfigurationMapper;
import com.bus.dao.dyfTwoConfigurationMapper;
import com.bus.javabean.DyfHtmlUserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class dyfBusTwoService
{
	@Resource
	private dyfTwoConfigurationMapper busConfigurationMapper;
	//用户名密码登入
	@Transactional
	public DyfHtmlUserBean userLogin(String userName,  String userPassWord){
		return busConfigurationMapper.userLogin(userName,userPassWord);
	}
	//用户电话号码是否重复注册
	@Transactional
	public DyfHtmlUserBean userSelectAllRepeatTwo(String userPhoneNum){
		return busConfigurationMapper.userSelectrepeatRegister(userPhoneNum);
	}
	//用户注册操作
	@Transactional
	public int userRegisterInto(String userName,String userPass,String userPhoneNum){
		return busConfigurationMapper.userRegisterInto(userName,userPass,userPhoneNum);
	}

}
