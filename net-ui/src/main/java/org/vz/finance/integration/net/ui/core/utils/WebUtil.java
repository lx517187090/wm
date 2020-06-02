package org.vz.finance.integration.net.ui.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;

public final class WebUtil {

    private static Logger logger = LogManager.getLogger();

    private WebUtil() {}

    public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        return cookie == null ? defaultValue : cookie.getValue();
    }

    public static final void saveCurrentUser(Object user) {
        setSession("CURRENT_USER", user);
    }

    public static final Object getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            try {
                Session session = currentUser.getSession();
                if (null != session) {
                    return session.getAttribute("CURRENT_USER");
                }
            } catch (InvalidSessionException var2) {
                logger.info(var2);
            }
        }

        return null;
    }

    public static final String getUserId() {
        Object userObj = getCurrentUser();
        Class clazz = userObj.getClass();

        try {
            Field f = clazz.getDeclaredField("userId");
            f.setAccessible(true);
            Object val = f.get(userObj);
            return null == val ? null : String.valueOf(val);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            logger.info(e.getMessage(), e);
        }

        return null;
    }

    public static final void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }

    }

    public static final Session getSession() {
        Subject currentUser = SecurityUtils.getSubject();
        return null != currentUser ? currentUser.getSession() : null;
    }

    public static final Object getSessionAttribute(String key) {
        Session session = getSession();
        return null == session ? null : session.getAttribute(key);
    }

    public static final void removeCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute("CURRENT_USER");
    }

    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, (String)null);
    }

    public static final String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("127.0.0.1".equals(ip)) {
            InetAddress inet = null;

            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException var4) {
                var4.printStackTrace();
            }

            ip = inet.getHostAddress();
        }

        if (ip != null && ip.length() > 15 && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }

        return ip;
    }
}