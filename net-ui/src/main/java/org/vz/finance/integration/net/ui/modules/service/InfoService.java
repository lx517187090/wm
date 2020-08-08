package org.vz.finance.integration.net.ui.modules.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.vz.finance.integration.model.util.ToolForID;
import org.vz.finance.integration.net.ui.modules.entity.*;
import org.vz.finance.integration.net.ui.modules.mappers.WmConfigMapper;
import org.vz.finance.integration.net.ui.modules.mappers.WmDeliveryInfoMapper;
import org.vz.finance.integration.net.ui.modules.mappers.WmOrderInfoMapper;
import org.vz.finance.integration.net.ui.modules.mappers.WmRepairInfoMapper;
import org.vz.finance.integration.net.ui.modules.vo.WmOrderInfoVO;

import java.util.*;

@Service
public class InfoService {

    @Autowired
    private WmOrderInfoMapper wmOrderInfoMapper;

    @Autowired
    private IInfoService iInfoService;

    @Autowired
    private WmDeliveryInfoMapper wmDeliveryInfoMapper;

    @Autowired
    private WmRepairInfoMapper wmRepairInfoMapper;

    @Autowired
    private WmConfigMapper wmConfigMapper;


    public Page<WmOrderInfoVO> queryInfo(WmOrderInfoVO info) {
        Page<WmOrderInfoVO> page = new Page<>(info.getCurrentPage(), info.getPageSize());
        List<WmOrderInfoVO> list = wmDeliveryInfoMapper.selectInfo(page, info);
        page.setRecords(list);
        return page;
    }


    public Page<WmOrderInfo> queryInfo2(WmOrderInfo info) {
        Page<WmOrderInfo> page = new Page<>(info.getCurrentPage(), info.getPageSize());
        EntityWrapper<WmOrderInfo> orderPage = buildWrapper(info.getName(),
                info.getMobilePhone(), info.getCourierNumber());
        return iInfoService.selectPage(page, orderPage);
    }

    /**公共列表查询方法*/
    public Page<WmOrderInfoVO> queryDeliveryInfo2(WmOrderInfoVO info) {
        Page<WmOrderInfo> page = new Page<>(info.getCurrentPage(), info.getPageSize());
        EntityWrapper<WmOrderInfo> orderPage = buildWrapper(info.getName(),
                info.getMobilePhone(), info.getCourierNumber());
        orderPage.like(StringUtils.isNotBlank(info.getSn()), "SN", info.getSn());
        if ("1".equals(info.getDeliverySign())) {
            orderPage.eq(StringUtils.isNotBlank(info.getDeliverySign()), "DELIVERY_SIGN", info.getDeliverySign());
        } else if("0".equals(info.getDeliverySign())){
            orderPage.isNull("DELIVERY_SIGN");
        }
        if (StringUtils.isNotBlank(info.getHappenDate())) {
            orderPage.ge(StringUtils.isNotBlank(info.getHappenDate()), "HAPPEN_DATE",
                    DateUtil.beginOfDay(DateUtil.parse(info.getHappenDate(), DatePattern.NORM_DATE_PATTERN)))
                    .le(StringUtils.isNotBlank(info.getHappenDate()), "HAPPEN_DATE",
                            DateUtil.endOfDay(DateUtil.parse(info.getHappenDate(), DatePattern.NORM_DATE_PATTERN)));
        }
        if (StringUtils.isNotBlank(info.getDeliveryDate())) {
            orderPage.ge(StringUtils.isNotBlank(info.getDeliveryDate()), "delivery_date",
                    DateUtil.beginOfDay(DateUtil.parse(info.getDeliveryDate(), DatePattern.NORM_DATE_PATTERN)))
                    .le(StringUtils.isNotBlank(info.getDeliveryDate()), "delivery_date",
                            DateUtil.endOfDay(DateUtil.parse(info.getDeliveryDate(), DatePattern.NORM_DATE_PATTERN)));
        }
        page = iInfoService.selectPage(page, orderPage);
        List<WmOrderInfoVO> voList = buildDeliveryVoList(page.getRecords());
        Page<WmOrderInfoVO> retPage = new Page<>();
        BeanUtil.copyProperties(page, retPage);
        retPage.setRecords(voList);
        return retPage;
    }


