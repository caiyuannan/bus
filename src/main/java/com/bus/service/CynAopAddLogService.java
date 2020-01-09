package com.bus.service;

import com.bus.aoplog.CynSystemLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CynAopAddLogService
{
	@Resource
	private CynAopAddLogService cynAopAddLogService;

	@Transactional
	public int addLog(CynSystemLog cynSystemLog){
		return cynAopAddLogService.addLog(cynSystemLog);
	}
}
