package com.baizhi.fifth.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//代表在运行时起作用
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToExcel {
    String value();
}
