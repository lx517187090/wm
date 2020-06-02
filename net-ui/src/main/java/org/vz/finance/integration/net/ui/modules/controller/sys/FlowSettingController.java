package org.vz.finance.integration.net.ui.modules.controller.sys;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.manage.sys.service.SysConfigService;
import org.vz.finance.integration.model.SysConfig;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;

import java.util.List;

/**
 * 流程设置
 */
@RestController
@RequestMapping("/sys/flow")
public class FlowSettingController extends BaseController {
    @Autowired
    private SysConfigService sysConfigService;

    @RequestMapping("")
    @RequiresPermissions("sys:flow:list")
    public ModelAndView index(){
        return new ModelAndView("sysManage/flowSetting");
    }

    /**
     * 所有配置列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:flow:list")
    public Object list(){
        List<SysConfig> sysConfigs = sysConfigService.queryListForFlowSetting();
        return setSuccessModelMap(sysConfigs);
    }

    /**
     * 修改配置
     */
    @SysLogAp("修改配置")
    @PutMapping("/update")
    @RequiresPermissions("sys:flow:update")
    public Object update(@RequestBody SysConfig config){
        sysConfigService.update(config);
        return setSuccessModelMap(new ModelMap());
    }


}
