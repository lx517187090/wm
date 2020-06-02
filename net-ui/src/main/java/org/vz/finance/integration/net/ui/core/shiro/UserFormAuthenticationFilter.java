package org.vz.finance.integration.net.ui.core.shiro;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            if(isAjax(request)){
                Map<String, Object> map = new HashMap<>();
                map.put("httpCode", HttpCode.UNAUTHORIZED.value());
                map.put("msg","SESSION访问超时,请刷新当前页面!");
                String s = JSON.toJSONString(map);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(s);
            }else{
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

    private static boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
