package com.pg.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 加上此注解的方解，第一个参数必须是{@link SessionFactory},否则会出异常
 * @author swan
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TaskAnnotation {
	String value();
}
