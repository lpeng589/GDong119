package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.ParamvalueBean;
import com.pg.searchbean.ParamvalueSearchBean;
public interface ParamvalueMapper
{
public Integer addParamvalue(ParamvalueBean bean);
public Integer delParamvalue(@Param("id")Integer id);
public Integer updateParamvalue(ParamvalueBean bean);
public List<ParamvalueSearchBean> list(ParamvalueSearchBean bean);
public Integer listCount(ParamvalueSearchBean bean);
public ParamvalueSearchBean getById(@Param("id")Integer id);
public List<ParamvalueSearchBean> getsettliementByids(ParamvalueSearchBean bean);
public ParamvalueSearchBean getByName(@Param("name")String name);

public List<ParamvalueSearchBean> getAllTpye();

}