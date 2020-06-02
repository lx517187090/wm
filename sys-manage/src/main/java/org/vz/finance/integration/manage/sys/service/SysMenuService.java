package org.vz.finance.integration.manage.sys.service;

import java.io.Serializable;
import java.util.List;

import org.vz.finance.integration.model.SysMenu;

import com.baomidou.mybatisplus.service.IService;


/**
 * 菜单管理
 * 
 * @author jaden
 * @date 2016年9月18日 上午9:42:16
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(String parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenu> getUserMenuList(String userId);

    /**
     * 从数据库查询最大Id
     * @return
     */
    Integer queryMaxId();

	/**
	 * 删除
	 */
	void delete(String menuId);
}
