package com.qijianguo.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 自定义注解：对某些请求的响应进行忽略
 * 可标注到类或方法上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IgnoreResponseAdvice {
}
