package com.pg.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
/**<pre>
 * 这个类是用来实现一个对属性设置。
 * 能过{@link #register(AttrInterface)}方法，把属性方法做代理，实现getProperty() setProperty()方法可以从bean中直接获取。
 * 
 * </pre>
 * @author Swan Fong
 * @see AttrInterface
 * @see AttrKey
 */
public class PropertiesCenter implements InvocationHandler{
	AttrInterface attr ;
	
	private static final HashMap<Class<? extends AttrInterface>, HashMap<String, Method>> attrMap = new HashMap<Class<? extends AttrInterface>, HashMap<String, Method>>();
	
	private PropertiesCenter(AttrInterface attr) {
		super();
		this.attr = attr;
	}
	/**<pre>
	 * 注册一个{@link AttrInterface}接口
	 * 注册后，会扫描方法上的所有{@link AttrKey}标签,
	 * {@link #getProperty}的时候会先执行默认的方法，如果返回是null,那么会再扫描 {@link AttrKey}对应的方法获取属性
	 * </pre>
	 * @param attr
	 * @return
	 */
	public static final AttrInterface register(AttrInterface attr) {
		if (!attrMap.containsKey(attr.getClass())) {
			HashMap<String, Method> hm = new HashMap<String, Method>();
			Method[] methods = attr.getClass().getMethods();
			for (Method method : methods) {
				AttrKey key = method.getAnnotation(AttrKey.class);
				if (key != null) {
					String methodName = method.getName();
					if(key.method().equals("") || key.value().equals(""))
					{
						String[] attrMethod = getMethodProperty(methodName);
						if(!key.method().equals(""))
						{
							attrMethod[0] = key.method();
						}
						if(!key.value().equals(""))
						{
							attrMethod[1] = key.value();
						}
						hm.put((attrMethod[0]+attrMethod[1]).toLowerCase(), method);
					}
					hm.put((key.method() + key.value()).toLowerCase(), method);
				}
			}
			attrMap.put(attr.getClass(), hm);
		}
		return (AttrInterface) Proxy.newProxyInstance(attr.getClass().getClassLoader(), attr.getClass().getInterfaces(),new PropertiesCenter(attr)); 
	}

	private static final String getProperty(Object proxy, Object[] args) {
		HashMap<String, Method> map = attrMap.get(proxy.getClass());
		if (map == null)
			return null;
		Method m = map.get((AttrKey.GET + args[0]).toLowerCase());
		if (m != null) {
			try {
				return String.valueOf(m.invoke(proxy));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static final String setProperties(Object proxy, Object args, Object args2) {
		HashMap<String, Method> map = attrMap.get(proxy.getClass());
		Method m = map.get((AttrKey.SET + args).toLowerCase());
		if (m != null) {
			try {
				m.invoke(proxy, args2);
				return (String)args2;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxy instanceof AttrInterface) {
			if (method.getName().toLowerCase().equals("getproperty")) {
				String ret = (String) method.invoke(attr, args);
				if (ret == null)
					return getProperty(attr, args);
				else
					return ret;
			} else if (method.getName().toLowerCase().equals("setproperty")) {
				Object ret = setProperties(attr, args[0], args[1]);
				if (ret == null) {
					return (String) method.invoke(attr, args);
				}else
					return ret;
			}else
			{
				return method.invoke(attr, args);
			}
		}
		return null;
	}
	
	/**
	 * 根据函数名，返回AttrKey所需要的参数
	 * @param methodName
	 * @return
	 */
	private static String[] getMethodProperty(String methodName)
	{
		String name = "";
		String[] getstr = new String[]{"get","is","has"};
		String[] setstr = new String[]{"set"};
		for (String string : setstr) {
			if(methodName.startsWith(string))
			{
				name = methodName.substring(string.length());
				return new String[]{AttrKey.SET,firstLowerCase(name)};
			}
		}
		for (String string : getstr) {
			if(methodName.startsWith(string))
			{
				name = methodName.substring(string.length());
				return new String[]{AttrKey.GET,firstLowerCase(name)};
			}
		}
		return new String[]{AttrKey.GET,methodName};
	}
	/**
	 * 首字母小写
	 * @param name
	 * @return
	 */
	private static String firstLowerCase(String name)
	{
		if(name != null && name.length()>0)
		{
			StringBuilder sb = new StringBuilder(name);
			String firstChar = String.valueOf(name.charAt(0));
			firstChar = firstChar.toLowerCase();
			sb.deleteCharAt(0).insert(0, firstChar);
			return sb.toString();
		}
		return name;
	}
}
