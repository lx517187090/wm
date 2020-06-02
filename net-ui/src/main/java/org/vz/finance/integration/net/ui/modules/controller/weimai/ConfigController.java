package org.vz.finance.integration.net.ui.modules.controller.weimai;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.core.utils.ValidatorUtils;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.vz.finance.integration.net.ui.modules.entity.WmConfig;
import org.vz.finance.integration.net.ui.modules.entity.WmDeliveryInfo;
import org.vz.finance.integration.net.ui.modules.service.ConfigService;
import org.vz.finance.integration.net.ui.modules.service.InfoService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weimai/config")
public class ConfigController extends BaseController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private ConfigService configService;

    /**
     * 基础信息维护列表跳转
     * @return
     */
    @RequiresPermissions("weimai:config")
    @RequestMapping("/list/{type}")
    public ModelAndView index(@PathVariable("type") String type, Model model) {
        ModelAndView view = null;
        if(type.equals("color")){//颜色
            view = new ModelAndView("weimai/colorConfigList");
        }else if(type.equals("express")){//快递
            view = new ModelAndView("weimai/expressConfigList");
        }else if(type.equals("machine")){//机器
            view = new ModelAndView("weimai/machineConfigList");
        }else if(type.equals("repair")){//维修
            view = new ModelAndView("weimai/repairConfigList");
        }else if(type.equals("parts")){//配件信息
            view = new ModelAndView("weimai/partsConfigList");
        }else if(type.equals("payAcc")){//付款账号
            view = new ModelAndView("weimai/payAccConfigList");
        }else if(type.equals("fault")){//故障信息
            view = new ModelAndView("weimai/faultConfigList");
        }
        model.addAttribute("type",type);
        return  view;
    }

    /**
     * 基础信息维护列表信息
     */
    @PostMapping("/table")
    @RequiresPermissions("weimai:config")
    public Object list(@RequestBody WmConfig wmConfig) {
        return setSuccessModelMap(configService.queryInfo(wmConfig));
    }

    /**
     * 保存配置信息
     */
    @PostMapping("/save")
    public Object save(@RequestBody WmConfig wmConfig) {
        configService.save(wmConfig);
        return setSuccessModelMap(1);
    }

    /**
     * 修改派单信息
     *
     * @param info
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody WmConfig info) {
        configService.update(info);
        return setSuccessModelMap(1);
    }

    /**
     * 删除基础信息维护数据
     */
    @SysLogAp("删除基础信息维护数据")
    @DeleteMapping("/delete")
    @RequiresPermissions("weimai:config")
    @ManagerLogAp(usePattern = true, value = "基础信息维护数据,id列表为[${}]" ,type = 3)
    public Object delete(@RequestBody String[] roleIds) {
        configService.deleteBatch(roleIds);
        return setSuccessModelMap(1);
    }

    /**
     * 基础信息维护列表信息
     */
    @PostMapping("/configList")
    public Object getConfigList(@RequestBody WmConfig config) {
        List<WmConfig> wmConfigs = configService.getConfig(config);
        return setSuccessModelMap(wmConfigs);
    }

}
