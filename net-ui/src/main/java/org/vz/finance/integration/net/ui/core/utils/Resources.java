package org.vz.finance.integration.net.ui.core.utils;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@PropertySource({"classpath:properties/thirdParty.properties", "classpath:i18n/messages*.properties"})
public final class Resources
{
    public static final ResourceBundle THIRDPARTY = ResourceBundle.getBundle("properties/thirdParty");

    private static final Map<String, ResourceBundle> MESSAGES = new HashMap();


    public static String getMessage(String key, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle message = (ResourceBundle)MESSAGES.get(locale.getLanguage());
        if (message == null) {
            synchronized (MESSAGES) {
                message = (ResourceBundle)MESSAGES.get(locale.getLanguage());
                if (message == null) {
                    message = ResourceBundle.getBundle("i18n/messages", locale);
                    MESSAGES.put(locale.getLanguage(), message);
                }
            }
        }
        if (params != null && params.length > 0) {
            return String.format(message.getString(key), params);
        }
        return message.getString(key);
    }



    public static void flushMessage() { MESSAGES.clear(); }
}
