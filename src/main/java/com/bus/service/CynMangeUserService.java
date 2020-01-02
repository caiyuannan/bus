package com.bus.service;

import com.bus.aoplog.CynSystemLog;
import com.bus.aoplog.CynSystemLogAspect;
import com.bus.dao.CynMangeUserMapper;
import com.bus.javabean.CynMangeUserBean;
import com.bus.javabean.CynMenuBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理用户接口实现类
 * by 蔡远南
 */
@Service
public class CynMangeUserService
{
	@Resource
	private CynMangeUserMapper cynMangeUserMapper;
	/**
	 * 获取用户bean
	 */
	@Transactional
	public CynMangeUserBean finMangeUser(String mangeUserName)
	{
		return cynMangeUserMapper.findMangeUserIdByUserName(mangeUserName);
	}
	/**
	 * 获取当前管理员的菜单
	 */
	@Transactional
	public List<CynMenuBean> findMenuAllByUserName(String mangeUserName)
	{
		return cynMangeUserMapper.findMenuAllByUserName(mangeUserName);
	}
	/**
	 * 添加日志操作
	 */
	@Transactional
	public int addLog(CynSystemLog cynSystemLogAspect){
		return cynMangeUserMapper.addLog(cynSystemLogAspect);
	}
	/**
	 * 查看日志操作
	 */
	@Transactional
	public List<CynSystemLog> findLogAllByOperatorName(String actionName){
		return cynMangeUserMapper.findLogAllByOperatorName(actionName);
	}
}
