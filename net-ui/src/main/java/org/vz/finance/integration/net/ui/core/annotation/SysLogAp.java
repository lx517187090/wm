package org.vz.finance.integration.net.ui.core.annotation;

import java.lang.annotation.*;

/**
 * @author jaden
 * @since 2018/5/31
 * @description 日志配置注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAp {

    String value() default "";
}
