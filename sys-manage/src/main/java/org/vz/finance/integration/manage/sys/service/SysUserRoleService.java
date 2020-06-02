package org.vz.finance.integration.manage.sys.service;

import java.util.List;

import org.vz.finance.integration.model.SysUserRole;

import com.baomidou.mybatisplus.service.IService;


/**
 * 用户与角色对应关系
 * 
 * @author jaden
 * @date 2016年9月18日 上午9:43:24
 */
public interface SysUserRoleService extends IService<SysUserRole>  {
	
	void saveOrUpdate(String userId, List<String> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);

	/**
	 * 根据用户ID，获取角色Name列表
	 */
	String queryRoleTypeList(String userId);

	List<String> queryRoleNameList(String userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
