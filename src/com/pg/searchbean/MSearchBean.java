package com.pg.searchbean;
import com.pg.bean.MBaseBean;
public class MSearchBean extends MBaseBean
{
	private Integer type;
	private Integer userid;
	private Integer alarmid;
	private Integer deptuserid;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getAlarmid() {
		return alarmid;
	}
	public void setAlarmid(Integer alarmid) {
		this.alarmid = alarmid;
	}
	public Integer getDeptuserid() {
		return deptuserid;
	}
	public void setDeptuserid(Integer deptuserid) {
		this.deptuserid = deptuserid;
	}
	
}