package org.vz.finance.integration.net.ui.core.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.Map;

public interface Constants {
    String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
    Map<Class<?>, String> cacheKeyMap = InstanceUtil.newHashMap(new Object[0]);
    String OPERATION_NAME = "OPERATION_NAME";
    String USERLANGUAGE = "userLanguage";
    String WEBTHEME = "webTheme";
    String CURRENT_USER = "CURRENT_USER";
    String PREREQUEST = "PREREQUEST";
    String PREREQUEST_TIME = "PREREQUEST_TIME";
    String LOGIN_URL = "/login.html";
    String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
    String CACHE_NAMESPACE = "VZcLOUD:";
    String ALLUSER_NUMBER = "SYSTEM:VZcLOUD:ALLUSER_NUMBER";
    String TOKEN_KEY = "VZcLOUD:TOKEN_KEY";

    public interface JOBSTATE {
        String INIT_STATS = "I";
        String SUCCESS_STATS = "S";
        String ERROR_STATS = "E";
        String UN_STATS = "N";
    }
}
