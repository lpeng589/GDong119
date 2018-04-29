package com.pg.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.pg.bean.FlowLogBean;
import com.pg.searchbean.FlowLogSearchBean;
public interface FlowLogMapper
{
public Integer addFlowLog(FlowLogBean bean);
public Integer delFlowLog(@Param("id")Integer id);
public Integer updateFlowLog(FlowLogBean bean);
public List<FlowLogSearchBean> list(FlowLogSearchBean bean);
public Integer listCount(FlowLogSearchBean bean);
public FlowLogSearchBean getById(@Param("id")Integer id);

}