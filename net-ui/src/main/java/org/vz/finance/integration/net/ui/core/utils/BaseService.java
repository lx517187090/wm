package org.vz.finance.integration.net.ui.core.utils;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.ClassUtil;
import com.xiaoleilu.hutool.util.BeanUtil.CopyOptions;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<M extends BaseMapper<T>, T extends BaseModel<T>>
        extends ServiceImpl<M, T> implements IBaseService<T>, ApplicationContextAware {

    protected Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    protected M mapper;

    protected ApplicationContext applicationContext;

    public BaseService() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static Page<String> getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "id_";
        if (DataUtil.isNotEmpty(params.get("pageNum"))) {
            current = Integer.valueOf(params.get("pageNum").toString());
        }

        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }

        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String)params.get("orderBy");
            params.remove("orderBy");
        }

        if (size == -1) {
            return new Page();
        } else {
            Page<String> page = new Page(current, size, orderBy);
            page.setAsc(false);
            return page;
        }
    }

    public Page<T> getPage(Page<String> ids) {
        if (ids == null) {
            return new Page();
        } else {
            Page<T> page = new Page(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            List<T> records = InstanceUtil.newArrayList();
            Iterator var4 = ids.getRecords().iterator();

            while(var4.hasNext()) {
                String id = (String)var4.next();
                records.add(this.queryById(id));
            }

            page.setRecords(records);
            return page;
        }
    }

    public Page<Map<String, Object>> getPageMap(Page<String> ids) {
        if (ids == null) {
            return new Page();
        } else {
            Page<Map<String, Object>> page = new Page(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            List<Map<String, Object>> records = InstanceUtil.newArrayList();
            Iterator var4 = ids.getRecords().iterator();

            while(var4.hasNext()) {
                String id = (String)var4.next();
                records.add(InstanceUtil.transBean2Map(this.queryById(id)));
            }

            page.setRecords(records);
            return page;
        }
    }

    public <K> Page<K> getPage(Page<String> ids, Class<K> cls) {
        if (ids == null) {
            return new Page();
        } else {
            Page<K> page = new Page(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            List<K> records = InstanceUtil.newArrayList();
            Iterator var5 = ids.getRecords().iterator();

            while(var5.hasNext()) {
                String id = (String)var5.next();
                T t = this.queryById(id);
                K k = InstanceUtil.to(t, cls);
                records.add(k);
            }

            page.setRecords(records);
            return page;
        }
    }

    public List<T> getList(List<String> ids) {
        List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            Iterator var3 = ids.iterator();

            while(var3.hasNext()) {
                String id = (String)var3.next();
                list.add(this.queryById(id));
            }
        }

        return list;
    }

    public <K> List<K> getList(List<String> ids, Class<K> cls) {
        List<K> list = InstanceUtil.newArrayList();
        if (ids != null) {
            Iterator var4 = ids.iterator();

            while(var4.hasNext()) {
                String id = (String)var4.next();
                T t = this.queryById(id);
                K k = InstanceUtil.to(t, cls);
                list.add(k);
            }
        }

        return list;
    }

    @Transactional
    public void del(String id, String userId) {
        try {
            T record = this.queryById(id);
            record.setUpdateTime(new Date());
            record.setUpdateBy(userId);
            this.mapper.updateById(record);
        } catch (Exception var4) {
            this.logger.error(var4.getMessage(), var4);
            throw new RuntimeException(var4.getMessage(), var4);
        }
    }

    @Transactional
    public void delete(String id) {
        try {
            this.mapper.deleteById(id);
        } catch (Exception var3) {
            this.logger.error(var3.getMessage(), var3);
            throw new RuntimeException(var3.getMessage(), var3);
        }
    }

    @Transactional
    public T update(T record) {
        String lockKey;
        try {
            record.setUpdateTime(new Date());
            if (record.getId() == null) {
                record.setCreateTime(new Date());
                this.mapper.insert(record);
            } else {
                T org = this.queryById(record.getId());
                lockKey = this.getLockKey(record.getId());
                if (StringUtils.isBlank(lockKey)) {
                    T update = InstanceUtil.getDiff(org, record);
                    update.setId(record.getId());
                    this.mapper.updateById(update);
                }
            }

            return record;
        } catch (DuplicateKeyException var5) {
            lockKey = ExceptionUtil.getStackTraceAsString(var5);
            this.logger.error("OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :" + lockKey, var5);
            throw new RuntimeException("已经存在相同的配置.");
        } catch (Exception var6) {
            lockKey = ExceptionUtil.getStackTraceAsString(var6);
            this.logger.error("OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :" + lockKey, var6);
            throw new RuntimeException(lockKey);
        }
    }

    @Transactional
    public T queryById(String id) {
        try {
            String key = this.getCacheKey(id);
            return StringUtils.isBlank(key) ? this.mapper.selectById(id) : null;
        } catch (Exception var3) {
            this.logger.error(var3.getMessage(), var3);
            throw new RuntimeException(var3.getMessage(), var3);
        }
    }

    public Page<T> query(Map<String, Object> params) {
        Page<String> page = getPage(params);
        page.setRecords(this.mapper.selectIdPage(page, params));
        return this.getPage(page);
    }

    public Page<Map<String, Object>> queryMap(Map<String, Object> params) {
        Page<String> page = getPage(params);
        page.setRecords(this.mapper.selectIdPage(page, params));
        return this.getPageMap(page);
    }

    public List<T> queryList(Map<String, Object> params) {
        List<String> ids = this.mapper.selectIdPage(params);
        List<T> list = this.getList(ids);
        return list;
    }

    protected <P> Page<P> query(Map<String, Object> params, Class<P> cls) {
        Page<String> page = getPage(params);
        page.setRecords(this.mapper.selectIdPage(page, params));
        return this.getPage(page, cls);
    }

    protected void sleep(int millis) {
        try {
            Thread.sleep(RandomUtils.nextLong(10L, (long)millis));
        } catch (InterruptedException var3) {
            this.logger.error("", var3);
        }

    }

    protected String getCacheKey(Object id) {
        String cacheName = this.getCacheKey();
        return StringUtils.isBlank(cacheName) ? null : "VZcLOUD:" + cacheName + ":" + id;
    }

    protected String getLockKey(Object id) {
        String cacheName = this.getCacheKey();
        return StringUtils.isBlank(cacheName) ? null : "VZcLOUD:" + cacheName + ":LOCK:" + id;
    }

    private String getCacheKey() {
        Class<?> cls = this.getClass();
        String cacheName = (String)Constants.cacheKeyMap.get(cls);
        if (StringUtils.isBlank(cacheName)) {
            CacheConfig cacheConfig = (CacheConfig)cls.getAnnotation(CacheConfig.class);
            if (cacheConfig == null) {
                return null;
            }

            if (cacheConfig.cacheNames() != null && cacheConfig.cacheNames().length >= 1) {
                cacheName = cacheConfig.cacheNames()[0];
            } else {
                cacheName = this.getClass().getName();
            }

            Constants.cacheKeyMap.put(cls, cacheName);
        }

        return cacheName;
    }

    public Page<T> selectPageByWrapper(Page<T> page, Map<String, Object> paramMap, String[] ignoreProperties, Class<T> clazz) throws Exception {
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreProperties(ignoreProperties);
        T t = BeanUtil.fillBeanWithMap(paramMap, ClassUtil.newInstance(clazz), copyOptions);
        return this.selectPageByWrapper(page, t, clazz);
    }

    public Page<T> selectPageByWrapper(Page<T> page, Map<String, Object> paramMap, Class<T> clazz) throws Exception {
        T t = BeanUtil.mapToBeanIgnoreCase(paramMap, clazz, true);
        return this.selectPageByWrapper(page, t, clazz);
    }

    public Page<T> selectPageByWrapper(Page<T> page, T t, Class<T> clazz) throws Exception {
        EntityWrapper<T> TEntityWrapper = new EntityWrapper();
        TEntityWrapper.setEntity(t);
        page.setRecords(this.mapper.selectPage(page, TEntityWrapper));
        return page;
    }

    public int updateByWrapper(T t, Map<String, Object> paramMap, Class<T> clazz) throws Exception {
        EntityWrapper<T> TEntityWrapper = new EntityWrapper();
        T cond = BeanUtil.mapToBeanIgnoreCase(paramMap, clazz, true);
        TEntityWrapper.setEntity(cond);
        return this.mapper.update(t, TEntityWrapper);
    }
}
