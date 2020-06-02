package org.vz.finance.integration.manage.sys.service;

import java.util.List;

import org.vz.finance.integration.model.SysRoleMenu;

import com.baomidou.mybatisplus.service.IService;


/**
 * 角色与菜单对应关系
 * 
 * @author jaden
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
	
	void saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
	
}
