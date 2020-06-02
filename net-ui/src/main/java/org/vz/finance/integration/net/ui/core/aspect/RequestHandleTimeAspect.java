package org.vz.finance.integration.net.ui.core.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhanlx
 * @since 2018/6/4
 * @description 请求处理时间切面类
 */
@Aspect
@Component
public class RequestHandleTimeAspect {

    private static final Logger LOGGER = LogManager.getLogger(RequestHandleTimeAspect.class);

    @Pointcut("execution(public * org.vz.finance.integration.net.ui.modules.controller.*.*.*(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        MethodSignature signature = (MethodSignature) point.getSignature();
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        LOGGER.info("处理{}请求,方法为{}.{},消耗时间为{}ms", methodName, className, methodName, time);
        return result;
    }

}
