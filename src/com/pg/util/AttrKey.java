package com.pg.util;

import java.lang.annotation.*;
/**<pre>
 * 属性标签,用来给所有需要设置属性类加上标签。
 * 联合 {@link PropertiesCenter}一起使用
 * </pre>
 * @author Swan Fong
 * @see PropertiesCenter
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AttrKey {
	/**
	 * method方法的实例,代表get方法
	 * @see method()
	 */
	public static final String GET = "get";
	/**
	 * method方法的实例，代表set方法
	 * @see method()
	 */
	public static final String SET = "set";
	/**
	 * 属性值
	 * @return
	 */
	String value() default "";
	/**
	 * 方法
	 * @return 属性方法
	 * @see GET
	 * @see SET
	 */
	String method() default "";
}