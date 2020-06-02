package org.vz.finance.integration.manage.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vz.finance.integration.manage.sys.dao.SysConfigDao;
import org.vz.finance.integration.manage.sys.service.SysConfigService;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.manage.sys.util.Query;
import org.vz.finance.integration.model.SysConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {
    // TODO add redis cache
    // @Autowired
    // private SysConfigRedis sysConfigRedis;

    @Override
    @Cacheable(value = "configs",key = "'queryListForFlowSetting'")
    public List<SysConfig> queryListForFlowSetting() {
        Object[] arr = { "GuaranteeType", "MainAppSwitch", "CompatibilityAppSwitch" };
        return this.selectList(new EntityWrapper<SysConfig>().in("`key`", arr).eq("status", 1));
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /*
         * params.forEach((k,v) -> { params.put(k,v+""); });
         */
        String name = (String) params.get("name");
        Page<SysConfig> page = this.selectPage(new Query<SysConfig>(params).getPage(), new EntityWrapper<SysConfig>()
                .like(StringUtils.isNotBlank(name), "name", name).orderBy("CREATE_TIME", false));

        return new PageUtils(page);
    }

    @Override
    @CacheEvict(value = "configs",allEntries = true)
    public void save(SysConfig config) {
        this.insert(config);
        if (config == null) {
            throw new RuntimeException("需要保存配置信息不能为空");
        }
        // this.updateById(config);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "configs",allEntries = true)
    public void update(SysConfig config) {
        this.updateById(config);
        // sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "configs",allEntries = true)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
        // sysConfigRedis.delete(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "configs",allEntries = true)
    public void deleteBatch(String[] ids) {
        for (String id : ids) {
            SysConfig config = this.selectById(id);
            // sysConfigRedis.delete(config.getKey());
        }

        this.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public String getValue(String key) {
        SysConfig config = null;
        // SysConfigEntity config = sysConfigRedis.get(key);
        // if(config == null){
        // config = baseMapper.queryByKey(key);
        // sysConfigRedis.saveOrUpdate(config);
        // }

        return config == null ? null : config.getValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return JSONObject.parseObject(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("获取参数失败");
        }
    }

    @Override
    public List<SysConfig> selectByKey(String key) {
        SysConfig sysConfigMs = new SysConfig();
        sysConfigMs.setKey(key);
        sysConfigMs.setEnabled("1");
        EntityWrapper<SysConfig> ewm = new EntityWrapper<>(sysConfigMs);
        return this.selectList(ewm);
    }

    @Override
    public SysConfig selectOneByKey(String key) {
        List<SysConfig> sysConfigMs = this.selectByKey(key);
        if (null == sysConfigMs || sysConfigMs.size() == 0) {
            return null;
        }
        return sysConfigMs.get(0);
    }
}
