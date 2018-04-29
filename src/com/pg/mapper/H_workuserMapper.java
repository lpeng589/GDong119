package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.H_workuserBean;
import com.pg.searchbean.F_departmentSearchBean;
import com.pg.searchbean.H_workuserSearchBean;
public interface H_workuserMapper
{
/**
 * 新增
 */
public Integer addH_workuser(H_workuserBean bean);
/**
 * 删除
 */
public Integer delH_workuser(H_workuserBean bean);
/**
 * 批量删除
 */
public Integer delManyH_workuser(H_workuserBean bean);
/**
 * 修改
 */
public Integer updateH_workuser(H_workuserBean bean);
/**
 * 修改不为空的
 */
public Integer updateNeedH_workuser(H_workuserBean bean);
/**
 * 批量修改
 */
public Integer updateManyH_workuser(H_workuserBean bean);
/**
 * 查询所有
 */
public List<H_workuserSearchBean> list(H_workuserSearchBean bean);
/**
 * 查询所有条数
 */
public Integer listCount(H_workuserSearchBean bean);
/**
 * 查询by关键字
 */
public H_workuserSearchBean getById(H_workuserSearchBean bean);
public H_workuserSearchBean getById(@Param("id")Integer id);
public H_workuserSearchBean getByPhone(@Param("phone")String phone);
public Integer UpdateCode(H_workuserBean bean);
public Integer updateStatus(H_workuserBean bean);
public H_workuserSearchBean getByCode(@Param("code")String code);
public  List<H_workuserSearchBean>  getByDepartmentid(@Param("departmentid")Integer departmentid);
public H_workuserSearchBean getMinByDepartmentid(@Param("departmentid")Integer departmentid);
public H_workuserSearchBean getByPhone2(@Param("phone")String phone);
}