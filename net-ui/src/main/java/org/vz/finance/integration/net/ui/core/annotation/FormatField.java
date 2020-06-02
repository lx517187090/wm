package org.vz.finance.integration.net.ui.core.annotation;

import java.lang.annotation.*;

/**
 * @Author: lixi
 * @Despriction: 格式化注解
 * @Date: Created in : 2020/2/19 0019
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormatField {

    String filterClass() default "";

    String filterMethod() default "";
}
