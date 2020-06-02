package org.vz.finance.integration.net.ui.core.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CellFormat {

    String value() default "";

    String filterClass() default "";

    String filterMethod() default "";

    String datePatten() default "";

    String firstTitle() default "";

}
