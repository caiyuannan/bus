package com.bus.service;

import com.bus.dao.CynMangeMenuMapper;
import com.bus.javabean.CynMenuActionBean;
import com.bus.javabean.CynRoleBean;
import com.bus.javabean.CynRoleMenuBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CynMangeMenuService
{
	@Resource
	private CynMangeMenuMapper cynMangeMenuMapper;

	public List<CynRoleBean> findRoleAll(){
		return cynMangeMenuMapper.findRoleAll();
	}

	public List<CynMenuActionBean> MenuOne(CynRoleBean role){
		return cynMangeMenuMapper.MenuOne(role);
	}

	public List<CynMenuActionBean> getOne(CynRoleBean role){
		return cynMangeMenuMapper.getOne(role);
	}

	//根据roleid获取所有menuid
	public List<CynMenuActionBean> getMenuIdByRoleID(CynRoleBean role){
		return 	cynMangeMenuMapper.getMenuIdByRoleID(role);
	}

	//删除所有数据
	public int deleteRoleMenuByRoleid(CynRoleBean role){
		return cynMangeMenuMapper.deleteRoleMenuByRoleid(role);
	}
	//增加所有数据
	public int addAllRoleMenuRelation(@Param("roleId") String roleId, @Param("list") List<Integer> list){
		return  cynMangeMenuMapper.addAllRoleMenuRelation(roleId,list);
	}

	public List<CynRoleMenuBean> judeMenuRoleIsNull(CynRoleBean role){
		return  cynMangeMenuMapper.judeMenuRoleIsNull(role);
	}




}
