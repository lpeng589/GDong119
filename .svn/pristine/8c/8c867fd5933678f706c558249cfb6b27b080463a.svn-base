package com.pg.mapper;
import java.util.List;

import com.pg.bean.EventBean;
import com.pg.searchbean.EventSearchBean;
import com.pg.searchbean.MSearchBean;
public interface EventMapper
{
/**
 * 新增
 */
public Integer addEvent(EventBean bean);
/**
 * 删除
 */
public Integer delEvent(EventBean bean);
/**
 * 批量删除
 */
public Integer delManyEvent(EventBean bean);
/**
 * 修改
 */
public Integer updateEvent(EventBean bean);
public Integer handleEvent(EventBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedEvent(EventBean bean);
/**
 * 批量修改
 */
public Integer updateManyEvent(EventBean bean);
/**
 * 查询所有
 */
public List<EventSearchBean> list(EventSearchBean bean);
public List<EventSearchBean> wxlist(MSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(EventSearchBean bean);
/**
 * 查询by关键字
 */
public EventSearchBean getById(EventSearchBean bean);
public List<EventSearchBean> mlist(MSearchBean bean);
}