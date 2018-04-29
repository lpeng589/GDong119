package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.RoleModuleBean;
public interface RoleModuleMapper
{
public Integer addRoleModule(RoleModuleBean bean);
public Integer updateRoleModule(RoleModuleBean bean);
public List<RoleModuleBean> list(RoleModuleBean bean);
public Integer listCount(RoleModuleBean bean);
/**
 * 删除角色信息
 * @param asList
 * @return
 */
public Integer delete(@Param("ids")List<String> asList);
}