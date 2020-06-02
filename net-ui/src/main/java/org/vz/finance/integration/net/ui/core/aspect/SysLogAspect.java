package org.vz.finance.integration.net.ui.core.aspect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.SyslogAppender;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.vz.finance.integration.manage.sys.service.SysLogService;
import org.vz.finance.integration.manage.sys.util.SQLFilter;
import org.vz.finance.integration.model.SysLog;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.model.util.ToolForID;
import org.vz.finance.integration.net.ui.core.annotation.*;
import org.vz.finance.integration.net.ui.core.constant.SysConstants;
import org.vz.finance.integration.net.ui.core.utils.HttpContextUtils;
import org.vz.finance.integration.net.ui.core.utils.IPUtils;
import org.vz.finance.integration.net.ui.core.utils.PlaceholderResolver;
import org.vz.finance.integration.net.ui.modules.dao.ISysEntityDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author jaden
 * @description 系统日志切面类
 * @since 2018/5/31
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger LOGGER = LogManager.getLogger(SyslogAppender.class);

    @Autowired
    private SysLogService sysLogService;

    @Resource
    private ISysEntityDao sysEntityDao;

    @Pointcut("@annotation(org.vz.finance.integration.net.ui.core.annotation.ManagerLogAp)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //long beginTime = System.currentTimeMillis();
        try {
            saveManagerLog(point);
        } catch (Exception e) {
            LOGGER.error("日志记录错误 {} ", e.getMessage());
        }

        //long time = System.currentTimeMillis() - beginTime;
        //saveSysLog(point, time);
        return point.proceed();
    }


    private void saveManagerLog(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ManagerLogAp managerLogap = method.getAnnotation(ManagerLogAp.class);
        if (managerLogap == null) {
            return;
        }
        Object[] args = point.getArgs();
        String param = "";
        if (managerLogap.usePattern()) {
            param = patternManagerLog(managerLogap, args[0]);
        } else {
            if (SysConstants.LOG_TYPE_UPDATE == managerLogap.type()) {
                param = compareUpdate(managerLogap, args[0]);
            } else if (SysConstants.LOG_TYPE_DOWNLOAD == managerLogap.type()) {
                param = downloadLog(managerLogap, args);
            } else if (SysConstants.LOG_TYPE_EXPORT == managerLogap.type()) {
                param = exportLog(managerLogap, args[0]);
            }
        }
        SysLog sysLog = new SysLog();
        sysLog.setId(ToolForID.getSysLogID());
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysLog.setUsername(sysUser.getUsername());
        sysLog.setParams(param);
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IPUtils.getIpAddr(request));
        sysLog.setOperateType(String.valueOf(managerLogap.type()));
        sysLog.setCreateTime(DateUtil.date());
        sysLogService.insert(sysLog);
    }

    /**
     * 下载文件日志
     */
    private String downloadLog(ManagerLogAp managerLogap, Object[] args) {
        List<String> params = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof String) {
                params.add((String) arg);
            }
        }
        if (CollectionUtils.isEmpty(params)) {
            return "";
        }
        PlaceholderResolver resolver = PlaceholderResolver.getDefaultResolver();
        String resolve = resolver.resolve(managerLogap.value(), params.toArray());
        LOGGER.info(resolve);
        return resolve;
    }

    private String exportLog(ManagerLogAp managerLogap, Object arg) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = arg.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            QueryParam queryParam = field.getAnnotation(QueryParam.class);
            if (queryParam == null) {
                continue;
            }
            makeAccessible(field);
            String value = (String) field.get(arg);
            if (StringUtils.isNotBlank(value)) {
                sb.append(queryParam.name()).append("[")
                        .append(value).append("]");
            }
        }
        sb.insert(0, managerLogap.value());
        return sb.toString();
    }

    /**
     * 更新操作日志
     */
    private String compareUpdate(ManagerLogAp managerLogap, Object arg) throws Exception {
        Class<?> clazz = arg.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        String whereParam = "";
        StringBuilder sb = new StringBuilder();
        for (Field field : declaredFields) {
            LogField logAnnotation = field.getAnnotation(LogField.class);
            if (logAnnotation == null) {
                continue;
            }
            makeAccessible(field);
            Object o = field.get(arg);
            boolean key = logAnnotation.key();
            if (key) {
                sb.append(logAnnotation.content()).append("[").append(o).append("]");
                whereParam = logAnnotation.columnName() + " = " + "'" + o + "'";
                break;
            }
        }
        Map<String, String> oldValues = getOldValue(managerLogap.tableName(), whereParam);
        sb.append("修改");
        for (Field field : declaredFields) {
            LogField logAnnotation = field.getAnnotation(LogField.class);
            if (logAnnotation == null) {
                continue;
            }
            if (!oldValues.containsKey(logAnnotation.columnName())) {
                continue;
            }
            String oldValue = oldValues.get(logAnnotation.columnName());
            makeAccessible(field);
            String newValue = (String) field.get(arg);
            if (StringUtils.isBlank(newValue) || "null" .equals(newValue)) {
                continue;
            }
            FormatField formatField = field.getAnnotation(FormatField.class);
            if (formatField != null) {
                oldValue = formatField(formatField, oldValue);
                newValue = formatField(formatField, newValue);
            }
            if (!oldValue.equals(newValue)) {
                // LOGGER.info("{} 从 {} 变为 {}", logAnnotation.content(), oldValue, newValue);
                sb.append(String.format("%s 从 [%s] 变为 [%s]", logAnnotation.content(), oldValue, newValue));
            }
        }
        LOGGER.info(sb.toString());
        return sb.toString();
    }

    /**
     * 获取原始数据
     */
    private Map<String, String> getOldValue(String tableName, String exa) {
        tableName = SQLFilter.sqlInject(tableName);
        return sysEntityDao.findByFiled(tableName, exa);
    }

    /**
     * 格式化数据
     */
    private static String formatField(FormatField formatField, Object value) throws Exception {
        String filterMethod = formatField.filterMethod();
        String filterClass = formatField.filterClass();
        if (StringUtils.isNotEmpty(filterClass) && StringUtils.isNotEmpty(filterMethod)) {
            Class<?> clazz = Class.forName(SysConstants.CELL_FORMAT_FILTER);
            Method method = clazz.getDeclaredMethod(filterMethod, Object.class);
            value = method.invoke(clazz.newInstance(), value);
        }
        return (String) value;
    }

    /**
     * 改变private/protected的成员变量为public，尽量不调用实际改动的语句
     */
    private static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
                .isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    /**
     * 模板解析日志
     */
    private String patternManagerLog(ManagerLogAp managerLogap, Object arg) {
        String value = managerLogap.value();
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        }
        PlaceholderResolver resolver = PlaceholderResolver.getDefaultResolver();
        if (arg instanceof String) {
            return resolver.resolve(value, (String)arg);
        }
        if (arg instanceof String[]) {
            return resolver.resolve(value, Arrays.toString((String[]) arg));
        }
        Map<String, Object> map = BeanUtil.beanToMap(arg);
        return resolver.resolveByMap(value, map);
    }
}
