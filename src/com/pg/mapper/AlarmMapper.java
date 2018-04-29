package com.pg.mapper;
import java.util.List;

import com.pg.bean.AlarmBean;
import com.pg.searchbean.AlarmSearchBean;
import com.pg.searchbean.MSearchBean;
public interface AlarmMapper
{
/**
 * 新增
 */
public Integer addAlarm(AlarmBean bean);
/**
 * 删除
 */
public Integer delAlarm(AlarmBean bean);
/**
 * 批量删除
 */
public Integer delManyAlarm(AlarmBean bean);
/**
 * 修改
 */
public Integer updateAlarm(AlarmBean bean);
public Integer updateStatus(AlarmSearchBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedAlarm(AlarmBean bean);
/**
 * 批量修改
 */
public Integer updateManyAlarm(AlarmBean bean);
/**
 * 查询所有
 */
public List<AlarmSearchBean> list(AlarmSearchBean bean);
public List<AlarmSearchBean> wxlist(MSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(AlarmSearchBean bean);
/**
 * 查询by关键字
 */
public AlarmSearchBean getById(AlarmSearchBean bean);

/**
 * 获取用户的最新报警信息
 */
public AlarmSearchBean getwxalarm(AlarmSearchBean bean);
}