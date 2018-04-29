package com.pg.searchbean;

import java.util.List;

import com.pg.bean.EmployeeBean;
import com.pg.bean.F_departmentBean;
import com.pg.bean.H_workuserBean;

/**
 * 职员搜索bean
 * @author Administrator
 *
 */
public class EmployeeSearchBean extends EmployeeBean{
	private Integer departtype;  //部门登记类型
	private Integer departmentid1;  //上级部门id
	private Integer departmentid2;  //上上级部门id
	//角色id
	private Integer role_id;
	private String department;
	private String department1;
	private Integer workuserid2;
	private Integer branchid;
	private F_departmentSearchBean departmentBean;
	private H_workuserSearchBean workuserBean;
	private Integer depylv;//职员/部门登记 0总队 1支队2街道办4巡查员  null--都不是
	private String role_name;

	public Integer getDepylv() {
		return depylv;
	}

	public void setDepylv(Integer depylv) {
		this.depylv = depylv;
	}

	/**
	 * 获取角色id
	 * @return
	 */
	public Integer getRole_id() {
		return role_id;
	}
	
	/**
	 * 设置角色id
	 * @param role_id
	 */
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
	protected List<String> rolelist ;

	public List<String> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<String> rolelist) {
		this.rolelist = rolelist;
	}
	//修改表单负责人
	private String tablename;//子表
	private String tablename2;//子表
	private Integer employid;
	private List<String> parentids ;//主表
	private String parentidname;
	private String idstr;//需要的权限
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTablename2() {
		return tablename2;
	}

	public void setTablename2(String tablename2) {
		this.tablename2 = tablename2;
	}

	public Integer getEmployid() {
		return employid;
	}

	public void setEmployid(Integer employid) {
		this.employid = employid;
	}

	public List<String> getParentids() {
		return parentids;
	}

	public void setParentids(List<String> parentids) {
		this.parentids = parentids;
	}

	public String getParentidname() {
		return parentidname;
	}

	public void setParentidname(String parentidname) {
		this.parentidname = parentidname;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public Integer getWorkuserid2() {
		return workuserid2;
	}

	public void setWorkuserid2(Integer workuserid2) {
		this.workuserid2 = workuserid2;
	}

	public Integer getBranchid() {
		return branchid;
	}

	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment1() {
		return department1;
	}

	public void setDepartment1(String department1) {
		this.department1 = department1;
	}



	public F_departmentSearchBean getDepartmentBean() {
		return departmentBean;
	}

	public void setDepartmentBean(F_departmentSearchBean departmentBean) {
		this.departmentBean = departmentBean;
	}

	public H_workuserSearchBean getWorkuserBean() {
		return workuserBean;
	}

	public void setWorkuserBean(H_workuserSearchBean workuserBean) {
		this.workuserBean = workuserBean;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Integer getDeparttype() {
		return departtype;
	}

	public void setDeparttype(Integer departtype) {
		this.departtype = departtype;
	}

	public Integer getDepartmentid1() {
		return departmentid1;
	}

	public void setDepartmentid1(Integer departmentid1) {
		this.departmentid1 = departmentid1;
	}

	public Integer getDepartmentid2() {
		return departmentid2;
	}

	public void setDepartmentid2(Integer departmentid2) {
		this.departmentid2 = departmentid2;
	}
	
}