package org.vz.finance.integration.net.ui.modules.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.service.SysConfigService;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.model.SysConfig;
import org.vz.finance.integration.model.util.ToolForID;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;

import java.util.Date;
import java.util.Map;


/**
 * 系统配置信息
 * 
 * @author chenshun
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/parameter")
public class SysConfigController extends BaseController {
	@Autowired
	private SysConfigService sysConfigService;

    @RequestMapping("")
    public ModelAndView index(){
	    return new ModelAndView("sysManage/parameter");
    }
	
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:parameter:list")
	public Object list(@RequestBody Map<String, Object> params){
        PageUtils page = sysConfigService.queryPage(params);

		return setSuccessModelMap(page);
	}
	
	
	/**
	 * 配置信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("sys:parameter:info")
	public Object info(@PathVariable("id") String id){
		SysConfig config = sysConfigService.selectById(id);
		
		return setSuccessModelMap(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLogAp("保存配置")
	@PostMapping("/save")
	@RequiresPermissions("sys:parameter:save")
	public Object save(@RequestBody SysConfig config){
//		ValidatorUtils.validateEntity(config);
        config.setId(ToolForID.getSysConfigID());
        config.setCreateBy(ShiroUtils.getUserEntity().getUsername());
        config.setCreateTime(new Date());
		sysConfigService.save(config);
		
		return setSuccessModelMap(new ModelMap());
	}
	
	/**
	 * 修改配置
	 */
	@SysLogAp("修改配置")
	@PutMapping("/update")
	@RequiresPermissions("sys:parameter:update")
	public Object update(@RequestBody SysConfig config){
//		ValidatorUtils.validateEntity(config);
		config.setUpdateBy(ShiroUtils.getUserEntity().getUsername());
		config.setUpdateTime(new Date());
		sysConfigService.update(config);
		
		return setSuccessModelMap(new ModelMap());
	}
	
	/**
	 * 删除配置
	 */
	@SysLogAp("删除配置")
	@DeleteMapping("/delete")
	@RequiresPermissions("sys:parameter:delete")
	public Object delete(@RequestBody String[] ids){
		sysConfigService.deleteBatch(ids);
		
		return setSuccessModelMap(new ModelMap());
	}

}
