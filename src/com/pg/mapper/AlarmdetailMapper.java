package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.AlarmBean;
import com.pg.bean.AlarmdetailBean;
import com.pg.searchbean.AlarmdetailSearchBean;
import com.pg.searchbean.MSearchBean;
public interface AlarmdetailMapper
{
/**
 * 新增
 */
public Integer addAlarmdetail(AlarmdetailBean bean);
/**
 * 删除
 */
public Integer delAlarmdetail(AlarmdetailBean bean);
/**
 * 批量删除
 */
public Integer delManyAlarmdetail(AlarmdetailBean bean);
/**
 * 修改
 */
public Integer updateAlarmdetail(AlarmdetailBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedAlarmdetail(AlarmdetailBean bean);
/**
 * 批量修改
 */
public Integer updateManyAlarmdetail(AlarmdetailBean bean);
/**
 * 查询所有
 */
public List<AlarmdetailSearchBean> list(AlarmdetailSearchBean bean);
public List<AlarmdetailSearchBean> wxlist(MSearchBean bean);
public List<AlarmdetailSearchBean> listNew(AlarmdetailSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(AlarmdetailSearchBean bean);
/**
 * 查询by关键字
 */
public AlarmdetailSearchBean getById(AlarmdetailSearchBean bean);
/**
 * 将未读更改为已读
 * @param alarmid
 * @return
 */
public Integer updateRead(@Param("alarmid")Integer alarmid);
/**
 * 查询未读消息
 */
public List<AlarmBean> getNotRead();
}