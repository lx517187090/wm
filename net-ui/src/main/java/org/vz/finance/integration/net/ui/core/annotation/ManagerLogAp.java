package org.vz.finance.integration.net.ui.core.annotation;

import java.lang.annotation.*;

/**
 * @Author: lixi
 * @Despriction: 操作日志注解
 * @Date: Created in : 2020/2/29 0029
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ManagerLogAp {

    String value() default "";

    short type() default 0;//0 其他 1.新增 2 更新 3 删除 4 下载 5 导出

    String tableName() default "";

    boolean usePattern() default false; //是否使用模板
}
