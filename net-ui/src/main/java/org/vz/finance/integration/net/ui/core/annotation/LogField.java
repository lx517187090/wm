package org.vz.finance.integration.net.ui.core.annotation;

import java.lang.annotation.*;

/**
 * @Author: lixi
 * @Despriction: 字段注解
 * @Date: Created in : 2020/2/19 0019
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogField {

    boolean key() default false;

    String columnName() default "";

    String content() default "";
}
