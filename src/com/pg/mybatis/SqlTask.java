package com.pg.mybatis;

/**
 * SQL任务
 * SessionFactory可以使用
 * @author swan
 * @see SessionFactory#runTask(SqlTask, Class)
 */
public interface SqlTask {

	public Object run(SessionFactory factory);
	
}
