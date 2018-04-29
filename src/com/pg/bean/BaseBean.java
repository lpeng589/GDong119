package com.pg.bean;

import java.sql.Timestamp;
import java.util.List;

import com.pg.servlet.BaseAction;

/**
 * 查找条件basebean
 * @author Administrator
 *
 */
public abstract class BaseBean{
	protected Integer page=1;	//分页第几页
	protected Integer limitStart=0; //limit开始     根据page改变sql limit开始下标
	protected Integer limitSize=20;   //limit 数量
	protected boolean  limitFlag=true;
	protected String selectsql="";
	protected String updatesql="";
	protected Integer employid_base; 
	protected List<String> ids ;
	protected BaseAction searchAction = null;//用于重写翻页的查询
	protected String flag_base;//一个区别标识 
	protected Integer flag_int;//一个区别标识 
	private Timestamp system_time;//系统时间
	protected String  operate;//操作
	public BaseBean() {
		this.system_time=new Timestamp(System.currentTimeMillis());
    }
	
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public Integer getEmployid_base() {
		return employid_base;
	}
	public void setEmployid_base(Integer employid_base) {
		this.employid_base = employid_base;
	}
	public Integer getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}
	public boolean isLimitFlag() {
		return limitFlag;
	}
	public void setLimitFlag(boolean limitFlag) {
		this.limitFlag = limitFlag;
	}
	public Integer getLimitSize() {
		return limitSize;
	}
	public void setLimitSize(Integer limitSize) {
		this.limitSize = limitSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
		this.limitStart=(page-1)*limitSize;
	}
	public String getSelectsql() {
		return selectsql;
	}
	public void setSelectsql(String selectsql) {
		this.selectsql = selectsql;
	}
	public String getUpdatesql() {
		return updatesql;
	}
	public void setUpdatesql(String updatesql) {
		this.updatesql = updatesql;
	}
	public BaseAction getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(BaseAction searchAction) {
		this.searchAction = searchAction;
	}
	public String getFlag_base() {
		return flag_base;
	}
	public void setFlag_base(String flag_base) {
		this.flag_base = flag_base;
	}
	public Timestamp getSystem_time() {
		return system_time;
	}

	public void setSystem_time(Timestamp system_time) {
		this.system_time = system_time;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Integer getFlag_int() {
		return flag_int;
	}

	public void setFlag_int(Integer flag_int) {
		this.flag_int = flag_int;
	}
	
	
}