package com.pg.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提交类型和模块识别
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpAnno {
	/**
	 * 提交方式为ajax
	 */
	public static final String AJAX = "AJAX";
	/**
	 * 提交方式为普通跳转
	 */
	public static final String VIEW = "VIEW";
	/**
	 * 要记录日志
	 * @see #log()
	 * @see #UNLOG 
	 */
	public static final String WILLLOG = "true";
	/**
	 * 不记录日志
	 * @see #log()
	 * @see #UNLOG
	 */
	public static final String UNLOG = "false";
	/**
	 * 默认方式为普通跳转，缺省值是 {@value #VIEW}
	 * @return
	 * @see #VIEW
	 * @see #AJAX
	 */
	String value() default AJAX;
	/**
	 * 默认模块为空
	 * @return
	 */
	String module() default "";
	/**
	 * 是否要写进日志,缺值是 {@link #WILLLOG}
	 * @return
	 * @see #WILLLOG
	 * @see #UNLOG
	 */
	String log() default WILLLOG;
}
