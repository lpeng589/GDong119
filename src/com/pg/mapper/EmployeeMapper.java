package com.pg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pg.bean.EmployeeBean;
import com.pg.bean.ModuleBean;
import com.pg.searchbean.EmployeeSearchBean;

/**
 * 职员处理
 * @author Administrator
 *
 */
public interface EmployeeMapper {
	//基本增删改查
	public static String list="list";
	public static String listCount="listCount";
	public static String add="add";
	public static String delete="delete";
	public static String getById="getById";
	public static String update="update";
	//特殊
	public static String getByName="getByName";
	public static String login="login";
	public static String moduleList="moduleList";
	public static String moduleRemove="moduleRemove";
	public static String moduleSet="moduleSet";
	
	/**
	 * 职员列表
	 * @param bean
	 * @return
	 */
	public List<EmployeeSearchBean> list(EmployeeSearchBean bean);
	
	/**
	 * 职员数量
	 * @param bean
	 * @return
	 */
	public int listCount(EmployeeSearchBean bean);
	
	/**
	 * 添加职员
	 * @param bean
	 * @return
	 */
	public int add(EmployeeBean bean);
	
	/**
	 * 删除职员
	 * @param ids
	 * @return
	 */
	public int delete(EmployeeBean bean);
	
	/**
	 * 通过id获取职员
	 * @param id
	 * @return
	 */
	public EmployeeSearchBean getById(@Param("id")String id);
	
	/**
	 * 修改职员
	 * @param bean
	 * @return
	 */
	public int update(EmployeeBean bean);
	
	/**
	 * 通过登录账号获取职员
	 * @param loginname
	 * @return
	 */
	public EmployeeBean getByName(@Param("loginname")String loginname);
	
	/**
	 * 职员登录
	 * @param loginname
	 * @param password
	 * @return
	 */
	public EmployeeSearchBean login(@Param("loginname")String loginname);
	
	/**
	 * 获取用户模块列表，按群组进行排序
	 * @param id
	 * @return
	 */
	public List<ModuleBean> moduleList(@Param("id") String id);
	
	/**
	 * 移除某个用户的权限
	 * @param userId
	 * @return
	 */
	public Integer moduleRemove(@Param("employeeid") String employeeid);
	
	/**
	 * 设置用户权限
	 * @param employeeid
	 * @param moduleid
	 * @return
	 */
	public Integer moduleSet(@Param("employeeid")String employeeid,@Param("moduleid")List<String> moduleid);
	
	/**
	 * 根据角色返回用户数
	 * @param roleids
	 * @return
	 */
	 
	public Integer getByRoleId(@Param("roleids")String roleids);
	
	
	public List<EmployeeSearchBean> getEmployeeidByRoleid(@Param("roleids")String roleids);
	
	public Integer updateSevrice(EmployeeSearchBean bean);
	public Integer updateSevriceChild(EmployeeSearchBean bean);
	
}
