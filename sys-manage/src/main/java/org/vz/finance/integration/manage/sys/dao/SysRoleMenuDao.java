package org.vz.finance.integration.manage.sys.dao;

import java.util.List;

import org.vz.finance.integration.model.SysRoleMenu;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 角色与菜单对应关系
 * 
 * @author jaden
 * @date 2016年9月18日 上午9:33:46
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
