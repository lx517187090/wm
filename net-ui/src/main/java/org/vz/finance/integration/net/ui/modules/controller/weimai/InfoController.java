package org.vz.finance.integration.net.ui.modules.controller.weimai;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp;
import org.vz.finance.integration.net.ui.core.annotation.SysLogAp;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.core.utils.ExcelReportHelper;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.vz.finance.integration.net.ui.modules.entity.WmDeliveryInfo;
import org.vz.finance.integration.net.ui.modules.entity.WmOrderInfo;
import org.vz.finance.integration.net.ui.modules.entity.WmRepairInfo;
import org.vz.finance.integration.net.ui.modules.service.InfoService;
import org.vz.finance.integration.net.ui.modules.vo.WmOrderInfoVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weimai/info")
public class InfoController extends BaseController {

    @Autowired
    private InfoService infoService;

    /**
     * 订单录入列表跳转
     *
     * @return
     */
    @RequiresPermissions("weimai:info:list")
    @RequestMapping("/list")
    public ModelAndView index() {
        return new ModelAndView("weimai/addInfoList");
    }

    /**
     * 订单录入列表信息
     */
    @PostMapping("/table")
    @RequiresPermissions("weimai:info:list")
    public Object list(@RequestBody WmOrderInfoVO info) {
        return setSuccessModelMap(infoService.queryInfo(info));
    }

    /**
     * 保存订单信息
     */
    @SysLogAp("保存订单信息")
    @PostMapping("/save")
    @RequiresPermissions("weimai:info:save")
    @ManagerLogAp(usePattern = true, value = "新增订单信息，机器型号为[${machineModel}]" ,type = 1)
    public Object save(@RequestBody WmOrderInfo info) {
        info.setCreateDate(new Date());
        info.setCreateBy(ShiroUtils.getUserEntity().getUsername());
        String parts = "";
        if(StringUtils.isNotBlank(info.getParts())){
            parts = StrUtil.removeAll(info.getParts(),'[',']','"');
        }
        info.setParts(parts);
        infoService.save(info);
        return setSuccessModelMap(1);
    }

    /**
     * 修改订单信息
     */
    @SysLogAp("修改订单信息")
    @PostMapping("/update")
    @RequiresPermissions("weimai:info:update")
    @ManagerLogAp(usePattern = true, value = "对订单信息id [${id}]进行修改操作", type = 2)
    public Object update(@RequestBody WmOrderInfo info) {
        info.setUpdateBy(ShiroUtils.getUserEntity().getUsername());
        info.setUpdateDate(new Date());
        if(StringUtils.isNotBlank(info.getParts())){
            info.setParts(StrUtil.removeAll(info.getParts(),'[',']','"'));
        }
        infoService.update(info);
        return setSuccessModelMap(1);
    }

    /**
     * 删除订单信息
     */
    @SysLogAp("删除订单信息")
    @DeleteMapping("/delete")
    @RequiresPermissions("weimai:info:delete")
    @ManagerLogAp(usePattern = true, value = "删除订单信息,id列表为[${}]", type = 3)
    public Object delete(@RequestBody String[] roleIds) {
        infoService.deleteBatch(roleIds);
        return setSuccessModelMap(1);
    }


    /**
     * 派件跳转
     *
     * @return
     */
    @RequiresPermissions("weimai:delevery:list")
    @RequestMapping("/delivery")
    public ModelAndView delivery() {
        return new ModelAndView("weimai/deliveryList");
    }

    /**
     * 派件录入列表信息
     */
    @PostMapping("/deliveryTable")
    @RequiresPermissions("weimai:delevery:list")
    public Object deliveryTable(@RequestBody WmOrderInfoVO info) {
        return setSuccessModelMap(infoService.queryDeliveryInfo(info));
    }

