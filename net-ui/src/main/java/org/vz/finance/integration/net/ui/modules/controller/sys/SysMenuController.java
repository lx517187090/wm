package org.vz.finance.integration.net.ui.modules.controller.sys;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.service.SysMenuService;
import org.vz.finance.integration.manage.sys.util.Constant;
import org.vz.finance.integration.model.SysMenu;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.modules.vo.sys.AsidebarMenuVo;
import org.vz.finance.integration.net.ui.modules.vo.sys.ChildMenuVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 系统菜单
 * 
 * @author jaden
 * 
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;

	@RequiresPermissions("sys:menu:list")
	@RequestMapping("")
	public ModelAndView index(){
		return new ModelAndView("sysManage/menu");
	}

	/**
	 * 导航菜单
	 */
	@RequestMapping("/nav")
	public Object nav(){

		List<SysMenu> menuList = sysMenuService.getUserMenuList(ShiroUtils.getUserId());
		return setSuccessModelMap(menuList);
	}
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public Object list(){
		List<SysMenu> menuList = sysMenuService.selectList(null);
		for(SysMenu sysMenuEntity : menuList){
			SysMenu parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return setSuccessModelMap(menuList);
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public Object select(){
		//查询列表数据
		List<SysMenu> menuList = sysMenuService.queryNotButtonList();
        boolean b = menuList.stream().anyMatch(sysMenu -> "0".equals(sysMenu.getId()));
        if (!b){
            //添加顶级菜单
            SysMenu root = new SysMenu();
            root.setId("0");
            root.setName("一级菜单");
            root.setParentId("-1");
        //	root.setOpen(true);
            menuList.add(root);
        }
		return setSuccessModelMap(menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:menu:info")
	public Object info(@PathVariable("id") String id){
		SysMenu menu = sysMenuService.selectById(id);
		return setSuccessModelMap(menu);
	}
	
	/**
	 * 保存
	 */
	@SysLogAp("保存菜单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	@ManagerLogAp(usePattern = true, value = "新增菜单, 名称为[${name}],类型为[${type}]", type = 1)
	public Object save(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
		menu.setCreateBy(ShiroUtils.getUserEntity().getUsername());
		menu.setCreateTime(new Date());
		Integer id = sysMenuService.queryMaxId();
		menu.setId(String.valueOf(id + 1));
		sysMenuService.insert(menu);
		
		return setSuccessModelMap(1);
	}
	
	/**
	 * 修改
	 */
	@SysLogAp("修改菜单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	@ManagerLogAp(usePattern = true, value = "对id[${id}]菜单进行修改操作" ,type = 2)
	public Object update(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
		menu.setUpdateBy(ShiroUtils.getUserEntity().getUsername());
		menu.setUpdateTime(new Date());
		sysMenuService.updateById(menu);
		
		return setSuccessModelMap(1);
	}
	
	/**
	 * 删除
	 */
	@SysLogAp("删除菜单")
	@DeleteMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	@ManagerLogAp(usePattern = true, value = "批量删除菜单,菜单id列表为[${}]" ,type = 3)
	public Object delete(@RequestBody String[] ids){
	
		// 先支持菜单一次只能删除一个
		if(null == ids || ids.length == 0 || ids.length > 1)
			return setSuccessModelMap(0);
		
		String id = ids[0];
		/*if(Integer.valueOf(id) <= 30){
			return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "系统菜单，不能删除");
		}*/

		//判断是否有子菜单或按钮
		List<SysMenu> menuList = sysMenuService.queryListParentId(id);
		if(menuList.size() > 0){
			return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "请先删除子菜单或按钮");
		}

		sysMenuService.delete(id);

		return setSuccessModelMap(1);
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RuntimeException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RuntimeException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUri())){
				throw new RuntimeException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(!"0".equals(menu.getParentId())){
			SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new RuntimeException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new RuntimeException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}

    /**
     * 根据当前登录的用户id查询出其对应的菜单列表
     */
    @RequestMapping("/listMenu")
    public Object listMenuByUserId(){
        List<AsidebarMenuVo> asidebarMenuVoList = new ArrayList<>();
        String userId = ShiroUtils.getUserId();
        List<SysMenu> userMenuList = sysMenuService.getUserMenuList(userId);
        userMenuList.forEach(sysMenu -> {
            AsidebarMenuVo asidebarMenuVo = new AsidebarMenuVo();
            asidebarMenuVo.setName(sysMenu.getName());
            asidebarMenuVo.setUrl("#");
            asidebarMenuVo.setIndexNum(sysMenu.getOrderNum().toString());
            asidebarMenuVo.setIconClass(sysMenu.getIcon());
            asidebarMenuVoList.add(asidebarMenuVo);
            if (sysMenu.getSubSysMenus() == null){
                return;
            }
            sysMenu.getSubSysMenus().forEach(childSysMenu -> {
                ChildMenuVo childMenuVo = new ChildMenuVo();
                childMenuVo.setName(childSysMenu.getName());
                childMenuVo.setIndexNum(childSysMenu.getOrderNum().toString());
                String uri = childSysMenu.getUri();
                int lastIndex = uri.lastIndexOf("/");
                String parentPath = uri.substring(0,lastIndex);
                String path = uri.substring(lastIndex);
                childMenuVo.setParentPath(parentPath);
                childMenuVo.setPath(path);
                asidebarMenuVo.getChildren().add(childMenuVo);
            });

        });
        return setSuccessModelMap(asidebarMenuVoList);
    }
}
