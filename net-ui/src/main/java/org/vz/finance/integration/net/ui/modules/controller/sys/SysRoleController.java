package org.vz.finance.integration.net.ui.modules.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.service.SysRoleMenuService;
import org.vz.finance.integration.manage.sys.service.SysRoleService;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysRole;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.core.utils.ValidatorUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色管理
 *
 * @author jaden
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @RequiresPermissions("sys:role:list")
    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("sysManage/role");
    }

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    @CacheEvict(value = "users", allEntries = true)
    public Object list(@RequestBody Map<String, Object> params) {
        PageUtils page = sysRoleService.queryPage(params);
        return setSuccessModelMap(page);
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public Object select() {
        List<SysRole> list = sysRoleService.selectList(null);
        return setSuccessModelMap(list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public Object info(@PathVariable("roleId") String roleId) {
        SysRole role = sysRoleService.selectById(roleId);
		
		/*SysDept sysDeptEntity = sysDeptService.selectById(role.getDeptId());
		role.setDeptName(sysDeptEntity.getName());*/

        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        //查询角色对应的部门
		/*List<String> deptIdList = sysRoleDeptService.queryDeptIdList(new String[]{roleId});
		role.setDeptIdList(deptIdList);*/

        return setSuccessModelMap(role);
    }

    /**
     * 保存角色
     */
    @SysLogAp("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    @ManagerLogAp(usePattern = true, value = "新增角色,角色名为[${roleName}]" ,type = 1)
    public Object save(@RequestBody SysRole role) {
        ValidatorUtils.validateEntity(role);
        role.setCreateTime(new Date());
        role.setCreateBy(ShiroUtils.getUserEntity().getUsername());
        sysRoleService.save(role);
        return setSuccessModelMap(1);
    }

    /**
     * 修改角色
     */
    @SysLogAp("修改角色")
    @PutMapping("/update")
    @RequiresPermissions("sys:role:update")
    @ManagerLogAp(usePattern = true, value = "对角色id [${id}]进行修改操作" ,type = 2)
    public Object update(@RequestBody SysRole role) {
//		ValidatorUtils.validateEntity(role);
        role.setUpdateBy(ShiroUtils.getUserEntity().getUsername());
        role.setUpdateTime(new Date());
        sysRoleService.update(role);
        return setSuccessModelMap(1);
    }

    /**
     * 删除角色
     */
    @SysLogAp("删除角色")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    @ManagerLogAp(usePattern = true, value = "批量删除角色,角色id列表为[${}]" ,type = 3)
    public Object delete(@RequestBody String[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return setSuccessModelMap(1);
    }
}
