package org.vz.finance.integration.net.ui.modules.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.caucho.hessian.client.HessianRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class ExceptionHandlerController extends BaseController {

    @ExceptionHandler({Exception.class})
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
        throws Exception {
        logger.error("OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :", ex);
        ModelMap modelMap = new ModelMap();

            modelMap.put("httpCode", HttpCode.INTERNAL_SERVER_ERROR.value());
            String msg = StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
            modelMap.put("msg", msg.length() > 100 ? "系统走神了,请稍候再试." : msg);

        response.setContentType("application/json;charset=UTF-8");
        modelMap.put("timestamp", System.currentTimeMillis());
        this.logger.info(JSON.toJSON(modelMap));
        byte[] bytes =
            JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }
}
