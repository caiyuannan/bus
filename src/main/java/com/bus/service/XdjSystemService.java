package com.bus.service;

import com.bus.dao.XdjSystemMapper;
import com.bus.javabean.TableBean;
import com.bus.javabean.XdjSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统帐号管理的service
 */
@Service
public class XdjSystemService
{
	@Resource
	private XdjSystemMapper xdjSystemMapper;
	TableBean tableBean=new TableBean();

	@Transactional
	public List<XdjSystem> findXdjSystemAll(XdjSystem xdjSystem){
		return xdjSystemMapper.findXdjSystemAll(xdjSystem);
	}
	//@Transactional
	//public int getTableCount()
	//{
	//	int count = xdjSystemMapper.findSystemCount(tableBean);
	//	return count;
	//}
	//增加
	@Transactional

	public int insertXdjSystem(XdjSystem xdjSystem){
		return xdjSystemMapper.insertXdjSystem(xdjSystem);
	}

	//修改
	@Transactional

	public int updateXdjSystem(XdjSystem xdjSystem){
		return xdjSystemMapper.updateXdjSystem(xdjSystem);
	}

	//删除

	@Transactional

	public int deleteXdjSystem(XdjSystem xdjSystem){
		return xdjSystemMapper.deleteXdjSystem(xdjSystem.getMangeuserId());
	}
}
