package org.vz.finance.integration.net.ui.core.annotation;

import java.lang.annotation.*;

/**
 * @Author: lixi
 * @Despriction: 查询条件注解
 * @Date: Created in : 2020/2/29 0029
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryParam {

    String name() default "";
}
