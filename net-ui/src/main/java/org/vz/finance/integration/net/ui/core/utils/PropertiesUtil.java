package org.vz.finance.integration.net.ui.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.*;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
    private static final byte[] KEY = {9, -1, 0, 5, 39, 8, 6, 19};

    private static Map<String, String> ctxPropertiesMap;
    private List<String> decryptProperties;

    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        ctxPropertiesMap = new HashMap();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if (this.decryptProperties != null && this.decryptProperties.contains(keyStr)) {
                value = SecurityUtil.decryptDes(value, KEY);
                props.setProperty(keyStr, value);
            }
            ctxPropertiesMap.put(keyStr, value);
        }
    }


    public void setDecryptProperties(List<String> decryptProperties) {
        this.decryptProperties = decryptProperties;
    }


    public static String getString(String key) {
        try {
            return (String) ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }


    public static int getInt(String key) {
        return Integer.parseInt((String) ctxPropertiesMap.get(key));
    }


    public static int getInt(String key, int defaultValue) {
        String value = (String) ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }


    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = (String) ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return (new Boolean(value)).booleanValue();
    }

}