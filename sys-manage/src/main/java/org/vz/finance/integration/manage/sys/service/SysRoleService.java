package org.vz.finance.integration.manage.sys.service;

import java.util.Map;

import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysRole;

import com.baomidou.mybatisplus.service.IService;



/**
 * 角色
 * 
 * @author jaden
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService extends IService<SysRole> {

	PageUtils queryPage(Map<String, Object> params);

	void save(SysRole role);

	void update(SysRole role);
	
	void deleteBatch(String[] roleIds);

}
