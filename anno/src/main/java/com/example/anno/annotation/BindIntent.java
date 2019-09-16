package com.example.anno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动读取Intent里面的值
 * 
 * 考虑可以加这个： 
 * Jet新增JMap方法，注入获取Map里面的参数。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindIntent {
    String value() default "";
}
