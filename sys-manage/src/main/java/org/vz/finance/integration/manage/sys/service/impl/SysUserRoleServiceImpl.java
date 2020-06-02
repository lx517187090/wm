package org.vz.finance.integration.manage.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.vz.finance.integration.manage.sys.dao.SysUserRoleDao;
import org.vz.finance.integration.manage.sys.service.SysUserRoleService;
import org.vz.finance.integration.model.SysUserRole;
import org.vz.finance.integration.model.util.ToolForID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
	@Override
    @CacheEvict(value = {"menus"},allEntries=true)
	public void saveOrUpdate(String userId, List<String> roleIdList) {
		//先删除用户与角色关系
		Map<String, Object> param = CollectionUtil.newHashMap();
		param.put("user_id", userId);
		this.deleteByMap(param);

		if(roleIdList.size() == 0){
			return ;
		}
		
		//保存用户与角色关系
		List<SysUserRole> list = new ArrayList<>(roleIdList.size());
		for(String roleId : roleIdList){
			SysUserRole sysUserRoleEntity = new SysUserRole();
			sysUserRoleEntity.setId(ToolForID.getSysUserRoleID());
			sysUserRoleEntity.setUserId(userId);
			sysUserRoleEntity.setRoleId(roleId);

			list.add(sysUserRoleEntity);
		}
		this.insertBatch(list);
	}

	@Override
	public List<String> queryRoleIdList(String userId) {
		return baseMapper.queryRoleIdList(userId);
	}

	@Override
	public String queryRoleTypeList(String userId) {
		return baseMapper.queryRoleTypeList(userId);
	}

	@Override
	public List<String> queryRoleNameList(String userId) {
		return baseMapper.queryRoleNameList(userId);
	}

	@Override
	public int deleteBatch(String[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}
}
