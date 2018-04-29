package com.pg.mapper;
import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.pg.bean.CodeBean;
import com.pg.searchbean.CodeSearchBean;
public interface CodeMapper
{
/**
 * 新增
 */
public Integer addCode(CodeBean bean);
/**
 * 删除
 */
public Integer delCode(CodeBean bean);
/**
 * 批量删除
 */
public Integer delManyCode(CodeBean bean);
/**
 * 修改
 */
public Integer updateCode(CodeBean bean);

public Integer updateUrl(CodeBean bean);

/**
 * 修改不为空的
 */
public Integer updateNeedCode(CodeBean bean);
/**
 * 批量修改
 */
public Integer updateManyCode(CodeBean bean);
/**
 * 查询所有
 */
public List<CodeSearchBean> list(CodeSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(CodeSearchBean bean);
/**
 * 查询by关键字
 */
public CodeSearchBean getById(CodeSearchBean bean);

/**
 * 查询by关键字
 */
public Integer getCount(CodeSearchBean bean);


}