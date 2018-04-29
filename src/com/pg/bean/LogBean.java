package com.pg.bean;

import java.sql.Timestamp;
/**
 * 日志bean
 * @author Administrator
 *
 */
public class LogBean extends BaseBean{
	
	private Integer id;
	private String moduleidstr;
	private String loginname;
	private String relatedid;
	private String description;
	private Timestamp operatetime;
	private String macaddress;
	private String ip;
	private String category;
	private String value;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Timestamp operatetime) {
		this.operatetime = operatetime;
	}
	public String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getRelatedid() {
		return relatedid;
	}
	public void setRelatedid(String relatedid) {
		this.relatedid = relatedid;
	}
	public String getModuleidstr() {
		return moduleidstr;
	}
	public void setModuleidstr(String moduleidstr) {
		this.moduleidstr = moduleidstr;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
}
