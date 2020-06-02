package org.vz.finance.integration.net.ui.modules.service;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vz.finance.integration.model.util.ToolForID;
import org.vz.finance.integration.net.ui.modules.dao.WeimaiConfigDao;
import org.vz.finance.integration.net.ui.modules.entity.WmConfig;
import org.vz.finance.integration.net.ui.modules.entity.WmConfigExample;
import org.vz.finance.integration.net.ui.modules.mappers.WmConfigMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class ConfigService extends ServiceImpl<WeimaiConfigDao, WmConfig> {

    @Autowired
    private WmConfigMapper wmConfigMapper;

    public List<WmConfig> queryInfo(WmConfig wmConfig){
        PageHelper.startPage(wmConfig.getCurrentPage(), wmConfig.getPageSize());
        WmConfigExample example = new WmConfigExample();
        WmConfigExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(wmConfig.getConfGroup())) {
            criteria.andConfGroupEqualTo(wmConfig.getConfGroup());
        }
        if (StringUtils.isNotBlank(wmConfig.getConfKey())) {
            criteria.andConfKeyEqualTo(wmConfig.getConfKey());
        }
        if(StringUtils.isNotBlank(wmConfig.getConfName())) {
            criteria.andConfNameLike("%" + wmConfig.getConfName() + "%");
        }
        return wmConfigMapper.selectByExample(example);
    }

    public List<WmConfig> getConfig(WmConfig wmConfig){
        WmConfigExample example = new WmConfigExample();
        WmConfigExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(wmConfig.getConfGroup())) {
            criteria.andConfGroupEqualTo(wmConfig.getConfGroup());
        }
        return wmConfigMapper.selectByExample(example);
    }

    public void save(WmConfig config) {
        config.setId(ToolForID.getConfID());
        wmConfigMapper.insert(config);
    }

    public void update(WmConfig config) {
        wmConfigMapper.updateByPrimaryKey(config);
    }

    public void deleteBatch(String[] ids) {
        // 删除基础信息维护数据
        this.deleteBatchIds(Arrays.asList(ids));
    }
}
