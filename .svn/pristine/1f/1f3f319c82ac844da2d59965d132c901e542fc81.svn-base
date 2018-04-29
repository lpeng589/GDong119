package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.F_departmentBean;
import com.pg.searchbean.F_departmentSearchBean;
public interface F_departmentMapper
{
/**
 * 新增
 */
public Integer addF_department(F_departmentBean bean);
/**
 * 删除
 */
public Integer delF_department(F_departmentBean bean);
/**
 * 批量删除
 */
public Integer delManyF_department(F_departmentBean bean);
/**
 * 修改
 */
public Integer updateF_department(F_departmentBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedF_department(F_departmentBean bean);
/**
 * 批量修改
 */
public Integer updateManyF_department(F_departmentBean bean);
/**
 * 查询所有
 */
public List<F_departmentSearchBean> list(F_departmentSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(F_departmentSearchBean bean);
/**
 * 查询by关键字
 */
public F_departmentSearchBean getById(F_departmentSearchBean bean);
public F_departmentSearchBean getById(@Param("id")Integer id);
public F_departmentSearchBean getByParentId(@Param("parentid")Integer parentid);

public F_departmentSearchBean getByName(@Param("name")String name);
public List<F_departmentSearchBean> getByType(@Param("type")Integer type);
public F_departmentSearchBean getTownByName(@Param("name")String name);
}