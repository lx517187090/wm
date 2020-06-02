package org.vz.finance.integration.manage.sys.service;

import java.util.List;

import org.vz.finance.integration.model.SysRoleDept;

import com.baomidou.mybatisplus.service.IService;


/**
 * 角色与部门对应关系
 * 
 * @author jaden
 * @date 2017年6月21日 23:42:30
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {
	
	void saveOrUpdate(String roleId, List<String> deptIdList);
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<String> queryDeptIdList(String[] roleIds) ;

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
