package org.vz.finance.integration.manage.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vz.finance.integration.manage.sys.dao.SysRoleDao;
import org.vz.finance.integration.manage.sys.service.*;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.manage.sys.util.Query;
import org.vz.finance.integration.model.SysDept;
import org.vz.finance.integration.model.SysRole;
import org.vz.finance.integration.model.util.ToolForID;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;

    @Override
    // @DataFilter(subDept = true, user = false)
    @Cacheable(value = "roles",key = "#params + 'queryPage'")
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");
        Page<SysRole> page = this.selectPage(new Query<SysRole>(params).getPage(), new EntityWrapper<SysRole>()
                .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
                .orderBy("CREATE_TIME", false));
        /*for (SysRole sysRoleEntity : page.getRecords()) {
            SysDept sysDeptEntity = sysDeptService.selectById(sysRoleEntity.getDeptId());
            sysRoleEntity.setDeptName(sysDeptEntity.getName());
        }*/
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="roles",allEntries=true)
    public void save(SysRole role) {
        role.setCreateTime(new Date());
        role.setId(ToolForID.getSysRoleID());
        this.insert(role);

        // 保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());

        // 保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getId(), role.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="roles",allEntries=true)
    public void update(SysRole role) {
        this.updateById(role);

        // 更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());

        // 保存角色与部门关系
        sysRoleDeptService.saveOrUpdate(role.getId(), role.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="roles",allEntries=true)
    public void deleteBatch(String[] roleIds) {
        // 删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        // 删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        // 删除角色与部门关联
        sysRoleDeptService.deleteBatch(roleIds);

        // 删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

}
