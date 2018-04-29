package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.CodedetailBean;
import com.pg.searchbean.CodeSearchBean;
import com.pg.searchbean.CodedetailSearchBean;
public interface CodedetailMapper
{
/**
 * 新增
 */
public Integer addCodedetail(CodedetailBean bean);
/**
 * 删除
 */
public Integer delCodedetail(CodedetailBean bean);
/**
 * 批量删除
 */
public Integer delManyCodedetail(CodedetailBean bean);
/**
 * 修改
 */
public Integer updateCodedetail(CodedetailBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedCodedetail(CodedetailBean bean);
/**
 * 批量修改
 */
public Integer updateManyCodedetail(CodedetailBean bean);
/**
 * 查询所有
 */
public List<CodedetailSearchBean> list(CodedetailSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(CodedetailSearchBean bean);
/**
 * 查询by关键字
 */
public CodedetailSearchBean getById(CodedetailSearchBean bean);


}