package com.pg.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.EmployeeRoleBean;
import com.pg.bean.ModuleBean;
import com.pg.bean.RoleBean;
import com.pg.searchbean.RoleSearchBean;
public interface RoleMapper
{
	//角色列表数量
	String listCount = "listCount";
	//根据id获取角色信息
	String getById = "getById";
	//根据id获取模块信息
	String modulelist = "modulelist";
	//设置角色模块信息
	String moduleSet = "moduleSet";
	//角色的增删改查
	String add = "add" ;
	String delete = "delete";
	String update = "update";
	String list = "list";
	
	/**
	 * 根据id获取角色信息
	 * @param id
	 * @return
	 */
	public RoleBean getById(@Param("id")Integer id);
	/**
	 * 根据id获取模块列表
	 * @param id
	 * @return
	 */
	public List<ModuleBean> moduleList(@Param("id")Integer id);
	/**
	 * 角色列表
	 * @param bean
	 * @return
	 */
	public List<RoleBean> list(RoleSearchBean bean);
	/**
	 * 角色列表数量
	 * @param bean
	 * @return
	 */
	public Integer listCount(RoleSearchBean bean);
	/**
	 * 修改角色信息
	 * @param bean
	 * @return
	 */
	public Integer update(RoleBean bean);
	/**
	 * 删除角色信息
	 * @param asList
	 * @return
	 */
	public Integer delete(@Param("ids")List<String> asList);
	/**
	 * 根据角色名获取角色信息
	 * @param role_name
	 * @return
	 */
	public RoleBean getByName(@Param("name") String role_name);
	/**
	 * 添加角色
	 * @param bean
	 * @return
	 */
	public int add(RoleBean bean);
	/**
	 * 角色权限模块设置
	 * @param role_id
	 * @param asList
	 * @return
	 */
	public int moduleSet(@Param("role_id")String role_id,@Param("module_id")List<String> asList);
	
	/**
	 * 角色权限模块删除
	 * @param role_id
	 * @return
	 */
	public int moduleDel(@Param("role_id")String role_id);
	
	
	
}