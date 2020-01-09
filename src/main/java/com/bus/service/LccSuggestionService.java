package com.bus.service;

import com.bus.dao.LccSuggestionMapper;
import com.bus.javabean.CynAdviceBean;
import com.bus.javabean.LccAdviceBean;
import com.bus.javabean.LccDriverWageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LccSuggestionService
{
	@Resource
	private LccSuggestionMapper sggp;


	/**
	 * 添加到用户反馈表
	 */
	@Transactional
	public int addAdvice(LccAdviceBean advice){
		return sggp.addAdvice(advice);
	}
	/**
	 * 添加到用户反馈表关联的图片表
	 */
	@Transactional
	public int addAdviceImage(LccAdviceBean advice){
		return sggp.addAdviceImage(advice);
	}


	/**
	 * 查看建议表
	 * @param map
	 * @return
	 */
	public List<LccAdviceBean> findAdvice(Map<String,Object> map){

		return sggp.findAdvice(map);
	}
	/**
	 * 查看建议总页
	 * @param map
	 * @return
	 */
	@Transactional
	public int getTotalPages2(Map<String,Object> map){
		return sggp.getTotalPages2(map);
//		return sggp.getTotalPage2(map);
	}
}
