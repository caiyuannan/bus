package com.bus.dao;

import com.bus.javabean.LccAdviceBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LccSuggestionMapper
{
	public int addAdvice(LccAdviceBean advice);

	public int addAdviceImage(LccAdviceBean advice);

	public List<LccAdviceBean> findAdvice(Map<String,Object> map);

	public int getTotalPages2(Map<String,Object> map);
}