    /**
     * 保存派单信息
     */
    @SysLogAp("保存派单信息")
    @PostMapping("/saveDelivery")
    @RequiresPermissions("weimai:delevery:save")
    @ManagerLogAp(usePattern = true, value = "保存派件信息，订单id为[{infoId}]" ,type = 1)
    public Object saveDelivery(@RequestBody WmDeliveryInfo info) {
        List<WmDeliveryInfo> deliveryByInfoId = infoService.getDeliveryByInfoId(info.getInfoId());
        if (deliveryByInfoId != null && !CollectionUtils.isEmpty(deliveryByInfoId)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "该订单存在派单信息，请删除后再重新派单");
        }
        infoService.saveDelivery(info);
        return setSuccessModelMap(1);
    }

    /**
     * 修改派单信息
     *
     * @param info
     * @return
     */
    @SysLogAp("修改派单信息")
    @PostMapping("/updateDelivery")
    @RequiresPermissions("weimai:delevery:update")
    @ManagerLogAp(usePattern = true, value = "对订单信息id [${id}]进行修改派单信息", type = 2)
    public Object updateDelivery(@RequestBody WmDeliveryInfo info) {
        if(StringUtils.isNotBlank(info.getId())) {
            infoService.updateDelivery(info);
        } else {
            infoService.saveDelivery(info);
        }
        return setSuccessModelMap(1);
    }

    /**
     * 删除派单信息
     */
    @SysLogAp("删除派单信息")
    @DeleteMapping("/deleteDelivery")
    @RequiresPermissions("weimai:delivery:delete")
    @ManagerLogAp(usePattern = true, value = "对订单信息id [${id}]进行删除", type = 3)
    public Object deleteDelivery(@RequestBody String[] deliveryIds) {
        infoService.deleteDeliveryBatch(deliveryIds);
        return setSuccessModelMap(1);
    }


    /**
     * 维修跳转
     *
     * @return
     */
    @RequiresPermissions("weimai:repair:list")
    @RequestMapping("/repairDis")
    public ModelAndView repair() {
        return new ModelAndView("weimai/repairList");
    }

    /**
     * 维修录入列表信息
     */
    @PostMapping("/repairTable")
    @RequiresPermissions("weimai:repair:list")
    public Object repairTable(@RequestBody WmOrderInfoVO info) {
        return setSuccessModelMap(infoService.queryRepairInfo(info));
    }

    /**
     * 保存维修信息
     */
    @SysLogAp("保存维修信息")
    @PostMapping("/saveRepair")
    @RequiresPermissions("weimai:repair:save")
    @ManagerLogAp(usePattern = true, value = "保存维修信息，订单id为[{infoId}]" ,type = 1)
    public Object saveRepair(@RequestBody WmRepairInfo info) {
        List<WmRepairInfo> repairByInfoId = infoService.getRepairByInfoId(info.getInfoId());
        if (repairByInfoId != null && !CollectionUtils.isEmpty(repairByInfoId)) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "该订单存在维修信息，请删除后再重新填写或修改");
        }
        infoService.saveRepair(info);
        return setSuccessModelMap(1);
    }

    /**
     * 修改维修信息
     *
     * @param info
     * @return
     */
    @SysLogAp("修改维修信息")
    @PostMapping("/updateRepair")
    @RequiresPermissions("weimai:repair:update")
    @ManagerLogAp(usePattern = true, value = "对订单信息id [${id}]进行修改维修信息", type = 2)
    public Object updateRepair(@RequestBody WmRepairInfo info) {
        if(null == info) {
            List<WmRepairInfo> repairByInfoId = infoService.getRepairByInfoId(info.getInfoId());
            if (repairByInfoId == null || CollectionUtils.isEmpty(repairByInfoId)) {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST, "该单还未填写维修信息，请填写后再修改");
            }
        }
        infoService.updateRepair(info);
        return setSuccessModelMap(1);
    }

    /**
     * 删除派单信息
     */
    @SysLogAp("删除维修信息")
    @DeleteMapping("/deleteRepair")
    @RequiresPermissions("weimai:repair:delete")
    @ManagerLogAp(usePattern = true, value = "对订单信息id [${id}]进行删除", type = 3)
    public Object deleteRepair(@RequestBody String[] deliveryIds) {
        infoService.deleteRepairBatch(deliveryIds);
        return setSuccessModelMap(1);
    }

    @GetMapping("/exportExcel")
    @ApiOperation("导出录入信息")
    @ManagerLogAp(value = "导出录入信息,参数为 ", type = 5)
    public void exportExcelContract(WmOrderInfoVO info, HttpServletResponse response) {
        logger.info("用户{}导出录入信息excel", ShiroUtils.getUserId());
        List<WmOrderInfoVO> list = infoService.queryDeliveryInfoList(info);
        logger.info("用户{}导出录入信息,返回结果:{}", ShiroUtils.getUserId(), JSON.toJSON(list));
        ExcelReportHelper.exportExcel(response, "派件信息", WmOrderInfoVO.class, list);
    }

}
