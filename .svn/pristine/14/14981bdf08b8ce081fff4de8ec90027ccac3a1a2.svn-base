package com.pg.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.FlowNodeBean;
import com.pg.searchbean.FlowNodeSearchBean;
public interface FlowNodeMapper
{
public Integer addFlowNode(FlowNodeBean bean);
public Integer delFlowNode(FlowNodeSearchBean bean);
public Integer updateFlowNode(FlowNodeBean bean);
public List<FlowNodeSearchBean> list(FlowNodeSearchBean bean);
public Integer listCount(FlowNodeSearchBean bean);

public FlowNodeSearchBean getById(@Param("id")Integer id);
public FlowNodeSearchBean getByTableNameAndStatus(@Param("statusnow")Integer statusnow,@Param("tablename")String tablename);
public FlowNodeSearchBean getByFlowidAndStatus(FlowNodeSearchBean bean);
public List<HashMap>  getCounts(@Param("tablename")String tablename,@Param("id")Integer id);
public Integer getCountsByType(@Param("tablename")String tablename,@Param("id")Integer id,@Param("type")Integer type);
public Integer updateTableStatus(FlowNodeSearchBean bean);
public HashMap getAllByTableName(@Param("id")Integer id,@Param("tablename")String tablename);
public Integer getStatusCount(@Param("statusnow")Integer statusnow,@Param("tablename")String tablename);
public HashMap getStatusInfo(@Param("statusnow")Integer statusnow,@Param("tablename")String tablename);

}