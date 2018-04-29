package com.pg.bean;
import java.sql.Timestamp;
public class RoleBean extends BaseBean{
	private Integer id;
	/**
	 * role的名称
	 */
	private String role_name;
	/**
	 * Role的描述
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Timestamp create_time;
	/**
	 * 创建人的名称
	 */
	private Integer employee_id;
	/**
	 * 状态，分为1启用和 0禁用
	 */
	private Integer status;
	/**
	*设置
	*/
	public void setId(Integer id){
		this.id = id;
	}
	/**
	*获取
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置role的名称
	*/
	public void setRole_name(String role_name){
		this.role_name = role_name;
	}
	/**
	*获取role的名称
	*/
	public String getRole_name(){
		return role_name;
	}
	/**
	*设置Role的描述
	*/
	public void setDescription(String description){
		this.description = description;
	}
	/**
	*获取Role的描述
	*/
	public String getDescription(){
		return description;
	}
	/**
	*设置创建时间
	*/
	public void setCreate_time(Timestamp create_time){
		this.create_time = create_time;
	}
	/**
	*获取创建时间
	*/
	public Timestamp getCreate_time(){
		return create_time;
	}
	/**
	*设置创建人的名称
	*/
	public void setEmployee_id(Integer employee_id){
		this.employee_id = employee_id;
	}
	/**
	*获取创建人的名称
	*/
	public Integer getEmployee_id(){
		return employee_id;
	}
	/**
	*设置状态，分为1启用和 0禁用
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	*获取状态，分为1启用和 0禁用
	*/
	public Integer getStatus(){
		return status;
	}
}