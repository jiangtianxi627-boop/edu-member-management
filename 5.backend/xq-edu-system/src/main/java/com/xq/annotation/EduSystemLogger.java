package com.xq.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EduSystemLogger {

    // 关于操作行为的描述
    public String logDescription() default "";

    // 方法返回值的描述
    public String methodReturnType() default "java.lang.String";
}
