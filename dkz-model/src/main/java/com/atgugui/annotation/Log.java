package com.atgugui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.atgugui.enums.annotation.BusinessType;
import com.atgugui.enums.annotation.OperatorType;

/**自定义操作日志记录注解
 * @author dkzadmin
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /** 模块 */
    String title() default "";

    /** 功能 */
    BusinessType businessType() default BusinessType.OTHER;

    /** 操作人类别 */
    OperatorType operatorType() default OperatorType.MANAGE;

    /** 是否保存请求的参数 */
    boolean isSaveRequestData() default true;

}
