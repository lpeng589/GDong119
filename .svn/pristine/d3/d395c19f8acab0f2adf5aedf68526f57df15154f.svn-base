package com.pg.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.pg.bean.FlowBean;
import com.pg.searchbean.FlowSearchBean;
public interface FlowMapper
{
public Integer addFlow(FlowBean bean);
public Integer delFlow(@Param("id")Integer id);
public Integer updateFlow(FlowBean bean);
public List<FlowSearchBean> list(FlowSearchBean bean);
public Integer listCount(FlowSearchBean bean);
public FlowSearchBean getById(@Param("id")Integer id);
public FlowSearchBean getByFlowKey(@Param("flowkey")String flowkey);
}