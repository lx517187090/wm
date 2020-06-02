package org.vz.finance.integration.net.ui.core.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface IBaseService<T> extends IService<T> {
    Page<?> getPage(Page<String> var1);

    Page<Map<String, Object>> getPageMap(Page<String> var1);

    <K> Page<K> getPage(Page<String> var1, Class<K> var2);

    List<?> getList(List<String> var1);

    <K> List<K> getList(List<String> var1, Class<K> var2);

    void del(String var1, String var2);

    void delete(String var1);

    T update(T var1);

    T queryById(String var1);

    Page<?> query(Map<String, Object> var1);

    Page<Map<String, Object>> queryMap(Map<String, Object> var1);

    List<?> queryList(Map<String, Object> var1);

    Page<T> selectPageByWrapper(Page<T> var1, Map<String, Object> var2, Class<T> var3) throws Exception;

    int updateByWrapper(T var1, Map<String, Object> var2, Class<T> var3) throws Exception;
}
