package com.bus.dao;

import com.bus.aoplog.CynSystemLog;
import com.bus.aoplog.CynSystemLogAspect;
import com.bus.javabean.CynMangeUserBean;
import com.bus.javabean.CynMenuBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理员操作接口
 */
@Mapper
public interface CynMangeUserMapper
{
	/**
	 * 登陆判断
	 */
	public CynMangeUserBean findMangeUserIdByUserName(String mangeUserName);

	/**
	 * 获取所有的一二级菜单
	 */
	public List<CynMenuBean> findMenuAllByUserName(String mangeUserName);

	/**
	 * 添加日志
	 */
	public int addLog(CynSystemLog cynSystemLog);

	/**
	 * 获取所有的日志信息
	 */
	public List<CynSystemLog> findLogAllByOperatorName(String actionName);
}
