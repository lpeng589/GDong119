package com.pg.bean;
/**
 * 查找条件basebean
 * @author Administrator
 *
 */
public abstract class MBaseBean{
	protected Integer page=1;	//分页第几页
	protected Integer limitStart=0; //limit开始     根据page改变sql limit开始下标
	protected Integer limitSize=5;   //limit 数量
	protected boolean  limitFlag=true;
	public Integer getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
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
	public boolean isLimitFlag() {
		return limitFlag;
	}
	public void setLimitFlag(boolean limitFlag) {
		this.limitFlag = limitFlag;
	}
	
}