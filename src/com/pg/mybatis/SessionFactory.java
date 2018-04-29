package com.pg.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**<pre>
 * 处理mybatis连数据库功能 实例使用了 {@link #getMapper(Class)}
 * {@link #getMapper(Class, String)}后必须调用{@link #close()}关闭连接
 * {@link #getMapper(Class)} 获取mybatis的mapper对象
 * {@link #getMapper(Class, String)} 获取mybatis的mapper对象,env为多数据库所用环境
 * {@link #close()} 提交SQL查询并关闭数据库链接 {@link #close(boolean)}
 * 关闭数据库链接，如果参数为true则先回滚再关闭，否提交后关闭
 * 
 * {@link #getSession(String)} 获取{@link SqlSession},参数为环境
 * </pre>
 * @author swan
 * 
 */
public class SessionFactory {

	private static String resource = "mybatis/mybatis.xml";

	private static HashMap<String, SqlSessionFactory> factoryMap = new HashMap<String, SqlSessionFactory>();
	// 开启线程监听资源文件
	static {
		try {
			Path p = Resources.getResourceAsFile(resource).getParentFile().toPath();
			final WatchService watch = p.getFileSystem().newWatchService();
			p.register(watch, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							WatchKey key = watch.take();
							List<WatchEvent<?>> pollEvents = key.pollEvents();
							for (int i = 0; i < pollEvents.size(); i++) {
								WatchEvent<?> event = pollEvents.get(i);
								Path p = (Path) event.context();
								if (p.toString().equals(resource)) {
									factoryMap.clear();
									System.out.println("数据库连接重置");
								}
							}
							if (!key.reset())
								break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	Map<String, SqlSession> map = new HashMap<>();

	/**
	 * <pre>
	 * 获取{@link SqlSession}<br/>
	 * 注意此方法获取session执行sql后，要手动{@link SqlSession#commit()},
	 * {@link SqlSession#rollback()},{@link SqlSession#close()}
	 * </pre>
	 * 
	 * @param environment
	 *            环境标识字符串
	 * @return {@link SqlSession}实例
	 */
	private static SqlSession getSession(String environment) {
		if (factoryMap.get(environment) == null) {
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader(resource);
				if (environment.equals(EnvSource.DEFAULT))
					factoryMap.put(environment, new SqlSessionFactoryBuilder().build(reader));
				else
					factoryMap.put(environment, new SqlSessionFactoryBuilder().build(reader, environment));
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return factoryMap.get(environment).openSession();
	}

	/**
	 * 获取Mapper实例
	 * 
	 * @param c
	 *            Mapper类
	 * @param env
	 *            环境，如果为null那么使用缺省值连接
	 * @return Mapper实例
	 */
	public <T> T getMapper(Class<T> c, final String env) {
		String environment = env;
		if (environment == null) {// 如果没指定env,则从类声明中取出env
			EnvSource ds = c.getAnnotation(EnvSource.class);
			if (ds != null)
				environment = ds.value();
		}
		// 如果取不到ENV，则使用默认连接
		if (environment == null)
			environment = EnvSource.DEFAULT;

		SqlSession session = map.get(environment);
		if (session == null) {
			session = SessionFactory.getSession(environment);
			map.put(environment, session);
		}
		return session.getMapper(c);
	}

	public <T> T getMapper(Class<T> c) {
		return getMapper(c, null);
	}

	/**
	 * 关闭所有session
	 * 
	 * @param commit
	 *            是否执行提交命令。为false执行rollback。
	 */
	public void close(boolean commit) {
		for (SqlSession session : map.values()) {
			if (commit)
				session.commit();
			else
				session.rollback();
			session.close();
		}
		map.clear();
	}

	/**
	 * 提交所有连接，并关闭所有连接。
	 */
	public void close() {
		close(true);
	}
	/**<pre>
	 * 运行SQL任务。
	 * 如果不发生异常会自动提交SQL语句，否则回滚
	 * </pre>
	 * @param task
	 */
	@SuppressWarnings("unchecked")
	public <T> T runTask(SqlTask task,Class<T> c) {
		T t = null;
		try {
			Object o = task.run(this);
			if(o == null || c.isInstance(o))
			{
				t = (T) o;
			}else
			{
				throw new IllegalArgumentException("返回的参数异常");
			}
			close();
		} catch (Exception ex) {
			ex.printStackTrace();
			close(false);
		}
		return t;
	}
	
	/**
	 * 运SQL任务
	 * @param object 运行方法所属的实例
	 * @param tag SQL标签，
	 * @param c 运行任务要返回实例所属的类
	 * @param objs 运行方法所需的参数
	 * @return 任务运行后的对象
	 * 
	 * @see TaskAnnotation
	 */
	@SuppressWarnings("unchecked")
	public <T> T runTask(Object object, String tag, Class<T> c,Object[] objs) {
		T t = null;
		for (Method method : object.getClass().getDeclaredMethods()) {
			if (method.getAnnotation(TaskAnnotation.class) != null && method.getAnnotation(TaskAnnotation.class).value().equals(tag)) {
				try {
					Object[] tmp = new Object[objs.length+1];
					tmp[0] = this;
					System.arraycopy(objs, 0, tmp, 1, objs.length);
					Object o = method.invoke(object, tmp);
					if (o == null || c.isInstance(o)) {
						t = (T) o;
					} else {
						throw new IllegalArgumentException("返回的参数异常");
					}
					close();
				} catch (Exception ex) {
					ex.printStackTrace();
					close(false);
				}
				return t;
			}
		}
		return null;
	}
}