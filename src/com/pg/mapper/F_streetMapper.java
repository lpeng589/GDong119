package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.F_streetBean;
import com.pg.searchbean.F_streetSearchBean;
public interface F_streetMapper
{
/**
 * 新增
 */
public Integer addF_street(F_streetBean bean);
/**
 * 删除
 */
public Integer delF_street(F_streetBean bean);
/**
 * 批量删除
 */
public Integer delManyF_street(F_streetBean bean);
/**
 * 修改
 */
public Integer updateF_street(F_streetBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedF_street(F_streetBean bean);
/**
 * 批量修改
 */
public Integer updateManyF_street(F_streetBean bean);
/**
 * 查询所有
 */
public List<F_streetSearchBean> list(F_streetSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(F_streetSearchBean bean);
/**
 * 查询by关键字
 */
public F_streetSearchBean getById(F_streetSearchBean bean);
public F_streetSearchBean getByName(@Param("name")String name);
}