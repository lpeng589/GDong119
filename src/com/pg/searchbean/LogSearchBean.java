package com.pg.searchbean;

import com.pg.bean.LogBean;

/**
 * 日志搜索bean
 * @author dzp
 *
 */
public class LogSearchBean extends LogBean{
	private String starttime;
	private String endtime;
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
