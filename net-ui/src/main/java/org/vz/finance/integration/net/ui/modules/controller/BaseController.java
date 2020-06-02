package org.vz.finance.integration.net.ui.modules.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.core.utils.InstanceUtil;
import org.vz.finance.integration.net.ui.core.utils.Pager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseController {
    protected final Logger logger = LogManager.getLogger(getClass());

    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }

    protected ResponseEntity<ModelMap> setSuccessModelMap(Object data) {
        return setModelMap(new ModelMap(), HttpCode.OK, data);
    }

    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }

    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        Map<String, Object> map = InstanceUtil.newLinkedHashMap();
        map.putAll(modelMap);
        modelMap.clear();
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
                modelMap.put(key, map.get(key));
            }
        }
        if (data != null) {
            if (data instanceof Pager) {
                Pager<?> page = (Pager) data;
                modelMap.put("data", page.getRecords());
                modelMap.put("current", Integer.valueOf(page.getCurrent()));
                modelMap.put("size", Integer.valueOf(page.getSize()));
                modelMap.put("pages", Integer.valueOf(page.getPages()));
                modelMap.put("total", Integer.valueOf(page.getTotal()));
                modelMap.put("iTotalRecords", Integer.valueOf(page.getTotal()));
                modelMap.put("iTotalDisplayRecords", Integer.valueOf(page.getTotal()));
            } else if (data instanceof Page) {
                Page<?> page = (Page) data;
                modelMap.put("data", page.getRecords());
                modelMap.put("current", Integer.valueOf(page.getCurrent()));
                modelMap.put("size", Integer.valueOf(page.getSize()));
                modelMap.put("pages", Integer.valueOf(page.getPages()));
                modelMap.put("total", Integer.valueOf(page.getTotal()));
                modelMap.put("iTotalRecords", Integer.valueOf(page.getTotal()));
                modelMap.put("iTotalDisplayRecords", Integer.valueOf(page.getTotal()));
            } else if (data instanceof List) {
                modelMap.put("data", data);
                modelMap.put("iTotalRecords", Integer.valueOf(((List) data).size()));
                modelMap.put("iTotalDisplayRecords", Integer.valueOf(((List) data).size()));
            } else {
                modelMap.put("data", data);
            }
        }
        modelMap.put("httpCode", code.value());
        modelMap.put("msg", code.msg());
        modelMap.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(modelMap);
    }
}