package com.pg.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用来指明mybatis使用哪个环境（多数据库时有用）
 * @author swan
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnvSource {
	public static final String DEFAULT = "_default";

	/**
	 * @return 数据连接池名
	 */
	String value();
}