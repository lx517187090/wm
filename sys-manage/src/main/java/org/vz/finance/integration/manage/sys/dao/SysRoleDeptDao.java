package org.vz.finance.integration.manage.sys.dao;

import java.util.List;

import org.vz.finance.integration.model.SysRoleDept;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 角色与部门对应关系
 * 
 * @author jaden
 * @date 2017年6月21日 23:33:46
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDept> {
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<String> queryDeptIdList(String[] roleIds);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
