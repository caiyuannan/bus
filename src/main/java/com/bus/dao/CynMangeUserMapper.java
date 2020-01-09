package com.bus.dao;

import com.bus.aoplog.CynSystemLog;
import com.bus.javabean.CynLogInf;
import com.bus.javabean.CynLogSelectBean;
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
	public List<CynLogInf> findLogAllByOperatorName(CynLogSelectBean cynLogSelectBean);

	/**
	 * 获取日志的总页数
	 */
	public int findLogAllCountByOperatorName(CynLogSelectBean cynLogSelectBean);

	/**
	 * 修改日志操作
	 */
	public int updateLog(CynLogInf cynLogInf);

	/**
	 * 删除日志操作
	 */
	public int deleteLogInf(int logId);
}
