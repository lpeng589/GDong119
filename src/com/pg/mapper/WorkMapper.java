package com.pg.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.pg.bean.WorkBean;
import com.pg.searchbean.WorkSearchBean;
public interface WorkMapper
{
public Integer addWork(WorkBean bean);
public Integer delWork(@Param("id")Integer id);
public Integer updateWork(WorkBean bean);
public List<WorkSearchBean> list(WorkSearchBean bean);
public Integer listCount(WorkSearchBean bean);
public WorkSearchBean getById(@Param("id")Integer id);

}