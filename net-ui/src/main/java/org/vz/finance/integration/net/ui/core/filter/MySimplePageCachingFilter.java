package org.vz.finance.integration.net.ui.core.filter;

import net.sf.ehcache.constructs.web.PageInfo;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import javax.servlet.http.HttpServletResponse;

public class MySimplePageCachingFilter extends SimplePageCachingFilter {
    @Override
    protected void setContentType(HttpServletResponse response, PageInfo pageInfo) {
        String contentType = pageInfo.getContentType();
        if (contentType != null && contentType.length() > 0) {
            if (contentType.contains("charset=ISO-8859-1")){
                String finalContentType = contentType.replace("charset=ISO-8859-1", "charset=UTF-8");
                response.setContentType(finalContentType);
            }else {
                response.setContentType(contentType);
            }
        }
    }
}
