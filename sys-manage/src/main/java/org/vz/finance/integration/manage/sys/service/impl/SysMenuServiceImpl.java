package org.vz.finance.integration.manage.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.vz.finance.integration.manage.sys.dao.SysMenuDao;
import org.vz.finance.integration.manage.sys.service.SysMenuService;
import org.vz.finance.integration.manage.sys.service.SysRoleMenuService;
import org.vz.finance.integration.manage.sys.service.SysUserService;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.model.SysMenu;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;



@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

    @Override
  //  @CacheEvict(value="menus",allEntries=true)
    public boolean insert(SysMenu entity) {
        return super.insert(entity);
    }

    @Override
  //  @CacheEvict(value="menus",allEntries=true)
    public boolean updateById(SysMenu entity) {
        return super.updateById(entity);
    }

    @Override
   // @Cacheable(value = "menus",key = "#id + 'selectById'")
	public SysMenu selectById(Serializable id) {
		return super.selectById(id);
	}

    @Override
   // @Cacheable(value = "menus",key = "#wrapper + 'selectList'")
    public List<SysMenu> selectList(Wrapper<SysMenu> wrapper) {
        return super.selectList(wrapper);
    }

    @Override
   // @Cacheable(value = "menus",key = "#parentId + 'queryListParentId'")
	public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
		List<SysMenu> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
    //@Cacheable(value = "menus",key = "#parentId + 'queryListParentId'")
	public List<SysMenu> queryListParentId(String parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
   // @Cacheable(value = "menus",key = "'queryNotButtonList'")
	public List<SysMenu> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
    //@Cacheable(value = "menus",key = "#userId + 'getUserMenuList'")
	public List<SysMenu> getUserMenuList(String userId) {
		//系统管理员，拥有最高权限
		if(Constant.SUPER_ADMIN.equals(userId)){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<String> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	@Override
	public Integer queryMaxId() {
         return baseMapper.queryMaxId();
	}

	@Override
   // @CacheEvict(value="menus",allEntries=true)
	public void delete(String menuId){
		//删除菜单
		this.deleteById(menuId);
		//删除菜单与角色关联
		Map<String, Object> param = CollectionUtil.newHashMap();
		param.put("menu_id", menuId);
		sysRoleMenuService.deleteByMap(param);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenu> getAllMenuList(List<String> menuIdList){
		//查询根菜单列表
		List<SysMenu> menuList = queryListParentId("0", menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for(SysMenu entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setSubSysMenus(getMenuTreeList(queryListParentId(entity.getId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
