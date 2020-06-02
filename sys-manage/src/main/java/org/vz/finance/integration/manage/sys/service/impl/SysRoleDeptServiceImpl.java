package org.vz.finance.integration.manage.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vz.finance.integration.manage.sys.dao.SysRoleDeptDao;
import org.vz.finance.integration.manage.sys.service.SysRoleDeptService;
import org.vz.finance.integration.model.SysRoleDept;
import org.vz.finance.integration.model.util.ToolForID;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;


/**
 * 角色与部门对应关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017年6月21日 23:42:30
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDept> implements SysRoleDeptService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(String roleId, List<String> deptIdList) {
		//先删除角色与部门关系
		deleteBatch(new String[]{roleId});

		if(null == deptIdList || deptIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		List<SysRoleDept> list = new ArrayList<>(deptIdList.size());
		for(String deptId : deptIdList){
			SysRoleDept sysRoleDeptEntity = new SysRoleDept();
			sysRoleDeptEntity.setId(ToolForID.getSysRoleDeptID());
			sysRoleDeptEntity.setDeptId(deptId);
			sysRoleDeptEntity.setRoleId(roleId);

			list.add(sysRoleDeptEntity);
		}
		this.insertBatch(list);
	}

	@Override
	public List<String> queryDeptIdList(String[] roleIds) {
		return baseMapper.queryDeptIdList(roleIds);
	}

	@Override
	public int deleteBatch(String[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}
}
