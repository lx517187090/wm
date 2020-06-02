package org.vz.finance.integration.net.ui.modules.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.service.SysDeptService;
import org.vz.finance.integration.manage.sys.service.SysRoleService;
import org.vz.finance.integration.manage.sys.service.SysUserService;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.model.SysDept;
import org.vz.finance.integration.model.SysRole;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.model.util.ToolForID;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 部门管理
 * 
 * @author jaden
 * 
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseController {
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
    private SysUserService sysUserService;
	@Autowired
    private SysRoleService sysRoleService;

	@RequiresPermissions("sys:dept:list")
	@RequestMapping("")
	public ModelAndView index(){
		return new ModelAndView("sysManage/department");
	}
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public Object list(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<String, Object>());

		return setSuccessModelMap(deptList);
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:dept:select")
	public Object select(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<String, Object>());
		SysUser sysUser = ShiroUtils.getUserEntity();
		
		//添加一级部门
		if(sysUser.getId() == Constant.SUPER_ADMIN){
			SysDept root = new SysDept();
			root.setId("0");
			root.setName("一级部门");
			root.setParentId("-1");
//			root.setOpen(true);
			deptList.add(root);
		}

		return setSuccessModelMap(deptList);
	}

	/**
	 * 上级部门Id(管理员则为0)
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public Object info(){
		long id = 0;
		// TODO 重写该逻辑
//		if(sysUser.getId() != Constant.SUPER_ADMIN){
//			List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
//			String parentId = null;
//			for(SysDeptEntity sysDeptEntity : deptList){
//				if(parentId == null){
//					parentId = sysDeptEntity.getParentId();
//					continue;
//				}
//
//				if(parentId > sysDeptEntity.getParentId().longValue()){
//					parentId = sysDeptEntity.getParentId();
//				}
//			}
//			id = parentId;
//		}
		return setSuccessModelMap(id);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:dept:info")
	public Object info(@PathVariable("id") String id){
		SysDept dept = sysDeptService.selectById(id);
		
		return setSuccessModelMap(dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	@ManagerLogAp(usePattern = true, value = "新增部门,部门名称为[${name}]" ,type = 1)
	public Object save(@RequestBody SysDept dept){
		dept.setId(ToolForID.getSysDeptID());
		dept.setEnable(1);
		dept.setCreateTime(new Date());
		dept.setCreateBy(ShiroUtils.getUserEntity().getUsername());
		sysDeptService.insert(dept);
		
		return setSuccessModelMap(1);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	@ManagerLogAp(usePattern = true, value = "对部门id为[${id}]进行修改操作", type = 2)
	public Object update(@RequestBody SysDept dept){
	    dept.setUpdateBy(ShiroUtils.getUserEntity().getUsername());
	    dept.setUpdateTime(new Date());
		sysDeptService.updateById(dept);
		
		return setSuccessModelMap(1);
	}
	
	/**
	 * 删除
	 */
	@DeleteMapping("/delete/{id}")
	@RequiresPermissions("sys:dept:delete")
	@ManagerLogAp(usePattern = true, value = "删除部门,部门id为[${}]" ,type = 3)
	public Object delete(@PathVariable String id){
		//判断是否有子部门
		List<String> deptList = sysDeptService.queryDetpIdList(id);
		//判断是否在管理员管理中被引用
        List<SysUser> sysUsers = sysUserService.selectList(new EntityWrapper<SysUser>()
                .eq("dept_id", id));
        //判断是否在角色管理中被引用
        List<SysRole> sysRoles = sysRoleService.selectList(new EntityWrapper<SysRole>()
                .eq("dept_id", id));
        if(!deptList.isEmpty() || !sysUsers.isEmpty() || !sysRoles.isEmpty()){
			return setSuccessModelMap(0);
		}

		sysDeptService.deleteById(id);
		
		return setSuccessModelMap(1);
	}
	
}
