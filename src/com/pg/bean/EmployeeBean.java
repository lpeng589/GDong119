package com.pg.bean;

import java.sql.Timestamp;



/**
 * 职员bean
 * @author dzp
 *
 */
public class EmployeeBean extends BaseBean{
	private Integer id;
	private String loginname;
	private String username;
	private String password;
	private Integer status;
	private Timestamp createtime;
	private Integer card_level;
	private Integer card_num;
	private String roleids;
	private String phone;
	private Integer employee_id;
	private String position;
	private String email;
	private String faxnumber;
	private Integer workuserid;
	private Integer departmentid;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFaxnumber() {
		return faxnumber;
	}
	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the card_level
	 */
	public Integer getCard_level() {
		return card_level;
	}
	/**
	 * @param card_level the card_level to set
	 */
	public void setCard_level(Integer card_level) {
		this.card_level = card_level;
	}
	/**
	 * @return the card_num
	 */
	public Integer getCard_num() {
		return card_num;
	}
	/**
	 * @param card_num the card_num to set
	 */
	public void setCard_num(Integer card_num) {
		this.card_num = card_num;
	}
	public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getWorkuserid() {
		return workuserid;
	}
	public void setWorkuserid(Integer workuserid) {
		this.workuserid = workuserid;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
}