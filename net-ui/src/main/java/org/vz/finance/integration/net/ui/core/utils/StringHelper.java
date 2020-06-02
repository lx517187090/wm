package org.vz.finance.integration.net.ui.core.utils;

import org.springframework.util.StringUtils;

/**
 * @author yezhaoxing
 * @since 2018/09/21
 */
public class StringHelper {

    /**
     * @author yezhaoxing
     * @since 2018/6/5 13:39
     * @description 将String封装成数据库模糊查询
     */
    public static String wrapperKeywordForSearch(String keyWord) {
        StringBuilder sb = new StringBuilder(100);
        if (StringUtils.hasText(keyWord)) {
            String[] arr = keyWord.split("");
            sb.append("%");
            for (String s : arr) {
                sb.append(s).append("%");
            }
        }
        return sb.toString();
    }
}
