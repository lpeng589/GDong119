package com.pg.searchbean;
import com.pg.bean.EventBean;
public class EventSearchBean extends EventBean
{
	private boolean flag;  //判断是否有操作权限
	private Integer deptid;  //部门id
	private Integer next_id;
	private Integer eventlogid;
	private Integer logstatus;  //案件流程状态
	private String deptusername;  //消防员名字
	private String nickname;
	private String headimgurl;
	private String username;
	private Integer user_id;
	private Integer user;
	private String inserttime2;
	private Integer status;
	private Integer next;
	private String openid;
	private Integer deal_status;
	
	public Integer getDeal_status() {
		return deal_status;
	}
	public void setDeal_status(Integer deal_status) {
		this.deal_status = deal_status;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInserttime2() {
		return inserttime2;
	}
	public void setInserttime2(String inserttime2) {
		this.inserttime2 = inserttime2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	public String getDeptusername() {
		return deptusername;
	}
	public void setDeptusername(String deptusername) {
		this.deptusername = deptusername;
	}
	public Integer getLogstatus() {
		return logstatus;
	}
	public void setLogstatus(Integer logstatus) {
		this.logstatus = logstatus;
	}
	public Integer getEventlogid() {
		return eventlogid;
	}
	public void setEventlogid(Integer eventlogid) {
		this.eventlogid = eventlogid;
	}
	public Integer getNext_id() {
		return next_id;
	}
	public void setNext_id(Integer next_id) {
		this.next_id = next_id;
	}
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	
}