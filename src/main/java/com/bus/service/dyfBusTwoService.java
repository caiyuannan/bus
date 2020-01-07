package com.bus.service;

import com.bus.dao.DyfBusConfigurationMapper;
import com.bus.dao.dyfTwoConfigurationMapper;
import com.bus.javabean.DyfAliPayBean;
import com.bus.javabean.DyfHtmlUserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

	//用户修改头像操作
	@Transactional
	public int updateImg(String image,String phoneNum){
		return busConfigurationMapper.updateImg(image,phoneNum);
	}

	//用户修改个人信息
	@Transactional
	public int updateUserInforMation(DyfHtmlUserBean userBean){
		return busConfigurationMapper.updateUserInforMation(userBean);
	}
	//查询订单信息
	@Transactional
	public List<DyfAliPayBean> selectAliPay(){
		return busConfigurationMapper.selectAliPay();
	}
	//添加充值信息到数据库
	@Transactional
	public Integer insertAliPay(DyfAliPayBean dyfAliPayBean){
		return busConfigurationMapper.insertAliPay(dyfAliPayBean);
	}

	//充值金额 修改用户金额数据
	@Transactional
	public int updateUserMoney(String userBlance,String userNum){
		return busConfigurationMapper.updateUserMoney(userBlance,userNum);
	}

	//用户消费明细分页查看
	@Transactional
	public List<DyfAliPayBean> selectSaveMoney(String phoneNumber,String userDate,Integer startIndex , Integer pageSize){
		return busConfigurationMapper.selectSaveMoney(phoneNumber,userDate,startIndex,pageSize);
	}

	//用户消费明细总页数
	@Transactional
	public Integer selectSaveMoneyCount(String phoneNumber,String userDate,Integer startIndex , Integer pageSize){
		return busConfigurationMapper.selectSaveMoneyCount(phoneNumber,userDate,startIndex,pageSize);
	}


}