    public Page<WmOrderInfoVO> queryDeliveryInfo(WmOrderInfoVO info) {
        Page<WmOrderInfoVO> page = new Page<>(info.getCurrentPage(), info.getPageSize());
        //写发货标志判断
        if (StringUtils.isNotBlank(info.getDeliverySign())) {

        }
        List<WmOrderInfoVO> list = wmDeliveryInfoMapper.selectDelivery(page, info);
        page.setRecords(list);
        return page;
    }


    private EntityWrapper<WmOrderInfo> buildWrapper(String name, String mobile, String courierNumber) {
        EntityWrapper<WmOrderInfo> orderPage = new EntityWrapper<>();
        orderPage
                .eq("enable_", "1")
                .like(StringUtils.isNoneBlank(name), "name", name)
                .like(StringUtils.isNoneBlank(courierNumber), "courier_number", courierNumber)
                .like(StringUtils.isNoneBlank(mobile), "mobile_phone", mobile)
                .orderBy("happen_date", false);
        return orderPage;
    }

    public List<WmOrderInfoVO> queryDeliveryInfoList(WmOrderInfoVO info) {
        PageHelper.startPage(info.getCurrentPage(), info.getPageSize());
        return wmOrderInfoMapper.selectDeliver(info);
    }

    public Page<WmOrderInfoVO> queryRepairInfo2(WmOrderInfoVO info) {
        Page<WmOrderInfo> page = new Page<>(info.getCurrentPage(), info.getPageSize());
        EntityWrapper<WmOrderInfo> orderPage = buildWrapper(info.getName(),
                info.getMobilePhone(), info.getCourierNumber());
        page = iInfoService.selectPage(page, orderPage);
        Page<WmOrderInfoVO> retPage = new Page<>();
        List<WmOrderInfoVO> voList = buildVoList(page.getRecords());
        BeanUtil.copyProperties(page, retPage);
        retPage.setRecords(voList);
        return retPage;
    }

    public Page<WmOrderInfoVO> queryRepairInfo(WmOrderInfoVO info) {
        Page<WmOrderInfoVO> page = new Page<>(info.getCurrentPage(), info.getPageSize());
        List<WmOrderInfoVO> list = wmDeliveryInfoMapper.selectRepair(page, info);
        page.setRecords(list);
        return page;
    }

