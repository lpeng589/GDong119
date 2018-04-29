package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.P_eventlogBean;
import com.pg.searchbean.P_eventlogSearchBean;
public interface P_eventlogMapper
{
/**
 * 新增
 */
public Integer addP_eventlog(P_eventlogBean bean);
/**
 * 删除
 */
public Integer delP_eventlog(P_eventlogBean bean);
/**
 * 批量删除
 */
public Integer delManyP_eventlog(P_eventlogBean bean);
/**
 * 修改
 */
public Integer updateP_eventlog(P_eventlogBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedP_eventlog(P_eventlogBean bean);
/**
 * 批量修改
 */
public Integer updateManyP_eventlog(P_eventlogBean bean);
/**
 * 查询所有
 */
public List<P_eventlogSearchBean> list(P_eventlogSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(P_eventlogSearchBean bean);
/**
 * 查询by关键字
 */
public P_eventlogSearchBean getById(P_eventlogSearchBean bean);
/**
 * 根据workuserid查询
 */
public List<P_eventlogSearchBean> getByWorkuserid(P_eventlogSearchBean bean);
/**
 * getOperate
 */
public List<P_eventlogSearchBean> getOperate();

public List<P_eventlogSearchBean> detail(P_eventlogSearchBean bean);  //根据eventid查看案件的详情
public Integer CountStatus(@Param("status")Integer status);
public Integer CountNum(@Param("time")String time);
public Integer CountWorkuserid(@Param("workuserid")Integer workuserid,@Param("time")String time);
public Integer CountDepartment1(@Param("department1")Integer department1,@Param("time")String time);
public Integer CountDepartment2(@Param("department2")Integer department2,@Param("time")String time);
public List<P_eventlogSearchBean> listsendMessage(P_eventlogSearchBean bean);
public List<P_eventlogSearchBean> getfenbolist(@Param("next_id")Integer next_id);
public List<P_eventlogSearchBean> getfenbolistThread();
}