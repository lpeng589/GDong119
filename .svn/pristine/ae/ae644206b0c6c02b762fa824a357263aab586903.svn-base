package com.pg.mapper;

import java.util.List;

import com.pg.bean.LogBean;
import com.pg.searchbean.LogSearchBean;

public interface LogMapper {

public static String add="add";
public static String list="list";
public static String listCount="listCount";


/**
 * 添加日志
 * @param bean
 * @return
 */
public int add(LogBean bean);

/**
 * 查找日志列表
 * @param bean
 * @return
 */
public List<LogBean> list(LogSearchBean bean);

/**
 * 查找日志列表记录数量
 * @param bean
 * @return
 */
public Integer listCount(LogSearchBean bean);


}