    private List<WmOrderInfoVO> buildVoList(List<WmOrderInfo> records) {
        List<WmOrderInfoVO> list = new ArrayList<>();
        records.forEach(record -> {
            WmOrderInfoVO temp = new WmOrderInfoVO();
            BeanUtil.copyProperties(record, temp);
            WmRepairInfoExample example = new WmRepairInfoExample();
            WmRepairInfoExample.Criteria criteria = example.createCriteria();
            criteria.andInfoIdEqualTo(record.getId());
            criteria.andEnableEqualTo("1");
            temp.setHappenDate(DateUtil.format(record.getHappenDate(), "YYYY-MM-dd"));
            List<WmRepairInfo> wmRepairInfos = wmRepairInfoMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(wmRepairInfos)) {
                WmRepairInfo wmRepairInfo = wmRepairInfos.get(0);
                BeanUtil.copyProperties(wmRepairInfo, temp);
            } else {
                temp.setId(null);
            }
            temp.setInfoId(record.getId());
            list.add(temp);
        });
        return list;
    }


    private List<WmOrderInfoVO> buildDeliveryVoList(List<WmOrderInfo> records) {
        List<WmOrderInfoVO> list = new ArrayList<>();
        records.forEach(record -> {
            WmOrderInfoVO temp = new WmOrderInfoVO();
            BeanUtil.copyProperties(record, temp);
            WmDeliveryInfoExample example = new WmDeliveryInfoExample();
            WmDeliveryInfoExample.Criteria criteria = example.createCriteria();
            criteria.andInfoIdEqualTo(record.getId());
            criteria.andEnableEqualTo("1");
            temp.setHappenDate(DateUtil.format(record.getHappenDate(), "YYYY-MM-dd"));
            List<WmDeliveryInfo> wmRepairInfos = wmDeliveryInfoMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(wmRepairInfos)) {
                WmDeliveryInfo wmDeliveryInfo = wmRepairInfos.get(0);
                WmConfig wmConfigs = wmConfigMapper.selectByConfName(temp.getMachineModel());
                if (wmConfigs != null) {
                    temp.setWeight(wmConfigs.getConfKey());
                }
                BeanUtil.copyProperties(wmDeliveryInfo, temp);
                temp.setDeliveryDate(DateUtil.format(wmDeliveryInfo.getDeliveryDate(), "YYYY-MM-dd"));
            } else {
                temp.setId(null);
            }
            temp.setInfoId(record.getId());
            list.add(temp);
        });
        return list;
    }

    public void save(WmOrderInfo info) {
        info.setId(ToolForID.getWmInfoID());
        wmOrderInfoMapper.insert(info);
    }

    public void saveDelivery(WmDeliveryInfo info) {
        info.setId(ToolForID.getWmInfoID());
        info.setDeliverySign("1");
        wmDeliveryInfoMapper.insert(info);
        /*if (StringUtils.isNotBlank(info.getInfoId())) {
            WmOrderInfo wmOrderInfo = new WmOrderInfo();
            wmOrderInfo.setId(info.getInfoId());
            wmOrderInfo.setDeliveryDate(info.getDeliveryDate());
            wmOrderInfo.setRmk(info.getRmk());
            wmOrderInfo.setDeliverySign(info.getDeliverySign());
           iInfoService.updateById(wmOrderInfo);
        }*/
    }

    public void saveRepair(WmRepairInfo info) {
        info.setId(ToolForID.getWmInfoID());
        wmRepairInfoMapper.insert(info);
    }


    public List<WmDeliveryInfo> getDeliveryByInfoId(String infoId) {
        WmDeliveryInfoExample example = new WmDeliveryInfoExample();
        WmDeliveryInfoExample.Criteria criteria = example.createCriteria();
        criteria.andInfoIdEqualTo(infoId);
        criteria.andEnableEqualTo("1");
        return wmDeliveryInfoMapper.selectByExample(example);
    }


    public List<WmRepairInfo> getRepairByInfoId(String infoId) {
        WmRepairInfoExample example = new WmRepairInfoExample();
        WmRepairInfoExample.Criteria criteria = example.createCriteria();
        criteria.andInfoIdEqualTo(infoId);
        criteria.andEnableEqualTo("1");
        return wmRepairInfoMapper.selectByExample(example);
    }

    public void update(WmOrderInfo info) {
        wmOrderInfoMapper.updateByPrimaryKey(info);
    }

    public void updateDelivery(WmDeliveryInfo info) {
        wmDeliveryInfoMapper.updateByPrimaryKey(info);
    }

    public void updateRepair(WmRepairInfo info) {
        wmRepairInfoMapper.updateByPrimaryKey(info);
    }

    public void deleteBatch(String[] ids) {
        for (String id : ids) {
            WmOrderInfo orderInfo = new WmOrderInfo();
            orderInfo.setId(id);
            orderInfo.setEnable("0");
            wmOrderInfoMapper.updateByPrimaryKey2(orderInfo);
        }
    }

    public void deleteDeliveryBatch(String[] ids) {
        for (String id : ids) {
            WmDeliveryInfo deliveryInfo = new WmDeliveryInfo();
            deliveryInfo.setId(id);
            deliveryInfo.setEnable("0");
            wmDeliveryInfoMapper.updateByPrimaryKey2(deliveryInfo);
            wmOrderInfoMapper.updateDelevery(id);
        }
    }

    public void deleteRepairBatch(String[] ids) {
        for (String id : ids) {
            WmRepairInfo repairInfo = new WmRepairInfo();
            repairInfo.setId(id);
            repairInfo.setEnable("0");
            wmRepairInfoMapper.updateByPrimaryKey2(repairInfo);
        }
    }
}

