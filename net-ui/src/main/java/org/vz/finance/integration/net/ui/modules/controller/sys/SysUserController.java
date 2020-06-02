package org.vz.finance.integration.net.ui.modules.controller.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.service.SysUserRoleService;
import org.vz.finance.integration.manage.sys.service.SysUserService;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.core.utils.ValidatorUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author jaden
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequiresPermissions("sys:user:list")
    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("sysManage/admin");
    }

    /**
     * 所有用户列表
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Object list(@RequestBody Map<String, Object> params) {
        PageUtils page = sysUserService.queryPage(params);

        return setSuccessModelMap(page);
    }

    @PostMapping("/validateUserName")
    public Object validateUserName(@RequestBody Map<String, Object> params) {
        EntityWrapper<SysUser> sysUserEntityWrapper = new EntityWrapper<>();
        sysUserEntityWrapper.eq("username", params.get("username"));
        int i = sysUserService.selectCount(sysUserEntityWrapper);
        if (i > 0) {
            return setSuccessModelMap(false);
        }
        return setSuccessModelMap(true);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Object info() {
        return setSuccessModelMap(ShiroUtils.getUserEntity());
    }

    /**
     * 修改登录用户密码
     */
    @SysLogAp("修改密码")
    @PutMapping("/password")
    @ManagerLogAp(usePattern = true, value = "修改登录用户密码,用户名为[${username}]" ,type = 2)
    public Object password(@RequestBody Map<String, Object> params) {
        String newPassword = String.valueOf(params.get("newPassword"));
        String password = String.valueOf(params.get("password"));
        //原密码
        password = ShiroUtils.sha256(password, "SALT");
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, "SALT");
        //更新密码
        boolean flag = sysUserService.updatePassword(ShiroUtils.getUserId(), password, newPassword);
        if (!flag) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "原密码不正确");
        }
        return setSuccessModelMap(1);
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public Object info(@PathVariable("userId") String userId) {
        SysUser user = sysUserService.selectById(userId);
		/*SysDept sysDeptEntity = sysDeptService.selectById(user.getDeptId());
		user.setDeptName(sysDeptEntity.getName());*/
        //获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return setSuccessModelMap(user);
    }

    /**
     * 保存用户
     */
    @SysLogAp("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    @ManagerLogAp(usePattern = true, value = "新增用户,用户名为[${username}]" ,type = 1)
    public Object save(@RequestBody SysUser user) {
        ValidatorUtils.validateEntity(user);
        EntityWrapper<SysUser> sysUserEntityWrapper = new EntityWrapper<>();
        sysUserEntityWrapper.eq("username", user.getUsername());
        int i = sysUserService.selectCount(sysUserEntityWrapper);
        if (i > 0) {
            return setModelMap(new ModelMap(), HttpCode.CONFLICT, "已存在该用户名！");
        }
        user.setCreateBy(ShiroUtils.getUserEntity().getUsername());
        user.setCreateTime(new Date());
        sysUserService.save(user);
        return setSuccessModelMap(1);
    }

    /**
     * 修改用户
     */
    @SysLogAp("修改用户")
    @PutMapping("/update")
    @RequiresPermissions("sys:user:update")
    @ManagerLogAp(usePattern = true, value = "对用户[${username}]进行修改操作" ,type = 2)
    public Object update(@RequestBody SysUser user) {
//		ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setUpdateBy(ShiroUtils.getUserEntity().getUsername());
        user.setUpdateTime(new Date());
        sysUserService.update(user);
        return setSuccessModelMap(1);
    }

    /**
     * 删除用户
     */
    @SysLogAp("删除用户")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    @ManagerLogAp(usePattern = true, value = "批量删除用户,用户id列表为[${}]" ,type = 3)
    public Object delete(@RequestBody String[] userIds) {
        if (ArrayUtils.contains(userIds, "1")) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, ShiroUtils.getUserId())) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "当前用户不能删除");
        }
        sysUserService.deleteBatchIds(Arrays.asList(userIds));
        return setSuccessModelMap(userIds.length);
    }

    /**
     * 批量重置密码
     */
    @SysLogAp("批量重置密码")
    @PutMapping("/resetPwd")
    @RequiresPermissions("sys:user:resetPwd")
    @ManagerLogAp(usePattern = true, value = "批量重置密码,用户id列表为[${}]" ,type = 2)
    public Object resetPwd(@RequestBody String[] userIds) {
        if (ArrayUtils.contains(userIds, "1")) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "系统管理员不能重置密码！");
        }
        sysUserService.resetBatchIds(Arrays.asList(userIds));
        return setSuccessModelMap(userIds.length);
    }

    /**
     * 查询所有用户
     */
    @PostMapping("/allUser")
    public Object allUser() {
        List<SysUser> stringListMap = sysUserService.getAllUser();
        return setSuccessModelMap(stringListMap);
    }
}
