package com.bus.dao;

import com.bus.javabean.DyfAliPayBean;
import com.bus.javabean.DyfHtmlUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface dyfTwoConfigurationMapper
{
	//用户账号密码登入
	public DyfHtmlUserBean userLogin(@Param("userName") String userName, @Param("userPassWord") String userPassWord);

	//查看该用户是否已注册过
	public DyfHtmlUserBean userSelectrepeatRegister(String userPhoneNum);

	//用户注册
	public int userRegisterInto(@Param("userName") String userName, @Param("userPass") String userPass, @Param("userPhoneNum") String userPhoneNum);

	//用户修改头像
	public int updateImg(@Param("image") String image, @Param("userPhoneNum") String userPhoneNum);

	//用户修改个人信息
	public int updateUserInforMation(DyfHtmlUserBean userBean);
	//查询订单信息
	public List<DyfAliPayBean> selectAliPay();

	//添加充值信息到数据库
	public Integer insertAliPay(DyfAliPayBean dyfAliPayBean);
	//充值金额 修改用户金额数据
	public int updateUserMoney(@Param("userBlance") String userBlance, @Param("userNum") String userNum);

	//消费明细分页查看
	public List<DyfAliPayBean> selectSaveMoney(@Param("phoneNumber") String phoneNumber, @Param("userDate") String userDate, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

	//消费明细总页数
	public Integer selectSaveMoneyCount(@Param("phoneNumber") String phoneNumber, @Param("userDate") String userDate, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);


}
